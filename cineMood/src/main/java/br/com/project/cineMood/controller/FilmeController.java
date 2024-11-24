package br.com.project.cineMood.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/filme")
public class FilmeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Criar
        Filme filme = new Filme();

        filme.setTitulo(request.getParameter("titulo"));
        filme.setTipo(request.getParameter("tipo"));

        try {
            System.out.println(filme.getId_filme()+" | "+ filme.getTitulo()+" | "+filme.getTipo());
            new FilmeDao().createFilme(filme);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Remover
        int id = Integer.parseInt(request.getParameter("id_delFilm"));
        new FilmeDao().deleteFilmById(id);
        response.sendRedirect("/filme");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Listar Todos
        List<Filme> films = new FilmeDao().findAllFilm();
        req.setAttribute("films",films);
        req.getRequestDispatcher("/resources/teste/filmeTeste/index.jsp").forward(req,resp);
    }

}
