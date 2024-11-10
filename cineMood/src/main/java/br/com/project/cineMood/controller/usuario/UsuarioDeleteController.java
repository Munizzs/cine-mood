package br.com.project.cineMood.controller.usuario;

import br.com.project.cineMood.dao.UsuarioDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-usuario")
public class UsuarioDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id_remover = Integer.parseInt(request.getParameter("id_usuario_delete"));

        new UsuarioDao().deleteUsuarioById(id_remover);

        response.sendRedirect("/usuario");

    }
}
