package br.com.project.cineMood.controller;

import br.com.project.cineMood.config.Config;
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

@WebServlet("/user/searchMovie")
public class MovieSearchController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet iniciado - recebendo requisição");

        String movieTitle = request.getParameter("title");
        System.out.println("Título do filme: " + movieTitle);

        String apiKey = Config.getApiKey();
        if (apiKey == null) {
            System.out.println("Erro: A chave da API não foi encontrada.");
            response.getWriter().write("Erro: A chave da API não foi encontrada.");
            return;
        }

        String apiUrl = "http://www.omdbapi.com/?t=" + movieTitle + "&apikey=" + apiKey;
        System.out.println("URL da API: " + apiUrl);

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder jsonResponse = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            jsonResponse.append(line);
        }
        reader.close();

        JSONObject movieJson = new JSONObject(jsonResponse.toString());

        System.out.println("Resposta da API: " + jsonResponse.toString());


        // Define o JSONObject como um atributo para o JSP acessar
        request.setAttribute("movie", movieJson);

        // Configurando os atributos para o JSP com base nos dados do JSON
        request.setAttribute("title", movieJson.optString("Title"));
        request.setAttribute("year", movieJson.optString("Year"));
        request.setAttribute("rated", movieJson.optString("Rated"));
        request.setAttribute("released", movieJson.optString("Released"));
        request.setAttribute("runtime", movieJson.optString("Runtime"));
        request.setAttribute("genre", movieJson.optString("Genre"));
        request.setAttribute("director", movieJson.optString("Director"));
        request.setAttribute("writer", movieJson.optString("Writer"));
        request.setAttribute("actors", movieJson.optString("Actors"));
        request.setAttribute("plot", movieJson.optString("Plot"));
        request.setAttribute("language", movieJson.optString("Language"));
        request.setAttribute("country", movieJson.optString("Country"));
        request.setAttribute("awards", movieJson.optString("Awards"));
        request.setAttribute("poster", movieJson.optString("Poster"));
        request.setAttribute("imdbRating", movieJson.optString("imdbRating"));
        request.setAttribute("boxOffice", movieJson.optString("BoxOffice"));

        request.getRequestDispatcher("/resources/teste/movie.jsp").forward(request, response);
    }
}
