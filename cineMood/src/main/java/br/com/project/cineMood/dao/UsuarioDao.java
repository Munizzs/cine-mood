package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsuarioDao {
    public void createUsuario(Usuario usuario)throws SQLException {

        String SQL = "INSERT INTO usuario (nome,email, senha, data_nascimento) VALUES (?,?,?,?)";

        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection(createFilme)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setString(4, usuario.getData_nascimento());

            preparedStatement.execute();
            System.out.println("success in insert usuario");

            conn.close();

        } catch (Exception e) {

            System.out.println("fail in database connection"+e.getMessage());

        }
    }

    public List<Usuario> findAllUsuario() {
        String SQL = "SELECT * FROM usuario";
        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection (to list)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Usuario> usuarios = new ArrayList<>();
            while(resultSet.next()) {
                String id = resultSet.getString("id_usuario");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String data_nascimento = resultSet.getString("data_nascimento");
                Usuario usuario= new Usuario(id,nome,email,senha,data_nascimento);
                usuarios.add(usuario);
            }

            System.out.println("Success in select * Usuario");
            conn.close();

            return usuarios;
        }catch (Exception e) {
            System.out.println("fail in database connection"+e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deleteUsuarioById(int id) {
        String SQL = "DELETE FROM usuario WHERE id_usuario = ?";
        try{
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("Sucesso na conexão (Deletar Usuario)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            System.out.println("Sucesso em remover o id: "+id);
            conn.close();

        }catch (Exception e) {
            System.out.println("Erro ao remover: "+e.getMessage());
        }
    }

    public void updateUsuario(Usuario usuario) {
        String SQL = "UPDATE usuario SET nome = ?,email = ?, senha = ?, data_nascimento = ? WHERE id_usuario = ?";

        try {

            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("Sucesso em conectar com o banco de dados");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setString(4, usuario.getData_nascimento());
            preparedStatement.setString(5, usuario.getId_usuario());
            preparedStatement.execute();

            System.out.println("Atualizado com sucesso");

            conn.close();

        } catch (Exception e) {

            System.out.println("Falha na conexão da database");
            System.out.println("Erro: " + e.getMessage());

        }
    }
}
