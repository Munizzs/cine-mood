package br.com.project.cineMood.model;

public class Filme {
    private String title;
    private String poster;
    private String genero;
    private String overview;
    private double voteAverage;
    private  String id;
    private String emocao;

    public Filme(String title, String poster) {
        this.title = title;
        this.poster = poster;
    }

    public Filme(String title, String poster, String genero, String overview) {
        this.title = title;
        this.poster = poster;
        this.genero = genero;
        this.overview = overview;
    }

    public Filme(String title, String poster, String overview) {
        this.title = title;
        this.poster = poster;
        this.overview = overview;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

     public String getEmocao() {
        return emocao;
    }

    public void setEmocao(String emocao) {
        this.emocao = emocao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
