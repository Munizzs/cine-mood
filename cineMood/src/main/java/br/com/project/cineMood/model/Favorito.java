package br.com.project.cineMood.model;

public class Favorito {

    private int id_favorito;
    private int id_usuario;
    private int id_filme;
    private String data_favoritado;

    public Favorito(String data_favoritado, int id_favorito, int id_filme, int id_usuario) {
        this.data_favoritado = data_favoritado;
        this.id_favorito = id_favorito;
        this.id_filme = id_filme;
        this.id_usuario = id_usuario;
    }

    public int getId_favorito() {
        return id_favorito;
    }

    public void setId_favorito(int id_favorito) {this.id_favorito = id_favorito;}

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {this.id_usuario = id_usuario;}

    public int getId_filme() {
        return id_filme;
    }

    public void setId_filme(int id_filme) {this.id_filme = id_filme;}

    public String getData_favoritado() {
        return data_favoritado;
    }

    public void setData_favoritado(String data_favoritado) {this.data_favoritado = data_favoritado;}


    @Override
    public String toString() {
        return "Favorito [id_favorito=" + id_favorito + ", id_usuario=" + id_usuario + ", id_filme=" + id_filme
                + ", data_favoritado=" + data_favoritado + "]";
    }
    
    

}
