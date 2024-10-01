//gerencia a relação dos filmes com o status que o usuário define
package br.com.project.cineMood.model;

import java.time.LocalDate;

public class ListaFilme {

    
    

    private String id_lista;
    private String id_usuario;
    private String id_filme;
    private String status;
    private int avaliacao;
    private LocalDate data_adicao;

    public ListaFilme(int avaliacao, LocalDate data_adicao, String id_filme, String id_lista, String id_usuario, String status) {
        this.avaliacao = avaliacao;
        this.data_adicao = data_adicao;
        this.id_filme = id_filme;
        this.id_lista = id_lista;
        this.id_usuario = id_usuario;
        this.status = status;
    }

    public String getId_lista() {
        return id_lista;
    }

    public void setId_lista(String id_lista) {
        this.id_lista = id_lista;
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

    public LocalDate getData_adicao() {
        return data_adicao;
    }

    public void setData_adicao(LocalDate data_adicao) {
        this.data_adicao = data_adicao;
    }

    @Override
    public String toString() {
        return "ListaFilme [id_lista=" + id_lista + ", id_usuario=" + id_usuario + ", id_filme=" + id_filme
                + ", status=" + status + ", avaliacao=" + avaliacao + ", data_adicao=" + data_adicao + "]";
    }

    
    }


