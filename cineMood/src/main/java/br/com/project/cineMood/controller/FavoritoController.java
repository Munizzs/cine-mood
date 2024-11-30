package br.com.project.cineMood.controller;

import br.com.project.cineMood.config.TmdbApiClient;
import br.com.project.cineMood.dao.FavoritoDao;
import br.com.project.cineMood.model.Favorito;
import br.com.project.cineMood.model.Filme;
import br.com.project.cineMood.model.Status;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/favorito-filme")
public class FavoritoController extends HttpServlet {

    private final FavoritoDao favoritoDao = new FavoritoDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int userId = (int) req.getSession().getAttribute("idUsuario");
            String statusParam = req.getParameter("status"); // Obtém o parâmetro de filtro

            List<Favorito> favoritos;
            if (statusParam != null && !statusParam.isEmpty()) {
                try {
                    // Converte o parâmetro para enum e busca favoritos filtrados
                    Status status = Status.valueOf(statusParam);
                    favoritos = favoritoDao.findFavoritosByUserIdAndStatus(userId, status);
                } catch (IllegalArgumentException e) {
                    throw new ServletException("Status inválido: " + statusParam, e);
                }
            } else {
                // Busca todos os favoritos se nenhum status for informado
                favoritos = favoritoDao.findFavoritosByUserId(userId);
            }


            req.setAttribute("favoritos", favoritos);

            // Busca informações dos filmes favoritos
            List<String> movieIds = new ArrayList<>();
            for (Favorito favorito : favoritos) {
                movieIds.add(favorito.getIdFilme());
            }

            List<Filme> filmesFavoritos = new ArrayList<>();
            for (String movieId : movieIds) {
                try (TmdbApiClient client = new TmdbApiClient()) {
                    String endpoint = "/movie/" + movieId;
                    Map<String, String> params = new HashMap<>();
                    params.put("api_key", "SUA_CHAVE_API");
                    params.put("language", "pt-BR");

                    JSONObject responseJson = client.get(endpoint, params);

                    if (responseJson != null && responseJson.has("title")) {
                        Filme filme = new Filme(
                                responseJson.getString("title"),
                                "https://image.tmdb.org/t/p/original" + responseJson.optString("backdrop_path"),
                                0
                        );
                        filme.setId(responseJson.optInt("id"));
                        filmesFavoritos.add(filme);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            req.setAttribute("filmesFavoritos", filmesFavoritos);
            req.getRequestDispatcher("/resources/front-end/favorito/index.jsp").forward(req, resp);

        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar favoritos.\n" + e.getMessage());
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("updateFavorito".equals(action)) {
                int idFavorito = Integer.parseInt(request.getParameter("idFavorito"));
                int avaliacao = Integer.parseInt(request.getParameter("avaliacao"));
                String genero = request.getParameter("genero");
                String statusStr = request.getParameter("status");
                Status status = Status.valueOf(statusStr);

                Favorito favorito = favoritoDao.getFavoritoById(idFavorito);
                favorito.setAvaliacao(avaliacao);
                favorito.setGenero(genero);
                favorito.setStatus(status);

                favoritoDao.updateFavorito(favorito);

                response.sendRedirect("/user/favorito-filme");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}