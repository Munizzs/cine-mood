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

@WebServlet("/user/historico-filme")
public class Historico extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String apiKey = Config.getApiKey();
        if (apiKey == null) {
            System.out.println("Erro: A chave da API não foi encontrada.");
            resp.getWriter().write("Erro: A chave da API não foi encontrada.");
            return;
        }

        // Listas de títulos para buscar na API
        String[] historicoTitulo = {"Barbie", "Oppenheimer", "Deadpool 2", "Alien: Romulus", "Inside out 2", "Five Nights at Freddy's", "Transformers One", "Avatar"};
        List<Filme> filmes = fetchMoviesFromApi(historicoTitulo, apiKey);

        // Calcular as emoções dos filmes
        JSONObject emotionCounts = calculateEmotionCounts(filmes);

        // Dividindo os filmes em chunks de 4
        List<List<Filme>> moviesChunks = partitionMovies(filmes, 4);

        // Debug para verificar as listas e os chunks
        System.out.println("Lançamentos: " + filmes.size() + " filmes divididos em " + moviesChunks.size() + " chunks.");

        // Envia os chunks de filmes para o JSP
        req.setAttribute("moviesChunks", moviesChunks);
        req.setAttribute("emotionCounts", emotionCounts);
        req.getRequestDispatcher("/resources/front-end/historico/index.jsp").forward(req, resp);
    }

    // Método para calcular as quantidades de filmes por emoção
    private JSONObject calculateEmotionCounts(List<Filme> filmes) {
        JSONObject emotionCounts = new JSONObject();
        int tristeza = 0, raiva = 0, alegria = 0, curiosidade = 0, amor = 0;

        for (Filme filme : filmes) {
            String emotion = filme.getEmotion();

            switch (emotion) {
                case "tristeza":
                    tristeza++;
                    break;
                case "raiva":
                    raiva++;
                    break;
                case "alegria":
                    alegria++;
                    break;
                case "curiosidade":
                    curiosidade++;
                    break;
                case "amor":
                    amor++;
                    break;
            }
        }

        // Adicionando as contagens ao JSON
        emotionCounts.put("tristeza", tristeza);
        emotionCounts.put("raiva", raiva);
        emotionCounts.put("alegria", alegria);
        emotionCounts.put("curiosidade", curiosidade);
        emotionCounts.put("amor", amor);

        return emotionCounts;
    }


    private String determineEmotion(Filme filme) {
        String genre = filme.getGenre().toLowerCase();
        String plot = filme.getPlot().toLowerCase();

        if (genre.contains("drama") || plot.contains("triste")) {
            return "tristeza";
        } else if (genre.contains("action") || plot.contains("raiva")) {
            return "raiva";
        } else if (genre.contains("comedy") || plot.contains("alegria")) {
            return "alegria";
        } else if (genre.contains("mystery") || plot.contains("curiosity")) {
            return "curiosidade";
        } else {
            return "amor";  // Assumindo amor por padrão
        }
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
                            jsonResponse.getString("Poster"),
                            jsonResponse.getString("Genre"),
                            jsonResponse.getString("Plot")
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
