package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.Favorito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FavoritoDao {

    public void createFavorito(Favorito favorito) throws SQLException {
        String SQL = "INSERT INTO favorito (id_usuario, id_filme, status, avaliacao, genero) VALUES (?,?,?,?,?)";

        try {
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("Success in database connection(createFavorito)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, favorito.getIdUsuario());
            preparedStatement.setString(2, favorito.getIdFilme());  // Assuming id_filme is a String
            preparedStatement.setString(3, favorito.getStatus().name());  // Using enum's name
            preparedStatement.setInt(4, favorito.getAvaliacao());
            preparedStatement.setString(5, favorito.getGenero());

            preparedStatement.execute();
            System.out.println("Success in insert favorito");

            conn.close();
        } catch (Exception e) {
            System.out.println("Fail in database connection: " + e.getMessage());
        }
    }

    public List<Favorito> findAllFavorito() {
        String SQL = "SELECT * FROM favorito";
        try {
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("Success in database connection (to list)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Favorito> favoritos = new ArrayList<>();
            while (resultSet.next()) {
                int id_favorito = resultSet.getInt("id_favorito");
                int id_usuario = resultSet.getInt("id_usuario");
                String id_filme = resultSet.getString("id_filme");
                String statusStr = resultSet.getString("status");
                int avaliacao = resultSet.getInt("avaliacao");
                String genero = resultSet.getString("genero");

                Favorito.Status status = Favorito.Status.valueOf(statusStr);

                Favorito favorito = new Favorito(id_favorito, id_usuario, status, avaliacao, id_filme, genero);
                favoritos.add(favorito);
            }

            System.out.println("Success in select * Favorito");
            conn.close();

            return favoritos;
        } catch (Exception e) {
            System.out.println("Fail in database connection: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deleteFavoritoById(int id) {
        String SQL = "DELETE FROM favorito WHERE id_favorito = ?";
        try {
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("Success in connection (Delete Favorito)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            System.out.println("Success in removing the id: " + id);
            conn.close();
        } catch (Exception e) {
            System.out.println("Error removing: " + e.getMessage());
        }
    }

    public void updateFavorito(Favorito favorito) {
        String SQL = "UPDATE favorito SET id_usuario = ?, id_filme = ?, status = ?, avaliacao = ?, genero = ? WHERE id_favorito = ?";

        try {
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("Success in connecting to the database");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, favorito.getIdUsuario());
            preparedStatement.setString(2, favorito.getIdFilme());
            preparedStatement.setString(3, favorito.getStatus().name());
            preparedStatement.setInt(4, favorito.getAvaliacao());
            preparedStatement.setString(5, favorito.getGenero());
            preparedStatement.setInt(6, favorito.getIdFavorito());
            preparedStatement.execute();

            System.out.println("Successfully updated favorito");

            conn.close();
        } catch (Exception e) {
            System.out.println("Error in database connection");
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Favorito getFavoritoById(int id) throws SQLException {
        String SQL = "SELECT * FROM favoritos WHERE id = ?";
        try {
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Favorito favorito = new Favorito();
                    favorito.setIdFavorito(rs.getInt("id"));
                    favorito.setIdUsuario(rs.getInt("id_usuario"));
                    favorito.setIdFilme(rs.getString("id_filme"));
                    favorito.setStatus(Favorito.Status.valueOf(rs.getString("status").toUpperCase()));
                    favorito.setAvaliacao(rs.getInt("avaliacao"));
                    favorito.setGenero(rs.getString("genero"));
                    return favorito;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
