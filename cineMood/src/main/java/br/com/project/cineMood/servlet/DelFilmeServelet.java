package br.com.project.cineMood.servlet;


import br.com.project.cineMood.dao.FilmeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-film")
public class DelFilmeServelet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id_delFilm"));
        new FilmeDao().deleteFilmById(id);
        req.getRequestDispatcher("/find-all-film").forward(req, resp);
    }
}
