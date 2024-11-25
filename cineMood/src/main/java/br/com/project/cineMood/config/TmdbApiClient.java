package br.com.project.cineMood.config;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;

import org.json.JSONObject;

public class TmdbApiClient implements AutoCloseable {

    private static final String API_KEY = "fc6e4d8ec00cfd0e100b0fcf72cae641";
    private static final String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmYzZlNGQ4ZWMwMGNmZDBlMTAwYjBmY2Y3MmNhZTY0MSIsIm5iZiI6MTczMTk4MzY1OC41Mjk5MTM3LCJzdWIiOiI2NmU0YTkwZjYwNzM1ZTk5MGQzYWM4ZTkiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.JWyu34eS57aqk_8YSsoLoUAEg1dkzk4w4LDv6dXedG0";
    private static final String BASE_URL = "https://api.themoviedb.org/3";

    private CloseableHttpClient httpClient;

    public TmdbApiClient() {
        this.httpClient = HttpClients.custom()
                .setMaxConnTotal(100)
                .setMaxConnPerRoute(20)
                .build();
    }

    public JSONObject get(String endpoint, Map<String, String> params) throws Exception {
        StringBuilder urlBuilder = new StringBuilder(BASE_URL).append(endpoint).append("?");

        // Adiciona os parâmetros da query
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }

        // Adiciona a API Key
        urlBuilder.append("api_key=").append(API_KEY);

        HttpGet request = new HttpGet(urlBuilder.toString());

        // Adiciona o token de acesso no cabeçalho
        request.addHeader("Authorization", "Bearer " + ACCESS_TOKEN);
        request.addHeader("Content-Type", "application/json;charset=utf-8");

        HttpResponse response = httpClient.execute(request);

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String linha = "";
        while ((linha = rd.readLine()) != null) {
            result.append(linha);
        }

        return new JSONObject(result.toString());
    }

    @Override
    public void close() throws Exception {
        httpClient.close();
    }
}
