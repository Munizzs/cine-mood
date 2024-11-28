package br.com.project.cineMood.controller;

import br.com.project.cineMood.dao.FavoritoDao;
import br.com.project.cineMood.model.Favorito;
import br.com.project.cineMood.model.Status;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            int idUsuario = (int) req.getSession().getAttribute("idUsuario");
            List<Favorito> favoritos = favoritoDao.findFavoritosByUserId(idUsuario);

            // Adiciona os favoritos como atributo para a JSP
            req.setAttribute("favoritos", favoritos);

            // Encaminha para a JSP
            req.getRequestDispatcher("/resources/front-end/favorito/index.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Id invalido.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            FavoritoDao favoritoDao = new FavoritoDao();

            if (action.equals("/deleteFavorito")) {
                int idFavorito = Integer.parseInt(request.getParameter("idFavorito"));
                favoritoDao.deleteFavoritoById(idFavorito);
                response.sendRedirect("/resources/front-end/favorito/index.jsp");
            } else if (action.equals("/updateFavorito")) {
                int idFavorito = Integer.parseInt(request.getParameter("idFavorito"));
                int avaliacao = Integer.parseInt(request.getParameter("avaliacao"));
                String genero = request.getParameter("genero");
                String statusStr = request.getParameter("status");
                Status status = Status.valueOf(statusStr.toUpperCase());

                Favorito favorito = favoritoDao.getFavoritoById(idFavorito);
                favorito.setAvaliacao(avaliacao);
                favorito.setGenero(genero);
                favorito.setStatus(status);

                favoritoDao.updateFavorito(favorito);
                //response.sendRedirect("");
                request.getRequestDispatcher("/resources/front-end/favorito/index.jsp").forward(request,response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
