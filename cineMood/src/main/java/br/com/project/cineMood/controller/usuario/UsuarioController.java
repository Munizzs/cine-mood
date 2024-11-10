package br.com.project.cineMood.controller2;

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

        // Checa se o parâmetro "id_usuario" está presente e é um número
        String idUsuarioStr = request.getParameter("id_usuario");
        int id = 0;
        if (idUsuarioStr != null && !idUsuarioStr.isEmpty()) {
            try {
                id = Integer.parseInt(idUsuarioStr);
            } catch (NumberFormatException e) {
                System.out.println("Erro: ID inválido para criação/atualização. Valor recebido: " + idUsuarioStr);
                // Você pode enviar uma mensagem de erro ou redirecionar o usuário para uma página de erro aqui.
                response.sendRedirect("/errorPage.jsp");
                return;
            }
        }

        // Cria o objeto Usuário com os dados do formulário
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String data_nascimento = request.getParameter("data_nascimento");

        Usuario usuario = new Usuario(id, nome, email, senha, data_nascimento);

        try {
            System.out.println(id + "|" + nome + " | " + email + " | " + senha + " | " + data_nascimento);

            if (id == 0) { // ID 0 indica que é um novo usuário
                usuarioDao.createUsuario(usuario);
                System.out.println("Usuário criado com sucesso.");
            } else {
                usuarioDao.updateUsuario(usuario);
                System.out.println("Usuário atualizado com sucesso: ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao criar/atualizar usuário: " + e.getMessage());
            throw new RuntimeException(e);
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
