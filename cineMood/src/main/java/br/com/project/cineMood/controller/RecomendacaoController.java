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
        String idRecomendacaoDelete = request.getParameter("id_recomendacao_delete");

        try {
            RecomendacaoDao recomendacaoDao = new RecomendacaoDao();
            if (idRecomendacaoDelete != null && !idRecomendacaoDelete.isEmpty()) {
                int idRemover = Integer.parseInt(idRecomendacaoDelete);
                recomendacaoDao.deleteRecomendacaoById(idRemover);
            } else {
                Recomendacao recomendacao = new Recomendacao();
                recomendacao.setId_usuario(Integer.parseInt(request.getParameter("usuario")));
                recomendacao.setId_filme(Integer.parseInt(request.getParameter("filme")));
                recomendacao.setId_emocao(Integer.parseInt(request.getParameter("emocao")));
                recomendacao.setData_recomendacao(request.getParameter("data_recomendacao"));

                System.out.println(recomendacao.getId_usuario() + " | " + recomendacao.getId_filme() + " | " + recomendacao.getId_emocao() + " | " + recomendacao.getData_recomendacao());
                recomendacaoDao.createRecomendacao(recomendacao);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao processar a requisição", e);
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
