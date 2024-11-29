package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.Emocao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EmocaoDao {

    // Método para criar uma nova emoção no banco de dados
    public void createEmocao(Emocao emocao) throws SQLException {

        String SQL = "INSERT INTO emocoes (nome, descricao) VALUES (?, ?)";

        try (Connection conn = new InitDao().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {

            preparedStatement.setString(1, emocao.getNome());
            preparedStatement.setString(2, emocao.getGenre());

            preparedStatement.execute();
            System.out.println("Criada emoção com sucesso!");

        } catch (Exception e) {
            System.out.println("Falhou na criação da conexão: " + e.getMessage());
        }
    }

    // Método para buscar todas as emoções do banco de dados
    public List<Emocao> findAllEmocao() {
        String SQL = "SELECT * FROM emocoes";
        List<Emocao> emocaoList = new ArrayList<>();

        try (Connection conn = new InitDao().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_emocao");
                String nome = resultSet.getString("nome");
                String genre = resultSet.getString("genre");
                String image = resultSet.getString("image");
                Emocao emocao = new Emocao(id, nome, genre,image);
                emocaoList.add(emocao);
            }

            System.out.println("Achou a emoção no bdd");

        } catch (Exception e) {
            System.out.println("Falhou na procura da emoção: " + e.getMessage());
            return Collections.emptyList();
        }

        return emocaoList;
    }

    // Método para deletar uma emoção pelo id
    public void deleteEmocaoById(int id) {
        String SQL = "DELETE FROM emocoes WHERE id_emocao = ?";

        try (Connection conn = new InitDao().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            System.out.println("Sucesso em remover o id: " + id);

        } catch (Exception e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }

    // Método para atualizar uma emoção no banco de dados
    public void updateEmocao(Emocao emocao) {
        String SQL = "UPDATE emocoes SET nome = ?, descricao = ? WHERE id_emocao = ?";

        try (Connection conn = new InitDao().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {

            preparedStatement.setString(1, emocao.getNome());
            preparedStatement.setString(2, emocao.getGenre());
            preparedStatement.setInt(3, emocao.getIdEmocao());
            preparedStatement.execute();

            System.out.println("Atualizado com sucesso");

        } catch (Exception e) {
            System.out.println("Falha na conexão da database");
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public Emocao findEmocaoById(String id) {
        String SQL = "SELECT * FROM emocoes WHERE id_emocao = ?";
        Emocao emocoes = null;

        try (Connection conn = new InitDao().getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {

            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idEmocao = resultSet.getInt("id_emocao");
                String nome = resultSet.getString("nome");
                String genres = resultSet.getString("genre"); // Corrigido o nome do campo
                String image = resultSet.getString("image");

                // Cria o objeto Emocao com os dados obtidos
                emocoes = new Emocao(idEmocao, nome, genres, image);

                System.out.println("Busca realizada com sucesso para o id: " + id);
            } else {
                System.out.println("Nenhum registro encontrado para o id: " + id);
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar emoção: " + e.getMessage());
        }

        // Retorna o objeto Emocao ou null se não encontrado
        return emocoes;
    }

}
