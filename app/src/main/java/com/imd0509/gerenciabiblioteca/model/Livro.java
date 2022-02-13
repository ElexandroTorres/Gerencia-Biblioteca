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
    private boolean estaDisponivel;

    public Livro(){
        this.estaDisponivel = true;
    }

    public Livro(int id, String titulo, String descricao, String autores, String urlImagemCapa, String publicadoraAno) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.autores = autores;
        this.urlImagemCapa = urlImagemCapa;
        this.publicadoraAno = publicadoraAno;
        this.estaDisponivel = true;
    }

    /* ID */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /* TITULO */
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo != null ? titulo : "";
    }
    /* PUBLICADORA ANO */
    public String getPublicadoraAno() {
        return publicadoraAno;
    }

    public void setPublicadoraAno(String publicadoraAno) {
        this.publicadoraAno = publicadoraAno;
    }

    public void setPublicadoraAno(String publicadora, String ano) {
        if(publicadora != null) {
            if(ano != null) {
                this.publicadoraAno = publicadora + ", " + ano;
            }
            else {
                this.publicadoraAno = publicadora;
            }
        }
        else {
            this.publicadoraAno = "Sem informações da publicação!";
        }
    }
    /* DESCRIÇÃO */
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao != null ? descricao : "";
    }
    /* AUTORES */
    public String getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        if(autores != null) {
            StringBuilder autoresString = new StringBuilder();
            for(int i = 0; i < autores.size(); i++) {
                autoresString.append(", ").append(autores.get(i));
            }

            this.autores = autoresString.toString();
        }
        else {
            this.autores = "";
        }

    }

    public void setAutores(String autores) {
        this.autores = autores;
    }
    /* IMAGEM CAPA */
    public String getUrlImagemCapa() {
        return urlImagemCapa;
    }

    public void setUrlImagemCapa(String urlImagemCapa) {
        if(urlImagemCapa != null) {
            this.urlImagemCapa = urlImagemCapa;
        }
        else {
            this.urlImagemCapa = "";
        }
    }

    public void emprestarLivro() {
        this.estaDisponivel = false;
    }

    public void devolverLivro() {
        this.estaDisponivel = true;
    }

    public boolean getDisponibilidade() {
        return this.estaDisponivel;
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
