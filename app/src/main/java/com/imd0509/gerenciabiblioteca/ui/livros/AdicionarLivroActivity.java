package com.imd0509.gerenciabiblioteca.ui.livros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.imd0509.gerenciabiblioteca.R;
import com.imd0509.gerenciabiblioteca.model.Livro;
import com.imd0509.gerenciabiblioteca.model.apiresponse.Root;
import com.imd0509.gerenciabiblioteca.service.Api;
import com.imd0509.gerenciabiblioteca.service.IBookService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdicionarLivroActivity extends AppCompatActivity {

    EditText edtPesquisarGoogleBooks;
    Button btnPesquisarGoogleBooks;
    TextView apiResultados;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_livro);

        retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        findViewsIds();
        setListeners();
    }

    private void findViewsIds() {
        edtPesquisarGoogleBooks = findViewById(R.id.edt_pesquisar_google_books);
        btnPesquisarGoogleBooks = findViewById(R.id.btn_pesquisar_google_books);
        apiResultados = findViewById(R.id.api_resultados);
    }

    private void setListeners() {
        btnPesquisarGoogleBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IBookService bookService = retrofit.create(IBookService.class);
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
}