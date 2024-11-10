package br.com.project.cineMood.controller.listaFilme;

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
        ListaFilmeDao listaFilmeDao = new ListaFilmeDao();

        String idListaFilmeStr = request.getParameter("id_lista");
        int id = 0;
        if (idListaFilmeStr != null && !idListaFilmeStr.isEmpty()) {
            try {
                id = Integer.parseInt(idListaFilmeStr);
            } catch (NumberFormatException e) {
                System.out.println("Erro: ID inválido para criação/atualização. Valor recebido: " + idListaFilmeStr);
                response.sendRedirect("/errorPage.jsp"); //Fazer essa requisição para pag erro
                return;
            }
        }

        int idUsuario = Integer.parseInt(request.getParameter("usuario"));
        int IdFilme = Integer.parseInt(request.getParameter("filme"));
        String status = request.getParameter("status");
        int avalicao = Integer.parseInt(request.getParameter("avaliacao"));
        String data = request.getParameter("data_adicao");

        ListaFilme listaFilme = new ListaFilme(id, idUsuario, IdFilme, status, avalicao, data);

        try {
            System.out.println(id + "|" + idUsuario + " | " + IdFilme+ " | " + status+ " | " + avalicao+ " | " + data);

            if (id == 0) {
                listaFilmeDao.createListaFilme(listaFilme);
                System.out.println("Criado com sucesso.");
            } else {
                listaFilmeDao.updateListaFilme(listaFilme);
                System.out.println("Atualizado com sucesso: ID " + id);
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
