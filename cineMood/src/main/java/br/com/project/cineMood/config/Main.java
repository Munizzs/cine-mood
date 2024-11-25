package br.com.project.cineMood.config;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class Main {
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
}
