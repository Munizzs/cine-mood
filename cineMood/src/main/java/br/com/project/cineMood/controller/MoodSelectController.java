package br.com.project.cineMood.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import br.com.project.cineMood.dao.EmocaoDao;
import br.com.project.cineMood.model.Emocao;
import br.com.project.cineMood.model.Emocao;
import com.sun.org.apache.bcel.internal.generic.Select;

@WebServlet("/mood")
public class MoodSelectController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Emocao> emocao = new EmocaoDao().findAllEmocao();
        System.out.println(emocao);
        req.setAttribute("emocoes",emocao);
        req.getRequestDispatcher("/resources/front-end/mood/mood.jsp").forward(req, resp);
    }


    //public List<String> buscarFilmesPorGenero(String generos) {
    //    List<String> filmes = new ArrayList<>();
    //    try (TmdbApiClient client = new TmdbApiClient()) {
    //        Map<String, String> params = new HashMap<>();
    //        params.put("with_genres", generos);
    //        JSONObject response = client.get("/discover/movie", params);
    //        JSONArray results = response.getJSONArray("results");
//
    //        for (int i = 0; i < results.length(); i++) {
    //            JSONObject filme = results.getJSONObject(i);
    //            filmes.add(filme.getString("title"));
    //        }
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //    return filmes;
    //}
}
