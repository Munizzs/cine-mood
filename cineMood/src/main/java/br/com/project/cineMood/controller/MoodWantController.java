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

@WebServlet("/mood")
public class MoodWantController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/resources/front-end/mood/moodWant.jsp").forward(req, resp);
    }

}
