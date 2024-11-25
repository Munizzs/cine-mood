package br.com.project.cineMood.config;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            TmdbApiClient client = new TmdbApiClient();

            // Definir o endpoint e os parâmetros
            String endpoint = "/movie/popular";
            Map<String, String> params = new HashMap<>();
            params.put("language", "pt-BR"); // Idioma dos resultados
            params.put("page", "1"); // Página dos resultados

            // Chamada à API
            JSONObject response = client.get(endpoint, params);

            // Processar a resposta
            System.out.println("Resposta da API: " + response.toString(2));

            // Fechar o cliente após o uso
            client.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
