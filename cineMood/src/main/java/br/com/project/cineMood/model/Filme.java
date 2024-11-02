package br.com.project.cineMood.model;

public class Filme {
    private String id_filme;
    private String titulo;
    private String diretor;
    private String tipo;
    private String genero;
    private String ano_lancamento;
    private String sinopse;
    private String duracao;
    private String classificao_indicativa;

    public Filme() {
    }

    public Filme(String titulo, String tipo) {
        this.titulo = titulo;
        this.tipo = tipo;
    }

    public Filme(String id_filme,String titulo,String tipo) {
        this.id_filme = id_filme;
        this.titulo = titulo;
        this.tipo = tipo;
    }

    public Filme(String id_filme, String titulo, String diretor, String tipo, String genero, String ano_lancamento, String sinopse, String duracao, String classificao_indicativa) {
        this.id_filme = id_filme;
        this.titulo = titulo;
        this.diretor = diretor;
        this.tipo = tipo;
        this.genero = genero;
        this.ano_lancamento = ano_lancamento;
        this.sinopse = sinopse;
        this.duracao = duracao;
        this.classificao_indicativa = classificao_indicativa;
    }


    public String getId_filme() {return id_filme;}

    public void setId_filme(String id_filme) {this.id_filme = id_filme;}

    public String getTitulo() {return titulo;}

    public void setTitulo(String titulo) {this.titulo = titulo;}

    public String getDiretor() {return diretor;}

    public void setDiretor(String diretor) {this.diretor = diretor;}

    public String getTipo() {return tipo;}

    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getGenero() {return genero;}

    public void setGenero(String genero) {this.genero = genero;}

    public String getAno_lancamento() {return ano_lancamento;}

    public void setAno_lancamento(String ano_lancamento) {this.ano_lancamento = ano_lancamento;}

    public String getSinopse() {return sinopse;}

    public void setSinopse(String sinopse) {this.sinopse = sinopse;}

    public String getDuracao() {return duracao;}

    public void setDuracao(String duracao) {this.duracao = duracao;}

    public String getClassificao_indicativa() {return classificao_indicativa;}

    public void setClassificao_indicativa(String classificao_indicativa) {this.classificao_indicativa = classificao_indicativa;}

    @Override
    public String toString() {
        return "Filme{" +
                "id_filme=" + id_filme +
                ", titulo='" + titulo + '\'' +
                ", diretor='" + diretor + '\'' +
                ", tipo='" + tipo + '\'' +
                ", genero='" + genero + '\'' +
                ", ano_lancamento='" + ano_lancamento + '\'' +
                ", sinopse='" + sinopse + '\'' +
                ", duracao='" + duracao + '\'' +
                ", classificao_indicativa='" + classificao_indicativa + '\'' +
                '}';
    }


}
