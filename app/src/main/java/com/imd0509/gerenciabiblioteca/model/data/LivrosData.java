package com.imd0509.gerenciabiblioteca.model.data;

import android.content.Context;
import android.widget.Toast;

import com.imd0509.gerenciabiblioteca.adapters.LivrosAdapter;
import com.imd0509.gerenciabiblioteca.dao.LivrosDAO;
import com.imd0509.gerenciabiblioteca.model.Livro;

import java.util.List;

public final class LivrosData {
    private List<Livro> livros;
    private LivrosAdapter livrosAdapter;
    private LivrosDAO livrosDAO;

    private static LivrosData instance;

    private LivrosData(LivrosAdapter livrosAdapter, List<Livro> livros) {
        this.livrosAdapter = livrosAdapter;
        this.livros = livros;
    }

    public static LivrosData createLivrosData(LivrosAdapter livrosAdapter, List<Livro> livros) {
       if(instance == null) {
           instance = new LivrosData(livrosAdapter, livros);
       }
       return instance;
    }

    public static LivrosData getLivrosData() {
        return instance;
    }

    public void adicionarLivro(Livro livro, Context context) {
        livrosDAO = new LivrosDAO(context);
        long resultado = livrosDAO.save(livro);

        if(resultado != (-1)) {
            Toast.makeText(context, "Adicionado com sucesso...", Toast.LENGTH_SHORT).show();
            livro.setId((int) resultado);
            instance.livros.add(livro);
            instance.livrosAdapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(context, "Erro ao adicionar com sucesso...", Toast.LENGTH_SHORT).show();
        }
    }




}



