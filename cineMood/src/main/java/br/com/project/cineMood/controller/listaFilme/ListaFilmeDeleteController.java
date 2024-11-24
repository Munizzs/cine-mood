package br.com.project.cineMood.controller.listaFilme;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-listaFilme")
public class ListaFilmeDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id_remover = Integer.parseInt(request.getParameter("id_lista_filme_delete"));

        new ListaFilmeDao().deleteListaFilmeById(id_remover);

        response.sendRedirect("/lista_filme");

    }
}
