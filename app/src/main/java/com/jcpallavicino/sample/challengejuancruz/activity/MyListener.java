package com.jcpallavicino.sample.challengejuancruz.activity;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jcpallavicino.sample.challengejuancruz.utils.Book;
import com.jcpallavicino.sample.challengejuancruz.utils.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by juan.pallavicino on 18/3/2018.
 */

public class MyListener {
    private Context context;

    public interface MyInterfaceListener{
        // These methods are the different events and
        // need to pass relevant arguments related to the event triggered
        public void onObjectReady(String title);
        // or when data has been loaded
        public void onDataLoaded(ArrayList<Book> data);
    }

    private MyInterfaceListener listener;

    public MyListener(Context context){
        this.listener = null;
        this.context = context;

    }

    public void setListener(MyInterfaceListener listener) {
        this.listener = listener;
        loadJSON();
    }

    private void loadJSON(){
        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://de-coding-test.s3.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RestClient restClient = retrofit.create(RestClient.class);
        Call<List<Book>> call = restClient.getData();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                switch (response.code()) {
                    case 200:
                        Log.i("REST SUCCESS", "Responce Code 200");

                        ArrayList<Book> data = (ArrayList<Book>) response.body();
                        listener.onDataLoaded(data);

                        break;
                    case 401:

                        break;
                    default:

                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.e("REST ERROR", t.toString());
                Toast.makeText(context, "ERROR EN EL SERVICIO, INTENTE MAS TARDE", Toast.LENGTH_LONG).show();
            }
        });
    }
}
