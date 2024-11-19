package br.com.project.cineMood.model;

public class Movie {
    private String title;
    private String poster;

    public Movie(String title, String poster) {
        this.title = title;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public String getPoster() {
        return poster;
    }
}
