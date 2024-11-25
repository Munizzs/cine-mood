package br.com.project.cineMood.controller;


import br.com.project.cineMood.config.TmdbApiClient;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/searchMovie")
public class MovieSearchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet iniciado - recebendo requisição");



        String movieTitle = request.getParameter("title");


        movieTitle = movieTitle.replace(" ", "&");


        System.out.println("Título do filme: " + movieTitle);

        // Configurando o cliente da API TMDB
        try (TmdbApiClient client = new TmdbApiClient()){



            // Configurando os parâmetros para a busca
            String endpoint = "/search/movie";
            Map<String, String> params = new HashMap<>();
            params.put("query", movieTitle);
            params.put("language", "pt-BR"); // Definindo o idioma dos resultados

            // Fazendo a requisição à API
            JSONObject responseJson = client.get(endpoint, params);

            System.out.println("Resposta da API: " + responseJson.toString(2));

            // Configurando os atributos para o JSP com base nos dados do JSON
            if (responseJson.has("results") && responseJson.getJSONArray("results").length() > 0) {
                JSONObject movieJson = responseJson.getJSONArray("results").getJSONObject(0); // Obtendo o primeiro resultado

                request.setAttribute("movie", movieJson);
                request.setAttribute("title", movieJson.optString("title")); // Título em exibição
                request.setAttribute("originalTitle", movieJson.optString("original_title")); // Título original
                request.setAttribute("overview", movieJson.optString("overview")); // Sinopse
                request.setAttribute("releaseDate", movieJson.optString("release_date")); // Data de lançamento
                request.setAttribute("posterPath", "https://image.tmdb.org/t/p/w500" + movieJson.optString("poster_path")); // URL do poster
                request.setAttribute("backdropPath", "https://image.tmdb.org/t/p/w500" + movieJson.optString("backdrop_path")); // URL do backdrop
                request.setAttribute("originalLanguage", movieJson.optString("original_language")); // Idioma original
                request.setAttribute("voteAverage", movieJson.optDouble("vote_average")); // Nota média
                request.setAttribute("voteCount", movieJson.optInt("vote_count")); // Número de votos
                request.setAttribute("popularity", movieJson.optDouble("popularity")); // Popularidade
                request.setAttribute("adult", movieJson.optBoolean("adult") ? "Sim" : "Não"); // Indicação se é conteúdo adulto

                // Encaminhando para o JSP
                request.getRequestDispatcher("/resources/teste/movie.jsp").forward(request, response);

            } else {
                request.setAttribute("error", "Nenhum filme encontrado com o título: " + movieTitle);
                request.getRequestDispatcher("/resources/teste/error.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Erro ao buscar o filme: " + e.getMessage());
        }
    }
}
