package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.Favorito;
import br.com.project.cineMood.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static br.com.project.cineMood.dao.InitDao.getConnection;

public class FavoritoDao {

    public void createFavorito(Favorito favorito) throws SQLException {
        String SQL = "INSERT INTO favorito (id_usuario, id_filme, status, avaliacao, genero) VALUES (?,?,?,?,?)";

        try {
            InitDao conex = new InitDao();
            Connection conn = getConnection();
            System.out.println("Success in database connection(createFavorito)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, favorito.getIdUsuario());
            preparedStatement.setString(2, favorito.getIdFilme());  // Assuming id_filme is a String
            preparedStatement.setObject(3, favorito.getStatus().name(), java.sql.Types.OTHER);
            preparedStatement.setInt(4, favorito.getAvaliacao());
            preparedStatement.setString(5, favorito.getGenero());

            preparedStatement.execute();
            System.out.println("Success in insert favorito");

            conn.close();
        } catch (Exception e) {
            System.out.println("Falha na criação: " + e.getMessage());
        }
    }

    public List<Favorito> findAllFavorito() {
        String SQL = "SELECT * FROM favorito";
        try {
            InitDao conex = new InitDao();
            Connection conn = getConnection();
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

                Status status = Status.valueOf(statusStr);

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
            Connection conn = getConnection();
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

    public void updateFavorito(Favorito favorito) throws SQLException {
        String SQL = "UPDATE favorito SET status = ?, avaliacao = ? WHERE id_favorito = ?";

        try (Connection conn = new InitDao().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {

            preparedStatement.setObject(1, favorito.getStatus().name(), java.sql.Types.OTHER);
            preparedStatement.setInt(2, favorito.getAvaliacao());
            preparedStatement.setInt(3, favorito.getIdFavorito());

            preparedStatement.executeUpdate();
            System.out.println("Favorito atualizado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar favorito: " + e.getMessage());
            throw e;
        }
    }

    public Favorito getFavoritoById(int id) throws SQLException {
        String SQL = "SELECT * FROM favorito WHERE id_favorito = ?";
        try {
            InitDao conex = new InitDao();
            Connection conn = getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Favorito favorito = new Favorito();
                    favorito.setIdFavorito(rs.getInt("id_favorito"));
                    favorito.setIdUsuario(rs.getInt("id_usuario"));
                    favorito.setIdFilme(rs.getString("id_filme"));
                    favorito.setStatus(Status.valueOf(rs.getString("status")));
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

    public List<Favorito> findFavoritosByUserId(int userId) {
        String SQL = "SELECT * FROM favorito WHERE id_usuario = ?";
        try {
            InitDao conex = new InitDao();
            Connection conn = getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Favorito> favoritos = new ArrayList<>();
            while (resultSet.next()) {
                int idFavorito = resultSet.getInt("id_favorito");
                String idFilme = resultSet.getString("id_filme");
                String statusStr = resultSet.getString("status");
                int avaliacao = resultSet.getInt("avaliacao");
                String genero = resultSet.getString("genero");

                Favorito favorito = new Favorito(
                        idFavorito,
                        userId,
                        Status.valueOf(statusStr),
                        avaliacao,
                        idFilme,
                        genero
                );
                favoritos.add(favorito);
            }

            conn.close();
            return favoritos;
        } catch (SQLException e) {
            System.out.println("Error fetching favoritos by user: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public List<String> findFavoriteMovieIdsByUserId(int userId) throws SQLException {
        String sql = "SELECT id_filme FROM favorito WHERE id_usuario = ?";
        List<String> movieIds = new ArrayList<>();

        try {
            InitDao conex = new InitDao();
            Connection conn = getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                movieIds.add(rs.getString("id_filme"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movieIds;
    }

    public List<Favorito> findFavoritosByUserIdAndStatus(int userId, Status status) throws SQLException {
        String sql = "SELECT * FROM favorito WHERE id_usuario = ? AND status = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);
            preparedStatement.setObject(2, status, java.sql.Types.OTHER); // Envia como ENUM

            try (ResultSet rs = preparedStatement.executeQuery()) {
                List<Favorito> favoritos = new ArrayList<>();
                while (rs.next()) {
                    Favorito favorito = new Favorito();
                    favorito.setIdFavorito(rs.getInt("id_favorito"));
                    favorito.setIdUsuario(rs.getInt("id_usuario"));
                    favorito.setIdFilme(rs.getString("id_filme"));
                    favorito.setStatus((Status) rs.getObject("status")); // Recupera diretamente o ENUM
                    favoritos.add(favorito);
                }
                return favoritos;
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new RuntimeException("Erro ao buscar favoritos", e);
        }
    }


}
