package br.com.project.cineMood.controller;

import br.com.project.cineMood.config.Config;
import br.com.project.cineMood.config.TmdbApiClient;
import br.com.project.cineMood.model.Filme;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
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

@WebServlet("/a")
public class NaoLogado extends HttpServlet {
    public static void main(String[] args) {
        TmdbApiClient client = new TmdbApiClient();
        try {
            Map<String, String> params = new HashMap<>();
            // Adicione quaisquer parâmetros adicionais aqui
            JSONObject response = client.get("/movie/500", params); // Obtém detalhes do filme com ID 550

            System.out.println(response.toString(2));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Filme> filmes = fetchMoviesFromApi();
        List<Filme> recommendedFilmes = fetchMoviesFromApi();

        // Dividindo os filmes em chunks de 4
        List<List<Filme>> moviesChunks = partitionMovies(filmes, 4);
        List<List<Filme>> recommendedMoviesChunks = partitionMovies(recommendedFilmes, 4);

        // Debug para verificar as listas e os chunks
        System.out.println("Lançamentos: " + filmes.size() + " filmes divididos em " + moviesChunks.size() + " chunks.");
        System.out.println("Recomendados: " + recommendedFilmes.size() + " filmes divididos em " + recommendedMoviesChunks.size() + " chunks.");

        // Envia os chunks de filmes para o JSP
        req.setAttribute("moviesChunks", moviesChunks);
        req.setAttribute("recommendedMoviesChunks", recommendedMoviesChunks);
        req.getRequestDispatcher("/resources/front-end/nao_logada/index.jsp").forward(req, resp);
    }

    // Método para buscar detalhes dos filmes da API OMDb.

    private List<Filme> fetchMoviesFromApi() {
        List<Filme> filmes = new ArrayList<>();
        TmdbApiClient client = new TmdbApiClient();
        Map<String, String> params = new HashMap<>();
        try {
            // Adicione quaisquer parâmetros adicionais aqui
            // Conexão com a API
            JSONObject response = client.get("movie/popular", params); // Obtém lista de filmes

            if (response.getString("results").equals("True")) {
                Filme filme = new Filme(
                        response.getString("Title"),
                        response.getString("Poster")
                );
                filmes.add(filme);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
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
