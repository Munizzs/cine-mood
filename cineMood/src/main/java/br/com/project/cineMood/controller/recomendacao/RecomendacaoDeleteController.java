package br.com.project.cineMood.controller.recomendacao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-recomendacao")
public class RecomendacaoDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id_remover = Integer.parseInt(request.getParameter("id_recomendacao_delete"));

        new RecomendacaoDao().deleteRecomendacaoById(id_remover);

        response.sendRedirect("/recomendacao");

    }
}
