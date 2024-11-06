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
        String idListaFilmeDelete = request.getParameter("id_lista_filme_delete");

        try {
            ListaFilmeDao listaFilmeDao = new ListaFilmeDao();
            if (idListaFilmeDelete != null && !idListaFilmeDelete.isEmpty()) {
                int idRemover = Integer.parseInt(idListaFilmeDelete);
                listaFilmeDao.deleteListaFilmeById(idRemover);
            } else {
                ListaFilme listaFilme = new ListaFilme();
                listaFilme.setId_usuario(Integer.parseInt(request.getParameter("usuario")));
                listaFilme.setId_filme(Integer.parseInt(request.getParameter("filme")));
                listaFilme.setStatus(request.getParameter("status"));
                listaFilme.setAvaliacao(Integer.parseInt(request.getParameter("avaliacao")));
                listaFilme.setData_adicao(request.getParameter("data_adicao"));

                System.out.println(listaFilme.getId_usuario() + " | " + listaFilme.getId_filme() + " | " + listaFilme.getStatus() + " | " + listaFilme.getAvaliacao() + " | " + listaFilme.getData_adicao());
                listaFilmeDao.createListaFilme(listaFilme);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao processar a requisição", e);
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
