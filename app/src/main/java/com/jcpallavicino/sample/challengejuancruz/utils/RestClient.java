package com.jcpallavicino.sample.challengejuancruz.utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by juan.pallavicino on 20/3/2018.
 */

public interface RestClient {
    @GET("books.json")
    Call<List<Book>> getData();

}
