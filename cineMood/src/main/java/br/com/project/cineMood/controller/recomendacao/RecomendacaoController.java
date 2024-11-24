package br.com.project.cineMood.controller.recomendacao;

import br.com.project.cineMood.dao.RecomendacaoDao;

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
        RecomendacaoDao recomendacaoDao = new RecomendacaoDao();

        String idRecomendacaoStr = request.getParameter("id_recomendacao");
        int id = 0;
        if (idRecomendacaoStr != null && !idRecomendacaoStr.isEmpty()) {
            try {
                id = Integer.parseInt(idRecomendacaoStr);
            } catch (NumberFormatException e) {
                System.out.println("Erro: ID inválido para criação/atualização. Valor recebido: " + idRecomendacaoStr);
                response.sendRedirect("/errorPage.jsp"); //Fazer essa requisição para pag erro
                return;
            }
        }

        int usuario = Integer.parseInt(request.getParameter("usuario"));
        int filme = Integer.parseInt(request.getParameter("filme"));
        int emocao = Integer.parseInt(request.getParameter("emocao"));
        String data = request.getParameter("data_recomendacao");

        Recomendacao recomendacao = new Recomendacao(id,usuario, filme, emocao, data);

        try {
            System.out.println(id + "|" + usuario + " | " + filme+ " | " + emocao +" | "+data);

            if (id == 0) { // ID 0 indica que é um novo usuário
                recomendacaoDao.createRecomendacao(recomendacao);
                System.out.println("Favorito criado com sucesso.");
            } else {
                recomendacaoDao.updateRecomendacao(recomendacao);
                System.out.println("Favorito atualizado com sucesso: ID " + id);
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
        req.getRequestDispatcher("/resources/teste/recomendacaoTeste/index.jsp").forward(req,resp);
    }
}
