package br.com.project.cineMood.controller;

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

        Emocao emocao = new Emocao();

        emocao.setNome(request.getParameter("nome"));
        emocao.setDescricao(request.getParameter("descricao"));

        try {
            System.out.println(emocao.getNome()+" | "+ emocao.getDescricao());
            new EmocaoDao().createEmocao(emocao);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Remover
        int id_remover = Integer.parseInt(request.getParameter("id_emocao_delete"));
        new EmocaoDao().deleteEmocaoById(id_remover);

        response.sendRedirect("/emocao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Emocao> emocaoList = new EmocaoDao().findAllEmocao();
        req.setAttribute("emocaoList",emocaoList);
        req.getRequestDispatcher("resources/JSP/emocao.jsp").forward(req,resp);
    }

}
