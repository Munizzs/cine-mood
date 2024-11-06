package br.com.project.cineMood.controller;

import br.com.project.cineMood.dao.EmocaoDao;
import br.com.project.cineMood.model.Emocao;
import br.com.project.cineMood.model.Favorito;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/emocao")
public class EmocaoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idEmocaoDelete = request.getParameter("id_emocao_delete");

        try {
            EmocaoDao emocaoDao = new EmocaoDao();
            if (idEmocaoDelete != null && !idEmocaoDelete.isEmpty()) {
                int idRemover = Integer.parseInt(idEmocaoDelete);
                emocaoDao.deleteEmocaoById(idRemover);
            } else {
                String nome = request.getParameter("nome");
                String descricao = request.getParameter("descricao");

                Emocao emocao = new Emocao(nome, descricao);
                emocaoDao.createEmocao(emocao);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao processar a requisição", e);
        }

        response.sendRedirect("/emocao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Emocao> emocoes = new EmocaoDao().findAllEmocao();
        req.setAttribute("emocoes",emocoes);
        req.getRequestDispatcher("resources/emocaoTeste/emocao.jsp").forward(req,resp);
    }

}
