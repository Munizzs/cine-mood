//gerencia a relação dos filmes com o status que o usuário define
package br.com.project.cineMood.model;

public class ListaFilme {
    private int id_lista;
    private int id_usuario;
    private int id_filme;
    private String status;
    private int avaliacao;
    private String data_adicao;

    public ListaFilme() {
    }

    public ListaFilme(int id_lista, int id_usuario, int id_filme, String status, int avaliacao, String data_adicao) {
        this.id_lista = id_lista;
        this.id_usuario = id_usuario;
        this.id_filme = id_filme;
        this.status = status;
        this.avaliacao = avaliacao;
        this.data_adicao = data_adicao;
    }

    public ListaFilme(int id_usuario, int id_filme, String status, int avaliacao, String data_adicao) {
        this.id_usuario = id_usuario;
        this.id_filme = id_filme;
        this.status = status;
        this.avaliacao = avaliacao;
        this.data_adicao = data_adicao;
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
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

    public void setId_filme(int id_filme) {
        this.id_filme = id_filme;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getData_adicao() {
        return data_adicao;
    }

    public void setData_adicao(String data_adicao) {
        this.data_adicao = data_adicao;
    }

    @Override
    public String toString() {
        return "ListaFilme [id_lista=" + id_lista + ", id_usuario=" + id_usuario + ", id_filme=" + id_filme
                + ", status=" + status + ", avaliacao=" + avaliacao + ", data_adicao=" + data_adicao + "]";
    }
    
    }


