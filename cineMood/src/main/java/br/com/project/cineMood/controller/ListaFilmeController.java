package br.com.project.cineMood.controller;

import br.com.project.cineMood.dao.ListaFilmeDao;
import br.com.project.cineMood.model.ListaFilme;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/lista_filme")
public class ListaFilmeController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ListaFilme listaFilme = new ListaFilme();

        listaFilme.setId_usuario(Integer.parseInt(request.getParameter("usuario")));
        listaFilme.setId_filme(Integer.parseInt(request.getParameter("filme")));
        listaFilme.setStatus(request.getParameter("status"));
        listaFilme.setAvaliacao(Integer.parseInt(request.getParameter("avaliacao")));
        listaFilme.setData_adicao(request.getParameter("data_adicao"));

        try {
            System.out.println(listaFilme.getId_usuario()+" | "+listaFilme.getId_filme()+" | "+listaFilme.getStatus()+" | "+listaFilme.getAvaliacao()+" | "+listaFilme.getData_adicao());
            new ListaFilmeDao().createListaFilme(listaFilme);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/lista_filme");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ListaFilme> listaFilmes = new ListaFilmeDao().findAlllista_filmes();
        req.setAttribute("lista_filmes",listaFilmes);
        req.getRequestDispatcher("/resources/listaFilmeTeste/index.jsp").forward(req,resp);
    }
}
