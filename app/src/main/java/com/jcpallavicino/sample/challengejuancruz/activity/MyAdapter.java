package com.jcpallavicino.sample.challengejuancruz.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcpallavicino.sample.challengejuancruz.R;
import com.jcpallavicino.sample.challengejuancruz.utils.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by juan.pallavicino on 26/9/2017.
 */



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Book> list;
    private ArrayList<Book> galleryList;
    private Context context;


    public MyAdapter(Context context, ArrayList<Book> galleryList) {
        this.galleryList = galleryList;
        this.context = context;

    }

    public void swap(List<Book> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();

    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        Picasso.with(this.context)
                .load(galleryList.get(i).getImageURL())
                .fit()
                .into(viewHolder.img);

        viewHolder.title.setText(galleryList.get(i).getTitle());
        viewHolder.author.setText(galleryList.get(i).getAuthor());

    }


    @Override
    public int getItemCount() {
        return galleryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title, author;
        private ImageView img;

        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.album_name);
            author = (TextView) view.findViewById(R.id.artist_name);
            img = (ImageView) view.findViewById(R.id.img);

        }


    }


}
