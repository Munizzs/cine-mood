package br.com.project.cineMood.controller;

import br.com.project.cineMood.config.TmdbApiClient;
import br.com.project.cineMood.model.Filme;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;



@WebServlet("/a")
public class NaoLogado extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Filme> filmes = fetchMoviesFromApi("/movie/popular");
        List<Filme> recommendedFilmes = fetchMoviesFromApi("/movie/top_rated");

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

    private List<Filme> fetchMoviesFromApi(String endpoint) {
        List<Filme> filmes = new ArrayList<>();
        String baseImageUrl = "https://image.tmdb.org/t/p/w500";
        try (TmdbApiClient client = new TmdbApiClient()){

            // Configurando os parâmetros para a busca
            Map<String, String> params = new HashMap<>();
            params.put("api_key", "SUA_CHAVE_API");
            params.put("language", "pt-BR");
            // Obtém lista de filmes
            JSONObject response = client.get(endpoint, params);
            System.out.println("Resposta da API: " + response.toString(2));
            if (response.has("results") && response.getJSONArray("results").length() > 0) {
                JSONArray results = response.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject movieJson = results.getJSONObject(i);
                    Filme filme = new Filme(
                            movieJson.getString("title"),
                            baseImageUrl+movieJson.getString("poster_path")
                    );
                    filmes.add(filme);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            // Remova o bloco 'finally' vazio
        return filmes;
    }
    // Método para dividir a lista de filmes em chunks menores.

    private List<List<Filme>> partitionMovies(List<Filme> filmes, int chunkSize) {
        if (chunkSize <= 0) {
            throw new IllegalArgumentException("O tamanho do chunk deve ser maior que zero.");
        }
        List<List<Filme>> chunks = new ArrayList<>();
        for (int i = 0; i < filmes.size(); i += chunkSize) {
            chunks.add(filmes.subList(i, Math.min(i + chunkSize, filmes.size())));
        }
        return chunks;
    }
}
