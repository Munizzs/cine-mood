package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FilmeDao {
    public void createFilme(Filme filme)throws SQLException {

        String SQL = "INSERT INTO FILME (NAME) VALUES (?)";

        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1, filme.getTitulo());
            preparedStatement.execute();
            System.out.println("success in insert filme");

            conn.close();

        } catch (Exception e) {

            System.out.println("fail in database connection"+e.getMessage());

        }
    }
}
