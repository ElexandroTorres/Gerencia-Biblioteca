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


    public String getAuthors() {
        String atores = "";
        for(int i = 0; i < authors.size(); i++) {
            atores = atores + " " + authors.get(i);
        }
        return atores;
    }

}