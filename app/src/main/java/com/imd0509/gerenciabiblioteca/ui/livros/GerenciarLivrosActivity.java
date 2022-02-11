package com.imd0509.gerenciabiblioteca.ui.livros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.adapters.LivrosAdapter;
import com.imd0509.gerenciabiblioteca.dao.LivrosDAO;
import com.imd0509.gerenciabiblioteca.model.Livro;
import com.imd0509.gerenciabiblioteca.model.data.LivrosData;

import java.util.ArrayList;
import java.util.List;

public class GerenciarLivrosActivity extends AppCompatActivity implements LivrosAdapter.LivroListener{

    private FloatingActionButton fabAdicionarNovoLivro;
    private RecyclerView rvListaLivros;
    private LivrosDAO livrosDAO;

    private LivrosAdapter adapter;
    List<Livro> listaLivros = new ArrayList<>();

    LivrosData livrosData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_livros);

        findViewsIds();
        setListeners();

        livrosDAO = new LivrosDAO(getApplicationContext());
        listaLivros = livrosDAO.listLivros();
        adapter = new LivrosAdapter(listaLivros, this::onLivroClickListener);

        livrosData = LivrosData.createLivrosData(adapter, listaLivros);

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(GerenciarLivrosActivity.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(GerenciarLivrosActivity.this, 3);
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


    @Override
    public void onLivroClickListener(int position) {
        Intent intentDetalhesLivros = new Intent(GerenciarLivrosActivity.this, DetalhesLivroActivity.class);
        intentDetalhesLivros.putExtra("livroSelecionado", listaLivros.get(position));
        startActivity(intentDetalhesLivros);
    }
}