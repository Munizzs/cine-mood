package br.com.project.cineMood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class  ListaFilmeDao {

    public void createListaFilme(ListaFilme lista)throws SQLException {

        String SQL = "INSERT INTO lista_filmes (id_usuario, id_filme, status, avaliacao, data_adicao) VALUES (?,?,?,?,?)";

        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection(createFilme)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, lista.getId_usuario());
            preparedStatement.setInt(2, lista.getId_filme());
            preparedStatement.setString(3, lista.getStatus());
            preparedStatement.setInt(4, lista.getAvaliacao());
            preparedStatement.setString(5, lista.getData_adicao());

            preparedStatement.execute();
            System.out.println("success in insert lista_filmes");

            conn.close();

        } catch (Exception e) {

            System.out.println("fail in database connection"+e.getMessage());

        }
    }
    public List<ListaFilme> findAlllista_filmes() {
        String SQL = "SELECT * FROM lista_filmes";
        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection (to list)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<ListaFilme> lista_filmes = new ArrayList<>();
            while(resultSet.next()) {
                int id_lista = resultSet.getInt("id_lista");
                int id_usuario = resultSet.getInt("id_usuario");
                int id_filme = resultSet.getInt("id_filme");
                String status = resultSet.getString("Status");
                int avaliação = resultSet.getInt("avaliacao");
                String data_adição = resultSet.getString("data_adicao");

                ListaFilme lista_filme= new ListaFilme(id_lista,id_usuario, id_filme, status, avaliação, data_adição);
                lista_filmes.add(lista_filme);
            }

            System.out.println("Success in select * lista_filmes");
            conn.close();

            return lista_filmes;
        }catch (Exception e) {
            System.out.println("fail in database connection"+e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deleteListaFilmeById(int id) {
        String SQL = "DELETE FROM lista_filmes WHERE id_lista = ?";
        try{
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("Sucesso na conexão (Deletar Lista)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            System.out.println("Sucesso em remover o id: "+id);
            conn.close();

        }catch (Exception e) {
            System.out.println("Erro ao remover: "+e.getMessage());
        }
    }

    public void updateListaFilme(ListaFilme lista) {
        String SQL = "UPDATE lista_filmes SET id_usuario = ?, id_filme = ?, status = ?, avaliacao = ?, data_adicao = ? WHERE id_lista = ?";

        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("Sucesso em conectar com o banco de dados");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, lista.getId_usuario());
            preparedStatement.setInt(2, lista.getId_filme());
            preparedStatement.setString(3, lista.getStatus());
            preparedStatement.setInt(4, lista.getAvaliacao());
            preparedStatement.setString(5, lista.getData_adicao());
            preparedStatement.setInt(6, lista.getId_lista());
            preparedStatement.execute();

            System.out.println("Atualizado com sucesso");

            conn.close();

        } catch (Exception e) {

            System.out.println("Falha na conexão da database");
            System.out.println("Erro: " + e.getMessage());

        }
    }
}

