package br.com.project.cineMood.controller.login;

import br.com.project.cineMood.dao.UsuarioDao;
import br.com.project.cineMood.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LogarController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/resources/front-end/login/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String senha = req.getParameter("senha");

        Usuario usuario = new Usuario(nome, senha);

        boolean ehUsuarioValido = new UsuarioDao().verificarCredencial(usuario);

        if(ehUsuarioValido){
            req.getSession().setAttribute("loggedUser",nome);

            resp.sendRedirect("/admin/searchMovie");
        }else{
            req.setAttribute("mensagem","Credencial Invalida");
            req.getRequestDispatcher("/resources/front-end/login/index.jsp").forward(req,resp);
        }
    }
}
