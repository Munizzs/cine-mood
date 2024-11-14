package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.Filme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InitDao {
    private static String url = "jdbc:postgresql://localhost:5432/CineFilmes";
    //private static String url = "jdbc:postgresql://3.15.6.30:5432/CineFilmes";
    private static String user = "cineGodness";
    private static String pass = "adaoeva11";
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
            conex.close();
            return conex;
        }
    }

    public void  criarBases() {// Fazer uma condição onde se existir o banco de dados e suas tabela não faz nada, mas caso não exista criar com o comando sql
        try {
            this.sql = "CREATE database Car;";
            pst = conex.prepareStatement(this.sql);
            pst.execute();

        } catch (Exception e) {
             System.out.println("fail in database connection:" + e.getMessage());

        }
    }

    public void fecharConne()throws SQLException {
        try {
            conex.close();
        } catch (SQLException e) {
            System.out.println("Failure to close the connection:" + e.getMessage());
        }

    }
}