package com.imd0509.gerenciabiblioteca.model;

import java.util.List;

public class Livro {
    private int id;
    private String titulo;
    private String subTitulo;
    private String descricao;
    private List<String> atores;
    private String dataPublicação;
    private String lingua;
    private String urlImagemCapa;


    public Livro(){}

    public Livro(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getAtores() {
        return atores;
    }

    public void setAtores(List<String> atores) {
        this.atores = atores;
    }

    public String getDataPublicação() {
        return dataPublicação;
    }

    public void setDataPublicação(String dataPublicação) {
        this.dataPublicação = dataPublicação;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getUrlImagemCapa() {
        return urlImagemCapa;
    }

    public void setUrlImagemCapa(String urlImagemCapa) {
        this.urlImagemCapa = urlImagemCapa;
    }
}
