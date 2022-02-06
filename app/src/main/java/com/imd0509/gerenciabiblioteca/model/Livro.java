package com.imd0509.gerenciabiblioteca.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Livro implements Parcelable {
    private int id;
    private String titulo;
    private String descricao;
    private String atores;
    private String dataPublicação;
    private String publicadora;
    private String urlImagemCapa;


    public Livro(){}

    public Livro(String titulo, String descricao, String atores, String dataPublicação, String publicadora) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.atores = atores;
        this.dataPublicação = dataPublicação;
        this.publicadora = publicadora;
    }

    protected Livro(Parcel in) {
        id = in.readInt();
        titulo = in.readString();
        descricao = in.readString();
        atores = in.readString();
        dataPublicação = in.readString();
        publicadora = in.readString();
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

    public String getPublicadora() {
        return publicadora;
    }

    public void setPublicadora(String publicadora) {
        this.publicadora = publicadora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public String getDataPublicação() {
        return dataPublicação;
    }

    public void setDataPublicação(String dataPublicação) {
        this.dataPublicação = dataPublicação;
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(titulo);
        dest.writeString(descricao);
        dest.writeString(atores);
        dest.writeString(dataPublicação);
        dest.writeString(publicadora);
        dest.writeString(urlImagemCapa);
    }
}
