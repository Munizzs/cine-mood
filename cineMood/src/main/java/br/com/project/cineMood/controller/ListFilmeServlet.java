package br.com.project.cineMood.controller;

import br.com.project.cineMood.dao.FilmeDao;
import br.com.project.cineMood.model.Filme;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/find-all-film")
public class ListFilmeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Filme> films = new FilmeDao().findAllFilm();
        req.setAttribute("films",films);
        req.getRequestDispatcher("index.jsp").forward(req,resp);

    }
}
