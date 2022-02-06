package com.imd0509.gerenciabiblioteca.model.apiresponse;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Root {
    //@SerializedName("items")
    public ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        String string = "";
        for(int i = 0; i < items.size(); i++) {
            string += " " + items.get(i);
        }
        return string;
    }
}
