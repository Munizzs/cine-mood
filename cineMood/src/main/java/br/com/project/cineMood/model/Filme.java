package br.com.project.cineMood.model;

public class Filme {
    private String title;            // Título do filme
    private String originalTitle;    // Título original do filme
    private String poster_path;           // URL do poster_path
    private String backdrop_path;         // URL do backdrop
    private String genres;           // Gêneros do filme
    private String overview;         // Sinopse
    private double voteAverage;      // Nota média
    private String releaseDate;      // Data de lançamento
    private int id;                  // ID do filme
    private String emocao;           // Emoção associada

    // Construtores
    public Filme(String title, String backdrop_path,Double nada) {
        this.title = title;
        this.backdrop_path = backdrop_path;
    }
    public Filme(String title, String poster_path, int id) {
        this.title = title;
        this.poster_path = poster_path;
        this.id = id;
    }

    public Filme(String title, String poster_path) {
        this.title = title;
        this.poster_path = poster_path;
    }

    public Filme(String title, String poster_path, String overview) {
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
    }

    public Filme(String title, String poster_path, String overview, String genres) {
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.genres = genres;
    }

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmocao() {
        return emocao;
    }

    public void setEmocao(String emocao) {
        this.emocao = emocao;
    }
}
