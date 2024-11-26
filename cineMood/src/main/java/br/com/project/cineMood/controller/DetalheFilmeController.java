package br.com.project.cineMood.controller;

import br.com.project.cineMood.config.TmdbApiClient;
import br.com.project.cineMood.model.Filme;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/detalhes")
public class DetalheFilmeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movieId = "220848";//request.getParameter("id"); // Não esta pegando o id

        System.out.println(movieId);

        if (movieId == null || movieId.isEmpty()) {
            response.sendRedirect("/user/pesquisa");
            return;
        }

        try (TmdbApiClient client = new TmdbApiClient()) {
            String endpoint = "/movie/" + movieId;
            Map<String, String> params = new HashMap<>();
            params.put("language", "pt-BR");

            JSONObject responseJson = client.get(endpoint, params);

            if (responseJson != null) {
                Filme filme = new Filme(
                        responseJson.getString("title"),
                        "https://image.tmdb.org/t/p/w500" + responseJson.optString("poster_path"),
                        //responseJson.getString("genres"),
                        responseJson.getString("overview")
                );

                filme.setVoteAverage(responseJson.optDouble("vote_average"));

                request.setAttribute("filme", filme);
            } else {
                request.setAttribute("error", "Não foi possível obter os detalhes do filme.");
            }

            request.getRequestDispatcher("/resources/front-end/detalhe/index.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
