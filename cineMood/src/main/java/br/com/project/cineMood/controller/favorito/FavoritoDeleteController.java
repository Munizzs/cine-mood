package br.com.project.cineMood.controller.favorito;

import br.com.project.cineMood.dao.EmocaoDao;
import br.com.project.cineMood.dao.FavoritoDao;
import br.com.project.cineMood.model.Favorito;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-favorito")
public class FavoritoDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id_remover = Integer.parseInt(request.getParameter("id_favorito_delete"));

        new FavoritoDao().deleteFavoritoById(id_remover);

        response.sendRedirect("/favorito");

    }
}
