package br.com.project.cineMood.controller;

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

@WebServlet("/a")
public class NaoLogado extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lista para armazenar os filmes
        JSONArray movies = new JSONArray();

        // Títulos dos filmes para busca
        String[] movieTitles = {"Minions", "Avatar", "Inception", "Titanic", "Avengers", "Frozen", "Joker"};

        for (String title : movieTitles) {
            String apiUrl = "http://www.omdbapi.com/?t=" + title + "&apikey=60350e13";
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            JSONObject movieData = new JSONObject(sb.toString());
            System.out.println("Resposta da API: " + sb.toString());

            // Verifica se a resposta da API é válida
            if ("True".equalsIgnoreCase(movieData.getString("Response"))) {
                JSONObject movie = new JSONObject();
                movie.put("title", movieData.optString("Title", "Título indisponível"));
                movie.put("poster", movieData.optString("Poster", "Imagem não disponível"));
                movies.put(movie); // Adiciona o filme à lista
            }
        }

        // Define o atributo com a lista de filmes
        req.setAttribute("movies", movies);
        req.getRequestDispatcher("/resources/front-end/nao_logada/index.jsp").forward(req, resp);
    }
}