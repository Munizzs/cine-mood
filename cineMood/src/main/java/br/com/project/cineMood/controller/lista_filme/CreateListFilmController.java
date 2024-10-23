package br.com.project.cineMood.controller.lista_filme;

import br.com.project.cineMood.dao.ListaFilmeDao;
import br.com.project.cineMood.model.ListaFilme;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/create-lista_filmes")
public class CreateListFilmController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id_lista = Integer.parseInt(request.getParameter("lista"));
        int id_usuario = Integer.parseInt(request.getParameter("usuario"));
        int id_filme = Integer.parseInt(request.getParameter("filme"));
        String status = request.getParameter("status");
        int avaliacao = Integer.parseInt(request.getParameter("avaliacao"));
        String data_adicao = request.getParameter("data_adicao");

        System.out.println(id_lista+" | "+id_usuario+" | "+id_filme+" | "+status+" | "+avaliacao+" | "+data_adicao);

        try {
            System.out.println(id_lista+" | "+id_usuario+" | "+id_filme+" | "+status+" | "+avaliacao+" | "+data_adicao);
            ListaFilme listafilme= new ListaFilme(id_lista,id_usuario,id_filme,status,avaliacao,data_adicao);
            new ListaFilmeDao().createListaFilme(listafilme);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/find-all-lista_filmes");

    }
}
