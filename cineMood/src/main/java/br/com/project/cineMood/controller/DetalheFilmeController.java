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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet("/user/detalhes")
public class DetalheFilmeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Captura o parâmetro 'id' da URL
        String movieIdParam = request.getParameter("id");
        if (movieIdParam == null || movieIdParam.isEmpty()) {
            request.setAttribute("error", "ID do filme não fornecido.");
            request.getRequestDispatcher("/resources/front-end/detalhe/index.jsp").forward(request, response);
            return;
        }

        try {
            int movieId = Integer.parseInt(movieIdParam);

            // Cliente da API TMDB
            try (TmdbApiClient client = new TmdbApiClient()) {
                String endpoint = "/movie/" + movieId;
                Map<String, String> params = new HashMap<>();
                params.put("language", "pt-BR");

                JSONObject responseJson = client.get(endpoint, params);

                if (responseJson != null) {
                    // Mapeia os dados do JSON para o modelo Filme
                    Filme filme = new Filme(
                            responseJson.getString("title"),
                            "https://image.tmdb.org/t/p/w500" + responseJson.optString("poster_path"),
                            responseJson.getString("overview")
                    );

                    filme.setBackdrop_path("https://image.tmdb.org/t/p/original" + responseJson.optString("backdrop_path"));
                    filme.setOriginalTitle(responseJson.optString("original_title"));
                    filme.setVoteAverage(responseJson.optDouble("vote_average"));
                    filme.setReleaseDate(responseJson.optString("release_date"));
                    filme.setId(responseJson.optInt("id"));

                    // Processa os gêneros
                    JSONArray genresArray = responseJson.optJSONArray("genres");
                    if (genresArray != null) {
                        String genres = IntStream.range(0, genresArray.length())
                                .mapToObj(i -> genresArray.getJSONObject(i).optString("name"))
                                .collect(Collectors.joining(", "));
                        filme.setGenres(genres);
                    }

                    request.setAttribute("filme", filme);

                    // Obtém os provedores de streaming
                    String watchProvidersEndpoint = "/movie/" + movieId + "/watch/providers";
                    JSONObject watchProvidersResponse = client.get(watchProvidersEndpoint, new HashMap<>());

                    if (watchProvidersResponse != null) {
                        JSONObject results = watchProvidersResponse.optJSONObject("results");
                        if (results != null) {
                            JSONObject brazilProviders = results.optJSONObject("BR"); // Exemplo: dados do Brasil
                            if (brazilProviders != null) {
                                JSONObject flatrate = brazilProviders.optJSONArray("flatrate") != null ? brazilProviders.optJSONArray("flatrate").optJSONObject(0) : null;
                                if (flatrate != null) {
                                    String providerName = flatrate.optString("provider_name");
                                    String providerLogo = "https://image.tmdb.org/t/p/w92" + flatrate.optString("logo_path");

                                    // Adicione os dados ao request
                                    request.setAttribute("providerName", providerName);
                                    request.setAttribute("providerLogo", providerLogo);
                                }
                            }
                        }
                    }

                } else {
                    request.setAttribute("error", "Não foi possível obter os detalhes do filme.");
                }

                // Encaminha para a página JSP
                request.getRequestDispatcher("/resources/front-end/detalhe/index.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID do filme inválido.");
            request.getRequestDispatcher("/resources/front-end/detalhe/index.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
