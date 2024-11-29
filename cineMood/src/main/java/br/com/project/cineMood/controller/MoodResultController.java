package br.com.project.cineMood.controller;

import br.com.project.cineMood.config.TmdbApiClient;
import br.com.project.cineMood.dao.EmocaoDao;
import br.com.project.cineMood.model.Emocao;
import br.com.project.cineMood.model.Filme;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/mood/result")
public class MoodResultController extends HttpServlet {
    private static final Log log = LogFactory.getLog(MoodResultController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idEmocao = req.getParameter("idEmocao");

        if (idEmocao != null) {
            Emocao emocao = pesquisaID(idEmocao);
            if (emocao != null) {
                List<Integer> generoList = separarStringParaLista(emocao.getGenre(), ",");
                List<Filme> recommendedFilmes = fetchMoviesFromApi(generoList,"/discover/movie");
                System.out.println(generoList);

                List<Integer> seleccaoids=selctById(recommendedFilmes);
                List<Filme> filmesSelecionados = new ArrayList<>();
                for (int id : seleccaoids) {
                    filmesSelecionados.add(recommendedFilmes.get(id));
                }
                req.setAttribute("recommendedFilmes", filmesSelecionados);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Emoção não encontrada");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da emoção não fornecido");
        }


        req.getRequestDispatcher("/resources/front-end/mood/moodResult.jsp").forward(req, resp);
    }

    public Emocao pesquisaID(String id) {
        EmocaoDao dao = new EmocaoDao();
        Emocao emocoes = dao.findEmocaoById(id);
        return emocoes;
    }
    private List<Integer> separarStringParaLista(String input, String separador) {
        return Arrays.stream(input.split(separador))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
    public List<Filme> fetchMoviesFromApi(List<Integer> genres, String endpoint) {
        List<Filme> filmes = new ArrayList<>();
        String baseImageUrl = "https://image.tmdb.org/t/p/w500";

        try (TmdbApiClient client = new TmdbApiClient()) {
            Map<String, String> params = new HashMap<>();
            params.put("api_key", "SUA_CHAVE_API");
            params.put("language", "pt-BR");
            params.put("sort_by", "popularity.desc");
            params.put("include_video", "fslse");
            params.put("include_adult", "true");

            // Concatena os gêneros e codifica
            String genresString = genres.stream()
                    .map(Object::toString) // Converte cada elemento para String
                    .collect(Collectors.joining("|"));
            genresString = URLEncoder.encode(genresString, StandardCharsets.UTF_8.toString());
            System.out.println(genresString);
            params.put("with_genres", genresString);

            // Chama o cliente para a API
            for(int j = 0; j < 10; j++) {
                //para retornar as primeiras 10 paginas da api
                params.put("page", ""+j);
            JSONObject response = client.get(endpoint, params);
            //System.out.println("Resposta da API: " + response.toString(2));
            if (response.has("results") && response.getJSONArray("results").length() > 0) {
                JSONArray results = response.getJSONArray("results");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject movieJson = results.getJSONObject(i);
                    Filme filme = new Filme(
                            movieJson.getString("title"),
                            baseImageUrl + movieJson.getString("poster_path"),
                            movieJson.getInt("id")
                    );
                    filmes.add(filme);
                }
            }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return filmes;
    }
    public int randFilm(List<Filme> filmes) {
        Random rand  = new Random();
        return rand.nextInt(0,filmes.size());
    }
    public List<Integer> selctById(List<Filme> filmes) {
        List<Integer> randList =  new ArrayList<>();
        while (randList.size() < Math.min(20, filmes.size())) {
            int addid = randFilm(filmes);

            // Verifica se o valor já existe na lista antes de adicionar
            if (!randList.contains(addid)) {
                randList.add(addid);
            }
        }
        return randList;
    }

}
