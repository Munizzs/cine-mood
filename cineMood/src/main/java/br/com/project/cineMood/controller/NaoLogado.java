package br.com.project.cineMood.controller;

import br.com.project.cineMood.config.Config;
import br.com.project.cineMood.model.Movie;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/")
public class NaoLogado extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String apiKey = Config.getApiKey();
        if (apiKey == null) {
            System.out.println("Erro: A chave da API não foi encontrada.");
            resp.getWriter().write("Erro: A chave da API não foi encontrada.");
            return;
        }

        String[] lancamentoTitulo = {"Barbie", "Oppenheimer", "Deadpool 2", "Alien: Romulus", "Inside out 2", "Five Nights at Freddy's"};
        List<Movie> movies = fetchMoviesFromApi(lancamentoTitulo, apiKey);

        String[] recommendedTitles = {"Inception", "Interstellar", "The Dark Knight", "Joker", "Deadpool 2", "Inside out 2"};
        List<Movie> recommendedMovies = fetchMoviesFromApi(recommendedTitles, apiKey);

        // Debug para verificar as listas
        System.out.println("Lançamentos: " + movies.size());
        for (Movie movie : movies) {
            System.out.println("Filme: " + movie.getTitle() + ", Poster: " + movie.getPoster());
        }

        System.out.println("Recomendados: " + recommendedMovies.size());
        for (Movie movie : recommendedMovies) {
            System.out.println("Filme: " + movie.getTitle() + ", Poster: " + movie.getPoster());
        }

        // Envia as listas de filmes para o JSP
        req.setAttribute("movies", movies);
        req.setAttribute("recommendedMovies", recommendedMovies);
        req.getRequestDispatcher("/resources/front-end/nao_logada/index.jsp").forward(req, resp);
    }

    private List<Movie> fetchMoviesFromApi(String[] movieTitles, String apiKey) {
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
        return movies;
    }
}