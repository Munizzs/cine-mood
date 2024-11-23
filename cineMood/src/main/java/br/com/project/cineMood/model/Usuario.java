package br.com.project.cineMood.model;

import java.util.Date;

public class Usuario {
    private int id_usuario;
    private String nome;
    private String email;
    private String senha;
    private String data_nascimento;

    public Usuario() {
    }

    public Usuario(int id_usuario, String nome, String email, String senha, String data_nascimento) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.data_nascimento = data_nascimento;
    }

    public Usuario(String nome, String email, String senha, String data_nascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.data_nascimento = data_nascimento;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public int getId_usuario() {return id_usuario;}

    public void setId_usuario(int id_usuario) {this.id_usuario = id_usuario;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}

    public String getData_nascimento() {return data_nascimento;}

    public void setData_nascimento(String data_nascimento) {this.data_nascimento = data_nascimento;}


    @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", data_nascimento='" + data_nascimento + '\'' +
                '}';
    }

}
