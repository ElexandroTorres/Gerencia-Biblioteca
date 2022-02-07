package com.imd0509.gerenciabiblioteca.service;

import com.imd0509.gerenciabiblioteca.model.apiresponse.Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IBookService {
    //@GET("volumes?q={pesquisa}&key=AIzaSyDZbm_ihbpZND2KVvoc64DcebeDjyWST4Q#")
    //Call<Root> pesquisarLivro(@Query("pesquisa") String pesquisa);
    @GET("volumes")
    Call<Root> pesquisarLivro(@Query("q") String pesquisa);
}
