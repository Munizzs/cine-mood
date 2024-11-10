package br.com.project.cineMood.controller.emocao;

import br.com.project.cineMood.dao.EmocaoDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-emocao")
public class EmocaoDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id_remover = Integer.parseInt(request.getParameter("id_emocao_delete"));

        new EmocaoDao().deleteEmocaoById(id_remover);

        response.sendRedirect("/emocao");

    }
}
