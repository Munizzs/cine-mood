package br.com.project.cineMood.model;

public class Recomendacao {
    private String id_recomendacao;
    private String id_usuario;
    private String id_filme;
    private String id_emocao;
    private String data_recomendacao;

    public Recomendacao(String id_recomendacao, String id_usuario, String id_filme, String id_emocao, String data_recomendacao) {
        this.id_recomendacao = id_recomendacao;
        this.id_usuario = id_usuario;
        this.id_filme = id_filme;
        this.id_emocao = id_emocao;
        this.data_recomendacao = data_recomendacao;
    }

    public String getId_recomendacao() {
        return id_recomendacao;
    }

    public void setId_recomendacao(String id_recomendacao) {
        this.id_recomendacao = id_recomendacao;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getId_filme() {
        return id_filme;
    }

    public void setId_filme(String id_filme) {
        this.id_filme = id_filme;
    }

    public String getId_emocao() {
        return id_emocao;
    }

    public void setId_emocao(String id_emocao) {
        this.id_emocao = id_emocao;
    }

    public String getData_recomendacao() {
        return data_recomendacao;
    }

    public void setData_recomendacao(String data_recomendacao) {
        this.data_recomendacao = data_recomendacao;
    }

    @Override
    public String toString() {
        return "Recomendacao [id_recomendacao=" + id_recomendacao + ", id_usuario=" + id_usuario + ", id_filme="
                + id_filme + ", id_emocao=" + id_emocao + ", data_recomendacao=" + data_recomendacao + "]";
    }

    

}
