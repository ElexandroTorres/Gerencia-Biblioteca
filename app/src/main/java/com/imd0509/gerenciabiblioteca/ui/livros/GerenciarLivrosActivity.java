package com.imd0509.gerenciabiblioteca.ui.livros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.adapters.LivrosAdapter;
import com.imd0509.gerenciabiblioteca.model.Livro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GerenciarLivrosActivity extends AppCompatActivity implements LivrosAdapter.LivroListener{

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
        listaLivros.add(new Livro("Harry Potter e a pedra filosofal", "Harry Potter é um garoto cujos pais, feiticeiros, foram assassinados por um poderosíssimo bruxo quando ele ainda era um bebê. Ele foi levado, então, para a casa dos tios que nada tinham a ver com o sobrenatural. Pelo contrário. Até os 10 anos, Harry foi uma espécie de gata borralheira: maltratado pelos tios, herdava roupas velhas do primo gorducho, tinha óculos remendados e era tratado como um estorvo. No dia de seu aniversário de 11 anos, entretanto, ele parece deslizar por um buraco sem fundo, como o de Alice no país das maravilhas, que o conduz a um mundo mágico.","J.K Rowling" , "2015", "Rocco; 1ª edição (19 agosto 2017)"));
        listaLivros.add(new Livro("Os Miseraveis", "Um clássico da literatura mundial, esta obra é uma poderosa denúncia a todos os tipos de injustiça humana. Narra a emocionante história de Jean Valjean ― o homem que, por ter roubado um pão, é condenado a dezenove anos de prisão. Os miseráveis é um livro inquietantemente religioso e político, com uma das narrativas mais envolventes já criadas.","Victor Hugo" , "1882", "Martin Claret; 1ª edição (7 outubro 2014)"));
        listaLivros.add(new Livro("O guia do mochileiro das galáxias", "Considerado um dos maiores clássicos da literatura de ficção científica, O Guia do Mochileiro das Galáxias vem encantando gerações de leitores ao redor do mundo com seu humor afiado.Este é o primeiro título da famosa série escrita por Douglas Adams, que conta as aventuras espaciais do inglês Arthur Dent e de seu amigo Ford Prefect.A dupla escapa da destruição da Terra pegando carona numa nave alienígena, graças aos conhecimentos de Prefect, um E.T. que vivia disfarçado de ator desempregado enquanto fazia pesquisa de campo para a nova edição do Guia do Mochileiro das Galáxias, o melhor guia de viagens interplanetário.Mestre da sátira, Douglas Adams cria personagens inesquecíveis e situações mirabolantes para debochar da burocracia, dos políticos, da \"alta cultura\" e de diversas instituições atuais. Seu livro, que trata em última instância da busca do sentido da vida, não só diverte como também faz pensar.","Douglas Adams" , "2015", "Editora Arqueiro (9 novembro 2010)"));
        listaLivros.add(new Livro("Matilda", "Todos os dias Matilda passava horas na Biblioteca, lendo um livro atrás do outro. Mas quanto mais ela lia e aprendia, mais aumentavam seus problemas. Os pais passavam o tempo todo vendo televisão, e achavam muito estranho a menina gostar tanto de ler. A diretora da escola achava Matilda uma fingida, pois não acreditava que uma criança tão nova pudesse saber tantas coisas.","Roald Dahl" , "2015", "WMF Martins Fontes; 4ª edição (9 janeiro 2010)"));
        //

        adapter = new LivrosAdapter(listaLivros, this::onLivroClickListener);

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


    @Override
    public void onLivroClickListener(int position) {
        Intent intentDetalhesLivros = new Intent(GerenciarLivrosActivity.this, DetalhesLivroActivity.class);
        intentDetalhesLivros.putExtra("livroSelecionado", listaLivros.get(position));
        startActivity(intentDetalhesLivros);
    }
}