package com.imd0509.gerenciabiblioteca.ui.livros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.adapters.LivrosAdapter;
import com.imd0509.gerenciabiblioteca.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class GerenciarLivrosActivity extends AppCompatActivity {

    private FloatingActionButton fabAdicionarNovoLivro;
    private RecyclerView rvListaLivros;

    private LivrosAdapter adapter;

    //Apenas para testes.
    List<Livro> listaLivros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_livros);

        findViewsIds();
        setListeners();

        //apenas para testes
        listaLivros.add(new Livro("Harry Potter", "Um bruxinho da pesada"));
        listaLivros.add(new Livro("Os Miseraveis", "Um bruxinho da pesada"));
        listaLivros.add(new Livro("Coraline", "Um bruxinho da pesada"));
        listaLivros.add(new Livro("O diario de Anne Frank", "Um bruxinho da pesada"));
        //

        adapter = new LivrosAdapter(listaLivros);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GerenciarLivrosActivity.this);
        rvListaLivros.setLayoutManager(layoutManager);
        rvListaLivros.setHasFixedSize(true);
        rvListaLivros.setAdapter(adapter);

    }

    private void findViewsIds() {
        fabAdicionarNovoLivro = findViewById(R.id.fab_adicionar_livro);
        rvListaLivros = findViewById(R.id.rv_lista_livros);
    }

    private void setListeners() {
        fabAdicionarNovoLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GerenciarLivrosActivity.this, AdicionarLivroActivity.class);
                startActivity(intent);
            }
        });
    }


}