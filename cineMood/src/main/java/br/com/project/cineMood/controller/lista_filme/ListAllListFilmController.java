package br.com.project.cineMood.controller.lista_filme;

import br.com.project.cineMood.dao.ListaFilmeDao;
import br.com.project.cineMood.model.ListaFilme;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/find-all-lista_filmes")
public class ListAllListFilmController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ListaFilme> listaFilmes = new ListaFilmeDao().findAlllista_filmes();
        req.setAttribute("lista_filmes",listaFilmes);
        req.getRequestDispatcher("/resources/listaFilmeTeste/index.jsp").forward(req,resp);
    }
}
