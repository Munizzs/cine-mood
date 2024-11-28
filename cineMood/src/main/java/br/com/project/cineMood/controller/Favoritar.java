package br.com.project.cineMood.controller;

import br.com.project.cineMood.dao.FavoritoDao;
import br.com.project.cineMood.model.Favorito;
import br.com.project.cineMood.model.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/favoritar")
public class Favoritar extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtendo os parâmetros do formulário
        String idFilme = request.getParameter("idFilme");
        String genero = request.getParameter("genero");

        // Simula o ID do usuário logado (deve ser substituído pela lógica de autenticação)
        int idUsuario = (int) request.getSession().getAttribute("idUsuario");

        // Criando o objeto Favorito
        Favorito favorito = new Favorito();
        favorito.setIdUsuario(idUsuario);
        favorito.setIdFilme(idFilme);
        favorito.setGenero(genero);
        favorito.setStatus(Status.Quero_Assistir); // Define um status padrão
        favorito.setAvaliacao(0); // Define uma avaliação padrão

        // Chamando o DAO para salvar no banco
        FavoritoDao favoritoDao = new FavoritoDao();
        try {
            favoritoDao.createFavorito(favorito);
            response.sendRedirect("/user/inicio?message=success"); // Redireciona com a mensagem de sucesso
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/error.jsp"); // Redireciona para uma página de erro
        }
    }
}
