package com.imd0509.gerenciabiblioteca.ui.livros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.model.Livro;

public class DetalhesLivroActivity extends AppCompatActivity {

    ImageView capaLivro;
    TextView tituloLivro;
    TextView autorLivro;
    TextView editoraAno;
    TextView descricaoLivro;
    TextView identificadorLivro;
    TextView quantidadeLivros;
    ImageButton diminuirQuantidade;
    ImageButton aumentarQuantidade;

    Livro livro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_livro);

        findViewsIds();
        setListeners();

        livro = getIntent().getParcelableExtra("livroSelecionado");

        setValuesOnViews();
    }

    private void findViewsIds() {
        capaLivro = findViewById(R.id.detalhes_livro_iv_capa_livro);
        tituloLivro = findViewById(R.id.detalhes_livro_tv_titulo_livro);
        autorLivro = findViewById(R.id.detalhes_livro_tv_autor_livro);
        editoraAno = findViewById(R.id.detalhes_livro_tv_editora_ano);
        descricaoLivro = findViewById(R.id.detalhes_livro_tv_descricao_livro);
        identificadorLivro = findViewById(R.id.detalhes_livro_tv_identificador_livro);
        quantidadeLivros = findViewById(R.id.detalhes_livro_tv_quantidade_livros);
        diminuirQuantidade = findViewById(R.id.detalhes_livro_ibtn_diminuir_livro);
        aumentarQuantidade = findViewById(R.id.detalhes_livro_ibtn_aumentar_livro);
    }

    private void setListeners() {
        aumentarQuantidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aumentar a quantidade de livros.
            }
        });
        diminuirQuantidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //diminuir a quantidade de livros
            }
        });
    }

    private void setValuesOnViews() {
        tituloLivro.setText(livro.getTitulo());
        autorLivro.setText(livro.getAutores());
        editoraAno.setText("...");
        descricaoLivro.setText(livro.getDescricao());
        identificadorLivro.setText("123456789");
        quantidadeLivros.setText("42");
    }
}