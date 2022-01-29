package com.imd0509.gerenciabiblioteca.ui.livros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imd0509.gerenciabiblioteca.R;

public class GerenciarLivrosActivity extends AppCompatActivity {

    private FloatingActionButton fabAdicionarNovoLivro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_livros);

        findViewsIds();
        setListeners();
    }

    private void findViewsIds() {
        fabAdicionarNovoLivro = findViewById(R.id.fab_adicionar_livro);
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