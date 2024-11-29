package br.com.project.cineMood.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import br.com.project.cineMood.dao.EmocaoDao;
import br.com.project.cineMood.model.Emocao;

@WebServlet("/mood/select")
public class MoodSelectController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String moodW = req.getParameter("moodW");
        List<Emocao> emocao = null;
        if (moodW.equals("0")){
            emocao=  new EmocaoDao().findAllEmocao();
        }if (moodW.equals("1")){
            emocao=  new EmocaoDao().findAllEmocao_();
        }

        System.out.println(emocao);
        req.setAttribute("emocoes",emocao);
        req.setAttribute("moodW",moodW);
        System.out.println(moodW);
        req.getRequestDispatcher("/resources/front-end/mood/mood.jsp").forward(req, resp);
    }

}
