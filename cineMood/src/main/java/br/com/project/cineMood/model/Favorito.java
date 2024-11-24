package br.com.project.cineMood.model;

public class Favorito {
    private int idFavorito;
    private int idUsuario;
    private Status status;
    private int avaliacao;
    private String idFilme;
    private String genero;

    public Favorito(int idFavorito, int idUsuario, Status status, int avaliacao, String idFilme, String genero) {
        this.idFavorito = idFavorito;
        this.idUsuario = idUsuario;
        this.status = status;
        this.avaliacao = avaliacao;
        this.idFilme = idFilme;
        this.genero = genero;
    }

    public Favorito() {
    }


    public int getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(int idFavorito) {
        this.idFavorito = idFavorito;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        if (avaliacao < 0 || avaliacao > 5) {
            throw new IllegalArgumentException("A avaliação deve estar entre 0 e 5.");
        }
        this.avaliacao = avaliacao;
    }

    public String getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(String idFilme) {
        this.idFilme = idFilme;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    // Enum para o status
    public enum Status {
        ASSISTIDO,
        ASSISTINDO,
        QUERO_ASSISTIR
    }
}
