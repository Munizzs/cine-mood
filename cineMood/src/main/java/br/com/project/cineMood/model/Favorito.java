package br.com.project.cineMood.model;

public class Favorito {

    private String id_favorito;
    private String id_usuario;
    private String id_filme;
    private String data_favoritado;

    public Favorito(String id_favorito, String id_usuario, String id_filme, String data_favoritado) {
        this.id_favorito = id_favorito;
        this.id_usuario = id_usuario;
        this.id_filme = id_filme;
        this.data_favoritado = data_favoritado;
    }

    public String getId_favorito() {
        return id_favorito;
    }

    public void setId_favorito(String id_favorito) {
        this.id_favorito = id_favorito;
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

    public String getData_favoritado() {
        return data_favoritado;
    }

    public void setData_favoritado(String data_favoritado) {
        this.data_favoritado = data_favoritado;
    }

    @Override
    public String toString() {
        return "Favorito [id_favorito=" + id_favorito + ", id_usuario=" + id_usuario + ", id_filme=" + id_filme
                + ", data_favoritado=" + data_favoritado + "]";
    }
    
    

}
