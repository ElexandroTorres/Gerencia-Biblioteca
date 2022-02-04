package com.imd0509.gerenciabiblioteca.service;

import com.imd0509.gerenciabiblioteca.model.Livro;
import com.imd0509.gerenciabiblioteca.model.apiresponse.Root;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IBookService {
    @GET("volumes?q=os%20miseraveis&key=AIzaSyDZbm_ihbpZND2KVvoc64DcebeDjyWST4Q#")
    Call<Root> pesquisarLivro();
}
