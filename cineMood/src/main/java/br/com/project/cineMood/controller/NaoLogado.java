package br.com.project.cineMood.controller;

import br.com.project.cineMood.config.Config;
import br.com.project.cineMood.model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/a")
public class NaoLogado extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String apiKey = Config.getApiKey();
        if (apiKey == null) {
            System.out.println("Erro: A chave da API não foi encontrada.");
            resp.getWriter().write("Erro: A chave da API não foi encontrada.");
            return;
        }

        String[] movieTitles = {"Inception", "Interstellar", "The Dark Knight", "Joker", "Deadpool 2", "Inside out 2"};
        List<Movie> movies = new ArrayList<>();

        for (String title : movieTitles) {
            String apiUrl = "http://www.omdbapi.com/?t=" + title.replace(" ", "%20") + "&apikey=" + apiKey;
            try {
                // Conexão com a API
                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder jsonOutput = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    jsonOutput.append(line);
                }
                br.close();

                // Parse da resposta JSON usando org.json
                JSONObject jsonResponse = new JSONObject(jsonOutput.toString());
                if (jsonResponse.getString("Response").equals("True")) {
                    Movie movie = new Movie(
                            jsonResponse.getString("Title"),
                            jsonResponse.getString("Poster")
                    );
                    movies.add(movie);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Lista de filmes: " + movies.size());
        for (Movie movie : movies) {
            System.out.println("Filme: " + movie.getTitle() + ", Poster: " + movie.getPoster());
        }

        // Envia a lista de filmes para o JSP
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/resources/front-end/nao_logada/index.jsp").forward(req, resp);
    }
}