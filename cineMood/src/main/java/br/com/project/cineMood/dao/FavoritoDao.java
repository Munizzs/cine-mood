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
        public void createFavorito(Favorito favorito)throws SQLException {

            String SQL = "INSERT INTO favorito (id_usuario, id_filme, data_favoritado) VALUES (?,?,?)";

            try {

                InitDao conex = new InitDao();
                Connection conn = conex.getConnection();
                System.out.println("success in database connection(createFilme)");

                PreparedStatement preparedStatement = conn.prepareStatement(SQL);
                preparedStatement.setInt(1, favorito.getId_usuario());
                preparedStatement.setInt(2, favorito.getId_filme());
                preparedStatement.setString(3, favorito.getData_favoritado());

                preparedStatement.execute();
                System.out.println("success in insert favorito");

                conn.close();

            } catch (Exception e) {

                System.out.println("fail in database connection"+e.getMessage());

            }
        }
        public List<Favorito> findAllFavorito() {
            String SQL = "SELECT * FROM favorito";
            try {

                InitDao conex = new InitDao();
                Connection conn = conex.getConnection();
                System.out.println("success in database connection (to list)");

                PreparedStatement preparedStatement = conn.prepareStatement(SQL);
                ResultSet resultSet = preparedStatement.executeQuery();

                List<Favorito> favoritos = new ArrayList<>();
                while(resultSet.next()) {
                    int id_favorito = resultSet.getInt("id_favorito");
                    int id_usuario = resultSet.getInt("id_usuario");
                    int id_filme = resultSet.getInt("id_filme");
                    String data_favoritado = resultSet.getString("data_favoritado");

                    Favorito favorito= new Favorito(id_favorito,id_usuario,id_filme,data_favoritado);
                    favoritos.add(favorito);
                }

                System.out.println("Success in select * Favorito");
                conn.close();

                return favoritos;
            }catch (Exception e) {
                System.out.println("fail in database connection"+e.getMessage());
                return Collections.emptyList();
            }
        }

        public void deleteFavoritoById(int id) {
            String SQL = "DELETE FROM favorito WHERE id_favorito = ?";
            try{
                InitDao conex = new InitDao();
                Connection conn = conex.getConnection();
                System.out.println("Sucesso na conexão (Deletar Favorito)");

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


