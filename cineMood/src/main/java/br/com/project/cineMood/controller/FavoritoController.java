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
            // Obtendo o ID do usuário da sessão
            int userId = (int) req.getSession().getAttribute("idUsuario");

            // Buscando os favoritos do banco de dados
            List<Favorito> favoritos = favoritoDao.findFavoritosByUserId(userId);
            req.setAttribute("favoritos", favoritos); // Passa os favoritos do banco para o JSP

            // Buscando os IDs dos filmes favoritos do usuário
            List<String> movieIds = favoritoDao.findFavoriteMovieIdsByUserId(userId);

            List<Filme> filmesFavoritos = new ArrayList<>();

            // Fazendo a requisição à API para cada filme favorito
            for (String movieId : movieIds) {
                try (TmdbApiClient client = new TmdbApiClient()) {
                    // Configurando os parâmetros para a busca
                    String endpoint = "/movie/" + movieId; // Requisição para detalhes do filme
                    Map<String, String> params = new HashMap<>();
                    params.put("api_key", "SUA_CHAVE_API");
                    params.put("language", "pt-BR");

                    // Fazendo a requisição à API para buscar os detalhes do filme
                    JSONObject responseJson = client.get(endpoint, params);

                    // Verificando se a resposta contém dados
                    if (responseJson != null && responseJson.has("title")) {
                        Filme filme = new Filme(
                                responseJson.getString("title"),
                                "https://image.tmdb.org/t/p/original" + responseJson.optString("backdrop_path"),
                                0
                        );
                        filme.setId(responseJson.optInt("id"));
                        //filme.setOverview(responseJson.optString("overview"));
                        //filme.setReleaseDate(responseJson.optString("release_date"));

                        // Adicionando o filme à lista de favoritos
                        filmesFavoritos.add(filme);
                    }
                } catch (Exception e) {
                    e.printStackTrace(); // Log de erro ao buscar o filme
                }
            }

            // Passando a lista de filmes favoritos para o JSP
            req.setAttribute("filmesFavoritos", filmesFavoritos);
            req.getRequestDispatcher("/resources/front-end/favorito/index.jsp").forward(req, resp);

        } catch (Exception e) {
            // Em caso de erro, envia uma mensagem de erro para o cliente
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar favoritos.");
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