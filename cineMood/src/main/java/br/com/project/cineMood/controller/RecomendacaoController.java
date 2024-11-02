package br.com.project.cineMood.controller;

import br.com.project.cineMood.dao.RecomendacaoDao;
import br.com.project.cineMood.model.Recomendacao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/recomendacao")
public class RecomendacaoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Recomendacao recomendacao = new Recomendacao();

        recomendacao.setId_usuario(Integer.parseInt(request.getParameter("usuario")));
        recomendacao.setId_filme(Integer.parseInt(request.getParameter("filme")));
        recomendacao.setId_emocao(Integer.parseInt(request.getParameter("emocao")));
        recomendacao.setData_adicao(request.getParameter("data_recomendacao"));

        try {
            System.out.println(recomendacao.getId_usuario()+" | "+recomendacao.getId_filme()+" | "+recomendacao.getId_filme()+" | "+recomendacao.getId_emocao()+" | "+recomendacao.getData_adicao());

            new RecomendacaoDao().createRecomendacao(recomendacao);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/recomendacao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Recomendacao> recomendacoes= new RecomendacaoDao().findAllRecomendacao();
        req.setAttribute("recomendacoes",recomendacoes);
        req.getRequestDispatcher("/resources/recomendacaoTeste/index.jsp").forward(req,resp);
    }
}
