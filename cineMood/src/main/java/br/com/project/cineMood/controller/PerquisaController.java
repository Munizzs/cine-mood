package br.com.project.cineMood.controller;

import br.com.project.cineMood.config.TmdbApiClient;
import br.com.project.cineMood.model.Filme;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/pesquisa")
public class PerquisaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet iniciado - recebendo requisição");

        String movieTitle = request.getParameter("title");
        System.out.println("Parâmetro recebido no formulário: " + movieTitle);

        if (movieTitle == null || movieTitle.trim().isEmpty()) {
            movieTitle = "faderisa";
        } else {
            movieTitle = movieTitle.replace(" ", "&");
        }

        System.out.println("Título do filme: " + movieTitle);

        // Configurando o cliente da API TMDB
        try (TmdbApiClient client = new TmdbApiClient()){

            // Configurando os parâmetros para a busca
            String endpoint = "/search/movie";
            Map<String, String> params = new HashMap<>();
            params.put("query", movieTitle);
            params.put("language", "pt-BR");

            // Fazendo a requisição à API
            JSONObject responseJson = client.get(endpoint, params);

            //System.out.println("Resposta da API: " + responseJson.toString(2));

            if (responseJson.has("results") && responseJson.getJSONArray("results").length() > 0) {

                // Pegando todos os filmes encontrados
                JSONArray results = responseJson.getJSONArray("results");

                // Convertendo o JSONArray para uma lista de Movie
                List<Filme> movies = new ArrayList<>();
                for (int i = 0; i < results.length(); i++) {
                    JSONObject movieObject = results.getJSONObject(i);
                    String title = movieObject.optString("title", "Título indisponível"); // Define título padrão

                    String posterPath = movieObject.optString("poster_path"); // Caminho vazio caso não exista imagem

                    // Exibir logs para cada filme
                    System.out.println("Título: " + movieTitle);
                    System.out.println("Poster: " + posterPath);

                    Filme filme = new Filme(
                            title,
                            posterPath.isEmpty() ? "/resources/front-end/pesquisa/image/default-poster.jpg" : "https://image.tmdb.org/t/p/w500" + posterPath // Imagem padrão caso não haja poster
                    );
                    filme.setId(movieObject.optInt("id", 0)); // ID padrão (0) caso não exista
                    movies.add(filme);
                }

                // Passando a lista de filmes encontrados
                request.setAttribute("movies", movies);
            } else {
                request.setAttribute("error", "Nenhum filme encontrado com o título: " + movieTitle);
            }

            // Encaminhando para o JSP
            request.getRequestDispatcher("/resources/front-end/pesquisa/index.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}