package br.com.project.cineMood.controller;

import br.com.project.cineMood.config.Config;
import br.com.project.cineMood.model.Filme;
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

@WebServlet("/user/favorito-filme")
public class Favorito extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String apiKey = Config.getApiKey();
        if (apiKey == null) {
            System.out.println("Erro: A chave da API não foi encontrada.");
            resp.getWriter().write("Erro: A chave da API não foi encontrada.");
            return;
        }

        // Listas de títulos para buscar na API
        String[] lancamentoTitulo = {"Barbie", "Oppenheimer", "Deadpool 2", "Alien: Romulus", "Inside out 2", "Five Nights at Freddy's", "Transformers One", "Avatar"};
        List<Filme> filmes = fetchMoviesFromApi(lancamentoTitulo, apiKey);

        String[] recommendedTitles = {"Inception", "Interstellar", "The Dark Knight", "Joker", "Deadpool 2", "Inside out 2"};
        List<Filme> recommendedFilmes = fetchMoviesFromApi(recommendedTitles, apiKey);

        // Dividindo os filmes em chunks de 4
        List<List<Filme>> moviesChunks = partitionMovies(filmes, 4);
        List<List<Filme>> recommendedMoviesChunks = partitionMovies(recommendedFilmes, 4);

        // Debug para verificar as listas e os chunks
        System.out.println("Lançamentos: " + filmes.size() + " filmes divididos em " + moviesChunks.size() + " chunks.");
        System.out.println("Recomendados: " + recommendedFilmes.size() + " filmes divididos em " + recommendedMoviesChunks.size() + " chunks.");

        // Envia os chunks de filmes para o JSP
        req.setAttribute("moviesChunks", moviesChunks);
        req.setAttribute("recommendedMoviesChunks", recommendedMoviesChunks);
        req.getRequestDispatcher("/resources/front-end/favorito/index.jsp").forward(req, resp);
    }

    // Método para buscar detalhes dos filmes da API OMDb.

    private List<Filme> fetchMoviesFromApi(String[] movieTitles, String apiKey) {
        List<Filme> filmes = new ArrayList<>();
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
                    Filme filme = new Filme(
                            jsonResponse.getString("Title"),
                            jsonResponse.getString("Poster")
                    );
                    filmes.add(filme);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filmes;
    }


    // Método para dividir a lista de filmes em chunks menores.

    private List<List<Filme>> partitionMovies(List<Filme> filmes, int chunkSize) {
        List<List<Filme>> chunks = new ArrayList<>();
        for (int i = 0; i < filmes.size(); i += chunkSize) {
            chunks.add(filmes.subList(i, Math.min(i + chunkSize, filmes.size())));
        }
        return chunks;
    }
}