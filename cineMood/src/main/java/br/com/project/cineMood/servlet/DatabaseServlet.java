package br.com.project.cineMood.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import br.com.project.cineMood.dao.InitDao;


@WebServlet("/myservlet")
public class DatabaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection connection = InitDao.getConnection()) {
            if (connection != null) {
                out.println("<h1>Conexão com PostgreSQL bem-sucedida!</h1>");
            } else {
                out.println("<h1>Falha na conexão com PostgreSQL.</h1>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>Erro: " + e.getMessage() + "</h1>");
        }
    }

}
