package br.com.project.cineMood.model;

import java.time.LocalDate;

public class Recomendacao {

    private int id_recomendacao;
    private int id_usuario;
    private int id_filme;
    private int id_emocao;
    private LocalDate data_recomendacao;

    public Recomendacao(LocalDate data_recomendacao, int id_emocao, int id_filme, int id_recomendacao, int id_usuario) {
        this.data_recomendacao = data_recomendacao;
        this.id_emocao = id_emocao;
        this.id_filme = id_filme;
        this.id_recomendacao = id_recomendacao;
        this.id_usuario = id_usuario;
    }

    public int getId_recomendacao() {
        return id_recomendacao;
    }

    public void setId_recomendacao(int id_recomendacao) {
        this.id_recomendacao = id_recomendacao;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_filme() {
        return id_filme;
    }

    public void setId_filme(int id_filme) {
        this.id_filme = id_filme;
    }

    public int getId_emocao() {
        return id_emocao;
    }

    public void setId_emocao(int id_emocao) {
        this.id_emocao = id_emocao;
    }

    public LocalDate getData_recomendacao() {
        return data_recomendacao;
    }

    public void setData_recomendacao(LocalDate data_recomendacao) {
        this.data_recomendacao = data_recomendacao;
    }

    @Override
    public String toString() {
        return "Recomendacao [id_recomendacao=" + id_recomendacao + ", id_usuario=" + id_usuario + ", id_filme="
                + id_filme + ", id_emocao=" + id_emocao + ", data_recomendacao=" + data_recomendacao + "]";
    }

    

}
