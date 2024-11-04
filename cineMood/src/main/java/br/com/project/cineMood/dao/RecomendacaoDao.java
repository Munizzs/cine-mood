package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.ListaFilme;
import br.com.project.cineMood.model.Recomendacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecomendacaoDao {
    public void createRecomendacao(Recomendacao recomendacao)throws SQLException {

        String SQL = "INSERT INTO recomendacao(id_usuario, id_filme, id_emocao, data_adicao) VALUES (?,?,?,?)";

        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection(createRecomendacao)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, recomendacao.getId_usuario());
            preparedStatement.setInt(2, recomendacao.getId_filme());
            preparedStatement.setInt(3, recomendacao.getId_emocao());
            preparedStatement.setString(4, recomendacao.getData_adicao());

            preparedStatement.execute();
            System.out.println("success in insert recomendacao");

            conn.close();

        } catch (Exception e) {

            System.out.println("fail in database connection"+e.getMessage());

        }
    }
    public List<Recomendacao> findAllRecomendacao() {
        String SQL = "SELECT * FROM recomendacao";
        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection (to list)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Recomendacao> recomendacoes = new ArrayList<>();
            while(resultSet.next()) {
                int id_usuario = resultSet.getInt("id_usuario");
                int id_filme = resultSet.getInt("id_filme");
                int id_emocao = resultSet.getInt("id_emocao");
                String data_adicao = resultSet.getString("data_adicao");


                Recomendacao recomendacao= new Recomendacao(id_usuario, id_filme, id_emocao, data_adicao);
                recomendacoes.add(recomendacao);
            }

            System.out.println("Success in select * recomendacao");
            conn.close();

            return recomendacoes;
        }catch (Exception e) {
            System.out.println("fail in database connection"+e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deleteRecomendacaoById(int id) {
        String SQL = "DELETE FROM recomendacao WHERE id_recomendacao = ?";
        try{
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("Sucesso na conexão (Deletar Recomendacao)");

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
