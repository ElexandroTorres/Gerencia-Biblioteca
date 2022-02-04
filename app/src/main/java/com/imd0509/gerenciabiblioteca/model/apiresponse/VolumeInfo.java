package com.imd0509.gerenciabiblioteca.model.apiresponse;

import java.util.ArrayList;

public class VolumeInfo{
    public String title;
    public ArrayList<String> authors;
    public String publishedDate;
    public int pageCount;
    public String language;
    public String subtitle;
    public String description;
    public String publisher;
    public ImageLinks imageLinks;

    @Override
    public String toString() {
        return "VolumeInfo{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", publishedDate='" + publishedDate + '\'' +
                ", pageCount=" + pageCount +
                ", language='" + language + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", description='" + description + '\'' +
                ", publisher='" + publisher + '\'' +
                ", imageLinks=" + imageLinks +
                '}';
    }
}