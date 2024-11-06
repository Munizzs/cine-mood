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

        UsuarioDao usuarioDao = new UsuarioDao();

        String idRemover = request.getParameter("id_usuario_delete");
        if (idRemover != null && !idRemover.isEmpty()) {
            try {
                int id = Integer.parseInt(idRemover);
                usuarioDao.deleteUsuarioById(id);
                System.out.println("Usuário removido com sucesso: ID " + id);
            } catch (NumberFormatException e) {
                System.out.println("Erro: ID inválido para remoção.");
            }
        } else {
            Usuario usuario = new Usuario();
            usuario.setId_usuario(request.getParameter("id_usuario"));
            usuario.setNome(request.getParameter("nome"));
            usuario.setEmail(request.getParameter("email"));
            usuario.setSenha(request.getParameter("senha"));
            usuario.setData_nascimento(request.getParameter("data_nascimento"));

            try {
                System.out.println(usuario.getNome() + " | " + usuario.getEmail() + " | " + usuario.getSenha() + " | " + usuario.getData_nascimento());

                if (usuario.getId_usuario() == null || usuario.getId_usuario().isEmpty()) {
                    usuarioDao.createUsuario(usuario);
                    System.out.println("Usuário criado com sucesso.");
                } else {
                    usuarioDao.updateUsuario(usuario);
                    System.out.println("Usuário atualizado com sucesso: ID " + usuario.getId_usuario());
                }
            } catch (SQLException e) {
                System.out.println("Erro ao criar/atualizar usuário: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        
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
