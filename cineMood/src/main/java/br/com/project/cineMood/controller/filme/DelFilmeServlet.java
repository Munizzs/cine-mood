package br.com.project.cineMood.controller.filme;


import br.com.project.cineMood.dao.FilmeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-film")
public class DelFilmeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id_delFilm"));
        new FilmeDao().deleteFilmById(id);
        resp.sendRedirect("/find-all-film");
    }
}
