package br.com.project.cineMood.controller.emocao;

import br.com.project.cineMood.dao.EmocaoDao;
import br.com.project.cineMood.model.Emocao;

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

        EmocaoDao emocaoDao = new EmocaoDao();

        String idEmocaoStr = request.getParameter("id_emocao");
        int id = 0;
        if (idEmocaoStr != null && !idEmocaoStr.isEmpty()) {
            try {
                id = Integer.parseInt(idEmocaoStr);
            } catch (NumberFormatException e) {
                System.out.println("Erro: ID inválido para criação/atualização. Valor recebido: " + idEmocaoStr);
                response.sendRedirect("/errorPage.jsp"); //Fazer essa requisição para pag erro
                return;
            }
        }

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");

        Emocao emocao = new Emocao(id, nome, descricao);

        try {
            System.out.println(id + "|" + nome + " | " + descricao);

            if (id == 0) { // ID 0 indica que é um novo usuário
                emocaoDao.createEmocao(emocao);
                System.out.println("Usuário criado com sucesso.");
            } else {
                emocaoDao.updateEmocao(emocao);
                System.out.println("Usuário atualizado com sucesso: ID " + id);
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
        req.getRequestDispatcher("resources/teste/emocaoTeste/emocao.jsp").forward(req,resp);
    }

}
