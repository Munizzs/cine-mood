package br.com.project.cineMood.model;

public class Emocao {
    private int idEmocao;
    private String nome;
    private String genre;
    private String image;

    public Emocao(int idEmocao, String nome, String genre, String image) {
        this.idEmocao = idEmocao;
        this.nome = nome;
        this.genre = genre;
        this.image = image;
    }

    public Emocao(int idEmocao, String nome, String genre) {
        this.idEmocao = idEmocao;
        this.nome = nome;
        this.genre = genre;
    }
    public Emocao() {}

    public int getIdEmocao() {return idEmocao;}

    public void setIdEmocao(int idEmocao) {this.idEmocao = idEmocao;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getGenre() {return genre;}

    public void setGenre(String genre) {this.genre = genre;}
    
    public String getImage() {return image;}

    public void setImage(String image) {this.image = image;}
}

