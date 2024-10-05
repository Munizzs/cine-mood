package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.Filme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class FilmeDao {
    public void createFilme(Filme filme){

        String SQL = "INSERT INTO FILME (NAME) VALUES (?)";

        try {

            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432", "sa","sa");

            System.out.println("success in database connection");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, filme.getName());
            preparedStatement.execute();

            System.out.println("success in insert filme");

            connection.close();

        } catch (Exception e) {

            System.out.println("fail in database connection"+e.getMessage());

        }
    }
}
