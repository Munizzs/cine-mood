package br.com.project.cineMood.controller.usuario;

import br.com.project.cineMood.dao.UsuarioDao;
import br.com.project.cineMood.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/usuario")
public class CreateUsuarioController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String data_nascimento = request.getParameter("data_nascimento");
        try {
            System.out.println(nome+" | "+email+" | "+senha+" | "+data_nascimento);
            Usuario usuario = new Usuario(nome, email, senha, data_nascimento);
            new UsuarioDao().createUsuario(usuario);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("/find-all-usuario");

    }
}
