package com.imd0509.gerenciabiblioteca.network;

import com.imd0509.gerenciabiblioteca.model.apiresponse.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IBookService {
    @GET("volumes")
    Call<Root> pesquisarLivro(@Query("q") String pesquisa);
}
