package br.com.project.cineMood.model;

public class Emocoes {
    private int idEmocao;
    private String nome;
    private String descricao;

    public Emocoes(int idEmocao, String nome, String descricao) {
        this.idEmocao = idEmocao;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getIdEmocao() {
        return idEmocao;
    }

    public void setIdEmocao(int idEmocao) {
        this.idEmocao = idEmocao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

