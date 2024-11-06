package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.Emocao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class EmocaoDao {

    public void createEmocao(Emocao emocao)throws SQLException {

        String SQL = "INSERT INTO emocoes (nome, descricao) VALUES (?,?)";

        try {
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1, emocao.getNome());
            preparedStatement.setString(2, emocao.getDescricao());

            preparedStatement.execute();
            System.out.println("Criada emoção com sucesso!");

            conn.close();

        } catch (Exception e) {

            System.out.println("Falhou na criação da conexão: "+e.getMessage());

        }
    }
    public List<Emocao> findAllEmocao() {
        String SQL = "SELECT * FROM emocoes";
        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection (to list)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Emocao> emocaoList = new ArrayList<>();

            while(resultSet.next()) {
                String id = resultSet.getString("id_emocao");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");

                Emocao emocao = new Emocao(id, nome, descricao);
                emocaoList.add(emocao);
            }

            System.out.println("Achou a emoção no bdd");
            conn.close();

            return emocaoList;
        }catch (Exception e) {
            System.out.println("Falhou na procura da emoção:"+e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deleteEmocaoById(int id) {
        String SQL = "DELETE FROM emocoes WHERE id_emocao = ?";
        try{
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("Sucesso na conexão (Deletar Emocao)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            System.out.println("Sucesso em remover o id: "+id);
            conn.close();

        }catch (Exception e) {
            System.out.println("Erro ao remover: "+e.getMessage());
        }
    }
}
