package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class FilmeDao {
    public void createFilme(Filme filme)throws SQLException {

        String SQL = "INSERT INTO filmes (titulo, tipo) VALUES (?,?)";

        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection(createFilme)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1, filme.getTitulo());
            preparedStatement.setString(2, filme.getTipo());

            preparedStatement.execute();
            System.out.println("success in insert filme");

            conn.close();

        } catch (Exception e) {

            System.out.println("fail in database connection"+e.getMessage());

        }
    }
    public List<Filme> findAllFilm() {
        String SQL = "SELECT * FROM FILMEs";
        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection (to list)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Filme> films = new ArrayList<>();
            while(resultSet.next()) {
                String id = resultSet.getString("id_filme");
                String titulo = resultSet.getString("titulo");
                String tipo = resultSet.getString("tipo");
                Filme film= new Filme(id,titulo,tipo);
                films.add(film);
            }

            System.out.println("Success in select * film");
            conn.close();

            return films;
        }catch (Exception e) {
            System.out.println("fail in database connection"+e.getMessage());
            return Collections.emptyList();
        }

    }
    public void deleteFilmById(int id) {
        String SQL = "DELETE FROM FILMEs WHERE id_filme = ?";
        try{
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection(deleteFilm)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            System.out.println("Success in select * film: "+id);
            conn.close();

        }catch (Exception e) {
            System.out.println("Error delete film: "+e.getMessage());
        }
    }
}
