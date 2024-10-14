package br.com.project.cineMood.controller;

import br.com.project.cineMood.dao.FilmeDao;
import br.com.project.cineMood.model.Filme;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/create-cineMood")
public class CreateCineMoodServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String titulo = request.getParameter("titulo");
        String tipo = request.getParameter("tipo");
        try {
        System.out.println(titulo+" | "+tipo);
        Filme filme = new Filme(titulo,tipo);
        new FilmeDao().createFilme(filme);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/find-all-film");

    }
}
