package com.imd0509.gerenciabiblioteca.ui.livros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.adapters.LivrosAdapter;
import com.imd0509.gerenciabiblioteca.adapters.ResultadosApiAdapter;
import com.imd0509.gerenciabiblioteca.model.apiresponse.Root;
import com.imd0509.gerenciabiblioteca.network.GoogleBooksApi;
import com.imd0509.gerenciabiblioteca.network.IBookService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdicionarLivroActivity extends AppCompatActivity {

    EditText edtPesquisarGoogleBooks;
    Button btnPesquisarGoogleBooks;
    TextView apiResultados;
    RecyclerView rvResultadosApi;

    private ResultadosApiAdapter adapter;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_livro);

        findViewsIds();
        setListeners();


    }

    private void findViewsIds() {
        edtPesquisarGoogleBooks = findViewById(R.id.adicionar_livro_edt_pesquisar_google_books);
        btnPesquisarGoogleBooks = findViewById(R.id.adicionar_livro_btn_pesquisar_google_books);
        rvResultadosApi = findViewById(R.id.adicionar_livro_rv_resultados_api);
    }

    private void setListeners() {
        btnPesquisarGoogleBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IBookService bookService = GoogleBooksApi.getClient().create(IBookService.class);
                Call<Root> resultado = bookService.pesquisarLivro(edtPesquisarGoogleBooks.getText().toString());

                resultado.enqueue(new Callback<Root>() {
                    @Override
                    public void onResponse(Call<Root> call, Response<Root> response) {
                        if(response.isSuccessful()) {
                            apiResultados.setText(response.body().toString());
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
        adapter = new ResultadosApiAdapter(/*LISTA DE RESULTADOS*/);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AdicionarLivroActivity.this);
        rvResultadosApi.setLayoutManager(layoutManager);
        rvResultadosApi.setHasFixedSize(true);
        rvResultadosApi.setAdapter(adapter);
    }
}