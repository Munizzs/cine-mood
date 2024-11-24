package br.com.project.cineMood.controller.registrar;

import br.com.project.cineMood.dao.UsuarioDao;
import br.com.project.cineMood.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/registrar")
public class RegistrarController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/resources/front-end/cadastro/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UsuarioDao usuarioDao = new UsuarioDao();

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        Usuario usuario = new Usuario(nome, email, senha);

        System.out.println(nome + " | " + email + " | " + senha);

        try {
            usuarioDao.createUsuario(usuario);
            System.out.println("Usuário criado com sucesso.");
        }catch (SQLException e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/login");
    }
}
