package br.com.project.cineMood.controller.recomendacao;

import br.com.project.cineMood.dao.ListaFilmeDao;
import br.com.project.cineMood.dao.RecomendacaoDao;
import br.com.project.cineMood.model.ListaFilme;
import br.com.project.cineMood.model.Recomendacao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/create-recomendacao")
public class CreateRecomendacaoController extends HttpServlet {


        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            int id_usuario = Integer.parseInt(request.getParameter("usuario"));
            int id_filme = Integer.parseInt(request.getParameter("filme"));
            int id_emocao = Integer.parseInt(request.getParameter("emocao"));
            String data_recomendacao = request.getParameter("data_recomendacao");

            System.out.println(id_usuario+" | "+id_filme+" | "+id_filme+" | "+id_emocao+" | "+data_recomendacao);

            try {
                Recomendacao recomendacao= new Recomendacao(id_usuario,id_filme,id_emocao,data_recomendacao);
                new RecomendacaoDao().createRecomendacao(recomendacao);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            response.sendRedirect("/find-all-recomendacao");

        }
    }

