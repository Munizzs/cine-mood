package br.com.project.cineMood.controller;

import br.com.project.cineMood.dao.FilmeDao;
import br.com.project.cineMood.dao.UsuarioDao;
import br.com.project.cineMood.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Criar
        Usuario usuario = new Usuario();

        usuario.setNome(request.getParameter("nome"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setSenha(request.getParameter("senha"));
        usuario.setData_nascimento(request.getParameter("data_nascimento"));

        try {
            System.out.println(usuario.getNome()+" | "+usuario.getEmail()+" | "+usuario.getSenha()+" | "+usuario.getData_nascimento());
            new UsuarioDao().createUsuario(usuario);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Remover
        int id_remover = Integer.parseInt(request.getParameter("id_usuario_delete"));
        new UsuarioDao().deleteUsuarioById(id_remover);

        response.sendRedirect("/usuario");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Listar Todos
        List<Usuario> usuarios = new UsuarioDao().findAllUsuario();
        req.setAttribute("usuarios",usuarios);
        req.getRequestDispatcher("/resources/usuarioTeste/index.jsp").forward(req,resp);
    }
}
