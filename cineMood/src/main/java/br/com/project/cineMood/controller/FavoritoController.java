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

@WebServlet("/favoritos")
public class FavoritoController extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Favorito> favoritoList = new FavoritoDao().findAllFavorito();
            req.setAttribute("FavoritoList",favoritoList);
            req.getRequestDispatcher("resources/FavoritoTeste/index.jsp").forward(req,resp);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            int id_usuario = Integer.parseInt(request.getParameter("usuario"));
            int id_filme = Integer.parseInt(request.getParameter("filme"));
            String data_favoritado = request.getParameter("data_favoritado");

            try {
                System.out.println(id_usuario+" | "+ id_filme+" | "+data_favoritado);
                Favorito favorito = new Favorito(id_usuario, id_filme, data_favoritado);
                new FavoritoDao().createFavorito(favorito);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            response.sendRedirect("/favoritos");
        }
    }


