package br.com.project.cineMood.dao;

import br.com.project.cineMood.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UsuarioDao {

    public boolean verificarCredencial(Usuario usuario) {
        String SQL = "SELECT * FROM usuario WHERE email = ?";

        try {
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1, usuario.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String senha = resultSet.getString("senha");
                if (senha.equals(usuario.getSenha())) {
                    return true;
                }
            }
            conn.close();
            return false;
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    public void createUsuario(Usuario usuario) throws SQLException {
        String SQL = "INSERT INTO usuario (nome, email, senha) VALUES (?, ?, ?)";

        try {
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("success in database connection");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());

            preparedStatement.execute();
            System.out.println("success in insert usuario");

            conn.close();
        } catch (Exception e) {
            System.out.println("fail in database connection: " + e.getMessage());
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
            while (resultSet.next()) {
                int id = resultSet.getInt("id_usuario");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                boolean ativo = resultSet.getBoolean("ativo");
                Usuario usuario = new Usuario(id, nome, email, senha, ativo);
                usuarios.add(usuario);
            }

            System.out.println("Success in select * Usuario");
            conn.close();
            return usuarios;
        } catch (Exception e) {
            System.out.println("fail in database connection: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public void deleteUsuarioById(int id) {
        String SQL = "DELETE FROM usuario WHERE id_usuario = ?";
        try {
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("Sucesso na conexão (Deletar Usuario)");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            System.out.println("Sucesso em remover o id: " + id);
            conn.close();
        } catch (Exception e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }

    public void updateUsuario(Usuario usuario) {
        String SQL = "UPDATE usuario SET nome = ?, email = ?, senha = ?, ativo = ? WHERE id_usuario = ?";

        try {
            InitDao conex = new InitDao();
            Connection conn = conex.getConnection();
            System.out.println("Sucesso em conectar com o banco de dados");

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setBoolean(4, usuario.isAtivo());
            preparedStatement.setInt(5, usuario.getIdUsuario());

            preparedStatement.execute();

            System.out.println("Atualizado com sucesso id - " + usuario.getIdUsuario());
            conn.close();
        } catch (Exception e) {
            System.out.println("Falha na conexão da database");
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
