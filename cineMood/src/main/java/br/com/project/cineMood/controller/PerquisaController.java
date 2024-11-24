package br.com.project.cineMood.controller;

import br.com.project.cineMood.model.Filme;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/pesquisa")
public class PerquisaController extends HttpServlet {
    private static List<Filme> filmes;

    static {
        filmes = new ArrayList<>();
        filmes.add(new Filme("O Senhor dos Anéis", "9.0", "Uma jornada épica pela Terra Média.", "https://link_do_poster.com/sda.jpg"));
        filmes.add(new Filme("Harry Potter", "7.6", "A saga do menino que sobreviveu.", "https://link_do_poster.com/hp.jpg"));
        filmes.add(new Filme("Matrix", "8.7", "Uma simulação virtual controlada por máquinas.", "https://link_do_poster.com/matrix.jpg"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obter o termo de pesquisa
        String query = request.getParameter("query");

        List<Filme> resultados = new ArrayList<>();

        if (query != null && !query.isEmpty()) {
            // Filtrar os filmes que correspondem ao termo
            for (Filme filme : filmes) {
                if (filme.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    resultados.add(filme);
                }
            }
        }

        // Adicionar os resultados ao request
        request.setAttribute("resultados", resultados);

        // Encaminhar para o JSP
        request.getRequestDispatcher("/resources/front-end/pesquisa/index.jsp").forward(request, response);
    }
}