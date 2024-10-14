package br.com.project.cineMood.controller.usuario;

import br.com.project.cineMood.dao.UsuarioDao;
import br.com.project.cineMood.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/find-all-usuario")
public class ListAllUsuarioController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Usuario> usuarios = new UsuarioDao().findAllUsuario();
        req.setAttribute("usuarios",usuarios);
        req.getRequestDispatcher("/resources/usuarioTeste/index.jsp").forward(req,resp);
    }
}
