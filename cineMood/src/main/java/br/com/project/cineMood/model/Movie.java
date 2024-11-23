package br.com.project.cineMood.model;

public class Movie {
    private String title;
    private String poster;
    private String genre;
    private String plot;
    private String emotion;

    public Movie(String title, String poster) {
        this.title = title;
        this.poster = poster;
    }

    public Movie(String title, String poster, String genre, String plot) {
        this.title = title;
        this.poster = poster;
        this.genre = genre;
        this.plot = plot;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }

    public String getGenre() {
        return genre;
    }

    public String getPlot() {
        return plot;
    }

    public String getEmotion() {
        return emotion;
    }
}
