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
import android.widget.TextView;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.adapters.LivrosAdapter;
import com.imd0509.gerenciabiblioteca.adapters.ResultadosApiAdapter;
import com.imd0509.gerenciabiblioteca.model.apiresponse.Item;
import com.imd0509.gerenciabiblioteca.model.apiresponse.Root;
import com.imd0509.gerenciabiblioteca.network.GoogleBooksApi;
import com.imd0509.gerenciabiblioteca.network.IBookService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdicionarLivroActivity extends AppCompatActivity {

    EditText edtPesquisarGoogleBooks;
    Button btnPesquisarGoogleBooks;
    TextView apiResultados;
    RecyclerView rvResultadosApi;
    ProgressBar progressBar;

    List<Item> listaTeste = new ArrayList<>();

    private ResultadosApiAdapter adapter;

    private Retrofit retrofit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_livro);

        findViewsIds();
        setListeners();
        configurarListaResultados();

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
                            adapter.setList(response.body().getItems());
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
        adapter = new ResultadosApiAdapter(listaTeste);
        rvResultadosApi.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AdicionarLivroActivity.this);
        rvResultadosApi.setLayoutManager(layoutManager);
        rvResultadosApi.setHasFixedSize(true);
        rvResultadosApi.setAdapter(adapter);
    }
}