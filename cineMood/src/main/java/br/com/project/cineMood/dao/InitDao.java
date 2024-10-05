package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.Filme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitDao {
    private static String url = "jdbc:postgresql://localhost:5432/CineFilmes";
    private static String user = "cineGodness";
    private static String pass = "adaoeva";
    private static Connection conex = null;
    private PreparedStatement pst;
    private String sql = null;

    public static Connection getConnection() throws SQLException{
        try {
            conex = DriverManager.getConnection(url, user, pass);
            System.out.println("success in database connection");
            return conex;

        } catch (SQLException e) {
            System.out.println("fail in database connection:" + e.getMessage());
            return null;
        }
    }

    public void  criarBases() {
        try {
            this.sql = "CREATE database Car;";
            pst = conex.prepareStatement(this.sql);
            pst.execute();

        } catch (Exception e) {
             System.out.println("fail in database connection" + e.getMessage());

        }
    }

}