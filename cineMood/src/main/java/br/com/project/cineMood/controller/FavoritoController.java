package br.com.project.cineMood.controller;
import br.com.project.cineMood.dao.FavoritoDao;
import br.com.project.cineMood.model.Favorito;
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
        String idFavoritoDelete = request.getParameter("id_favorito_delete");

        try {
            FavoritoDao favoritoDao = new FavoritoDao();
            if (idFavoritoDelete != null && !idFavoritoDelete.isEmpty()) {
                int idRemover = Integer.parseInt(idFavoritoDelete);
                favoritoDao.deleteFavoritoById(idRemover);
            } else {
                int id_usuario = Integer.parseInt(request.getParameter("usuario"));
                int id_filme = Integer.parseInt(request.getParameter("filme"));
                String data_favoritado = request.getParameter("data_favoritado");

                Favorito favorito = new Favorito(id_usuario, id_filme, data_favoritado);
                favoritoDao.createFavorito(favorito);
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
        req.getRequestDispatcher("resources/favoritoTeste/index.jsp").forward(req,resp);
    }
    }


