package com.imd0509.gerenciabiblioteca.ui.livros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.model.Livro;

public class DetalhesLivroActivity extends AppCompatActivity {

    ImageView capaLivro;
    TextView tituloLivro;
    TextView autorLivro;
    TextView editoraAno;
    TextView descricaoLivro;
    TextView identificadorLivro;

    Livro livro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_livro);

        findViewsIds();

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
    }


    private void setValuesOnViews() {

        Log.d("livro", livro.getUrlImagemCapa());

        if(!livro.getUrlImagemCapa().isEmpty()) {
            Glide.with(DetalhesLivroActivity.this)
                    .load(livro.getUrlImagemCapa())
                    .placeholder(R.drawable.capa_livro_shape)
                    .error(R.drawable.capa_livro_shape)
                    .into(capaLivro);
        }



        tituloLivro.setText(livro.getTitulo());
        autorLivro.setText(livro.getAutores());
        editoraAno.setText(livro.getPublicadoraAno());
        descricaoLivro.setText(livro.getDescricao());
        identificadorLivro.setText("" + livro.getId());
    }
}