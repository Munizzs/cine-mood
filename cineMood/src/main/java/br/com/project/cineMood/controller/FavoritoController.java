package br.com.project.cineMood.controller;

import br.com.project.cineMood.dao.FavoritoDao;
import br.com.project.cineMood.model.Favorito;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/favorito-filme")
public class FavoritoController extends HttpServlet {
    private final FavoritoDao favoritoDao = new FavoritoDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtém a lista de favoritos do banco
            List<Favorito> favoritos = favoritoDao.findAllFavorito();

            // Passa os dados para a página JSP
            request.setAttribute("favoritos", favoritos);
            request.getRequestDispatcher("/resources/front-end/favorito/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erro ao carregar os favoritos: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("delete".equals(action)) {
                // Excluir favorito
                int idFavorito = Integer.parseInt(request.getParameter("idFavorito"));
                favoritoDao.deleteFavoritoById(idFavorito);
            } else if ("edit".equals(action)) {
                // Editar favorito
                int idFavorito = Integer.parseInt(request.getParameter("idFavorito"));
                String status = request.getParameter("status");
                int avaliacao = Integer.parseInt(request.getParameter("avaliacao"));

                Favorito favorito = favoritoDao.getFavoritoById(idFavorito);
                favorito.setStatus(Favorito.Status.valueOf(status.toUpperCase()));
                favorito.setAvaliacao(avaliacao);

                favoritoDao.updateFavorito(favorito);
            }

            // Redireciona após ação
            response.sendRedirect(request.getContextPath() + "/user/favorito-filme");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erro ao processar a ação: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
