package br.com.project.cineMood.controller.recomendacao;

import br.com.project.cineMood.dao.ListaFilmeDao;
import br.com.project.cineMood.dao.RecomendacaoDao;
import br.com.project.cineMood.model.ListaFilme;
import br.com.project.cineMood.model.Recomendacao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

    @WebServlet("/find-all-recomendacao")
    public class ListAllRecomendacaoController extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Recomendacao> recomendacoes= new RecomendacaoDao().findAllRecomendacao();
            req.setAttribute("recomendacoes",recomendacoes);
            req.getRequestDispatcher("/resources/recomendacaoTeste/index.jsp").forward(req,resp);
        }
    }

