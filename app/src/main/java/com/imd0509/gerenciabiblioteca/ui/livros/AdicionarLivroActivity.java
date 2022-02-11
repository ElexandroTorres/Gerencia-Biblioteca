package com.imd0509.gerenciabiblioteca.ui.livros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.adapters.ResultadosApiAdapter;
import com.imd0509.gerenciabiblioteca.dao.LivrosDAO;
import com.imd0509.gerenciabiblioteca.model.Livro;
import com.imd0509.gerenciabiblioteca.model.apiresponse.Item;
import com.imd0509.gerenciabiblioteca.model.apiresponse.Root;
import com.imd0509.gerenciabiblioteca.model.apiresponse.VolumeInfo;
import com.imd0509.gerenciabiblioteca.model.data.LivrosData;
import com.imd0509.gerenciabiblioteca.network.GoogleBooksApi;
import com.imd0509.gerenciabiblioteca.network.IBookService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdicionarLivroActivity extends AppCompatActivity implements ResultadosApiAdapter.ResultadoListener{

    private EditText edtPesquisarGoogleBooks;
    private Button btnPesquisarGoogleBooks;
    private RecyclerView rvResultadosApi;
    private ProgressBar progressBar;

    private List<Item> resultados = new ArrayList<>();
    LivrosDAO livrosDAO;

    private ResultadosApiAdapter adapter;

    private LivrosData livrosData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_livro);

        findViewsIds();
        setListeners();
        configurarListaResultados();

        livrosDAO = new LivrosDAO(getApplicationContext());

        livrosData = LivrosData.getLivrosData();




    }

    private void findViewsIds() {
        edtPesquisarGoogleBooks = findViewById(R.id.adicionar_livro_edt_pesquisar_google_books);
        btnPesquisarGoogleBooks = findViewById(R.id.adicionar_livro_btn_pesquisar_google_books);
        rvResultadosApi = findViewById(R.id.adicionar_livro_rv_resultados_api);
        progressBar = findViewById(R.id.adicionar_livro_progressBar);
    }

    private void setListeners() {
        btnPesquisarGoogleBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                IBookService bookService = GoogleBooksApi.getClient().create(IBookService.class);
                Call<Root> resultado = bookService.pesquisarLivro(edtPesquisarGoogleBooks.getText().toString());

                resultado.enqueue(new Callback<Root>() {
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        if(response.isSuccessful() && response != null) {
                            resultados = response.body().getItems();
                            adapter.setList(resultados);
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<Root> call, Throwable t) {
                        Log.d("meuerro", t.toString());
                    }
                });
            }
        });
    }

    private void configurarListaResultados() {
        adapter = new ResultadosApiAdapter(resultados, AdicionarLivroActivity.this);
        rvResultadosApi.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AdicionarLivroActivity.this);
        rvResultadosApi.setLayoutManager(layoutManager);
        rvResultadosApi.setHasFixedSize(true);
        rvResultadosApi.setAdapter(adapter);
    }

    @Override
    public void onResultadoClickListener(int position) {

        VolumeInfo livroSelecionado = resultados.get(position).getVolumeInfo();

        Livro livroAdicionar = new Livro();

        livroAdicionar.setTitulo(livroSelecionado.title);
        livroAdicionar.setDescricao(livroSelecionado.description);
        livroAdicionar.setAutores(livroSelecionado.authors);
        livroAdicionar.setPublicadoraAno(livroSelecionado.publisher, livroSelecionado.publishedDate);
        if(livroSelecionado.imageLinks != null) {
            livroAdicionar.setUrlImagemCapa(livroSelecionado.imageLinks.thumbnail);
            Log.d("salvou", "salvou com imagem");
        }
        else {
            livroAdicionar.setUrlImagemCapa("");
            Log.d("salvou", "salvou SEMm imagem");
        }

        livrosData.adicionarLivro(livroAdicionar, getApplicationContext());
    }
}