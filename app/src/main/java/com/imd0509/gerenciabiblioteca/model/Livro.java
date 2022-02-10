package com.imd0509.gerenciabiblioteca.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Livro implements Parcelable {
    private int id;
    private String titulo;
    private String descricao;
    private String autores;
    private String urlImagemCapa;
    private String publicadoraAno;

    public Livro(){}

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

    public String getPublicadoraAno() {
        return publicadoraAno;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        String autoresString = "";
        for(int i = 0; i < autores.size(); i++) {
            autoresString = autoresString + ", " + autores.get(i);
        }

        this.autores = autoresString;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    //Quando ja vem do banco.
    public void setPublicadoraAno(String publicadoraAno) {
        this.publicadoraAno = publicadoraAno;
    }

    public void setPublicadoraAno(String publicadora, String ano) {
        //fazer algo depois
    }

    public String getUrlImagemCapa() {
        return urlImagemCapa;
    }

    public void setUrlImagemCapa(String urlImagemCapa) {
        this.urlImagemCapa = urlImagemCapa;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected Livro(Parcel in) {
        id = in.readInt();
        titulo = in.readString();
        descricao = in.readString();
        autores = in.readString();
        publicadoraAno = in.readString();
        urlImagemCapa = in.readString();
    }

    public static final Creator<Livro> CREATOR = new Creator<Livro>() {
        @Override
        public Livro createFromParcel(Parcel in) {
            return new Livro(in);
        }

        @Override
        public Livro[] newArray(int size) {
            return new Livro[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titulo);
        dest.writeString(descricao);
        dest.writeString(autores);
        dest.writeString(publicadoraAno);
        dest.writeString(urlImagemCapa);
    }
}
