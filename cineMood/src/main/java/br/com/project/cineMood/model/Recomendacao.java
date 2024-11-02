package br.com.project.cineMood.model;

public class Recomendacao {
    private int id_recomendacao;
    private int id_usuario;
    private int id_filme;
    private int id_emocao;
    private String data_adicao;

    public Recomendacao() {
    }

    public Recomendacao(int id_recomendacao, int id_usuario, int id_filme, int id_emocao, String data_adicao) {
        this.id_recomendacao = id_recomendacao;
        this.id_usuario = id_usuario;
        this.id_filme = id_filme;
        this.id_emocao = id_emocao;
        this.data_adicao = data_adicao;
    }
    public Recomendacao( int id_usuario, int id_filme, int id_emocao, String data_adicao) {
        this.id_usuario = id_usuario;
        this.id_filme = id_filme;
        this.id_emocao = id_emocao;
        this.data_adicao = data_adicao;
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

    public void setId_filme(int id_filme) {this.id_filme = id_filme;}

    public int getId_emocao() {
        return id_emocao;
    }

    public void setId_emocao(int id_emocao) {
        this.id_emocao = id_emocao;
    }

    public String getData_adicao() {
        return data_adicao;
    }

    public void setData_adicao(String data_adicao) {
        this.data_adicao = data_adicao;
    }

    @Override
    public String toString() {
        return "Recomendacao [id_recomendacao=" + id_recomendacao + ", id_usuario=" + id_usuario + ", id_filme="
                + id_filme + ", id_emocao=" + id_emocao + ", data_recomendacao=" + data_adicao + "]";
    }

    

}
