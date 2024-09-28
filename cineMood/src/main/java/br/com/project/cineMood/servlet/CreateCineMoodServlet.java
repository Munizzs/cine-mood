package br.com.project.cineMood.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create-cineMood")
public class CreateCineMoodServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nomeFilme = request.getParameter("nome-filme");

        System.out.println(nomeFilme);

        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
}
