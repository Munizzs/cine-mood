package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.ListaFilme;
import br.com.project.cineMood.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaFilmeDao {

    public void createListaFilme(ListaFilme lista)throws SQLException {

        String SQL = "INSERT INTO lista_filmes (id_lista,id_filme,avaliacao, data_adicao, status, id_usuario) VALUES (?,?,?,?,?,?)";

        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection(createFilme)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, lista.getId_lista());
            preparedStatement.setInt(2, lista.getId_filme());
            preparedStatement.setInt(3, lista.getAvaliacao());
            preparedStatement.setString(4, lista.getStatus());
            preparedStatement.setInt(5, lista.getId_usuario());
            preparedStatement.setString(6, lista.getData_adicao());

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

            List<ListaFilme> listafilmes = new ArrayList<>();
            while(resultSet.next()) {
                int id_lista = resultSet.getInt("id_lista");
                int id_filme = resultSet.getInt("id_filme");
                int avaliação = resultSet.getInt("avaliação");
                String status = resultSet.getString("Status");
                String data_adição = resultSet.getString("data_adição");
                int id_usuario = resultSet.getInt("id_usuario");
                ListaFilme listaFilme= new ListaFilme(id_lista,id_usuario, id_filme, status, avaliação, data_adição);
                listafilmes.add(listaFilme);
            }

            System.out.println("Success in select * lista_filmes");
            conn.close();

            return listafilmes;
        }catch (Exception e) {
            System.out.println("fail in database connection"+e.getMessage());
            return Collections.emptyList();
        }

    }
}

