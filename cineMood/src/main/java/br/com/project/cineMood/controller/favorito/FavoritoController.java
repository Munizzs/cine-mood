package br.com.project.cineMood.controller.favorito;
import br.com.project.cineMood.dao.FavoritoDao;
import br.com.project.cineMood.model.Emocao;
import br.com.project.cineMood.model.Favorito;
import br.com.project.cineMood.model.Status;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/favorito")
public class FavoritoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FavoritoDao favoritoDao = new FavoritoDao();

        String idFavoritoStr = request.getParameter("id_favorito");
        int id = 0;
        if (idFavoritoStr != null && !idFavoritoStr.isEmpty()) {
            try {
                id = Integer.parseInt(idFavoritoStr);
            } catch (NumberFormatException e) {
                System.out.println("Erro: ID inválido para criação/atualização. Valor recebido: " + idFavoritoStr);
                response.sendRedirect("/errorPage.jsp"); //Fazer essa requisição para pag erro
                return;
            }
        }

        int idUsuario = Integer.parseInt(request.getParameter("usuario"));
        String idFilme = request.getParameter("filme");
        Status status = Status.valueOf(request.getParameter("status"));
        int avaliacao = Integer.parseInt(request.getParameter("avaliacao"));
        String genero = request.getParameter("genero");

        Favorito favorito = new Favorito(id,idUsuario, status, avaliacao, idFilme, genero);

        try {
            System.out.println(id + "|" + idUsuario + " | " + status+ " | " + avaliacao+ " | " + idFilme+ " | " + genero);

            if (id == 0) { // ID 0 indica que é um novo usuário
                favoritoDao.createFavorito(favorito);
                System.out.println("Favorito criado com sucesso.");
            } else {
                favoritoDao.updateFavorito(favorito);
                System.out.println("Favorito atualizado com sucesso: ID " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao processar a requisição", e);
        }

        response.sendRedirect("/favorito");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Favorito> favoritos = new FavoritoDao().findAllFavorito();
        req.setAttribute("favoritos",favoritos);
        req.getRequestDispatcher("resources/teste/favoritoTeste/index.jsp").forward(req,resp);
    }
    }


