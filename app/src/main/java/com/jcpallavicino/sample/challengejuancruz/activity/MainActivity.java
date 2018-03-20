package com.jcpallavicino.sample.challengejuancruz.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jcpallavicino.sample.challengejuancruz.R;
import com.jcpallavicino.sample.challengejuancruz.utils.Book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Book> resultForAdapter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListener listener = new MyListener(MainActivity.this);
        listener.setListener(new MyListener.MyInterfaceListener() {
            @Override
            public void onObjectReady(String title) {

            }

            @Override
            public void onDataLoaded(ArrayList<Book> data) {
                getDataUsingRetrofit(data);
            }
        });
    }

    private void getDataUsingRetrofit(ArrayList<Book> data) {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.imagegallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        resultForAdapter = data;
        MyAdapter view = new MyAdapter(MainActivity.this, resultForAdapter );
        recyclerView.setAdapter(view);
    }
}
