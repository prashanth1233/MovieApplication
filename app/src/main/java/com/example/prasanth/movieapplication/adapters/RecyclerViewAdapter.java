package com.example.prasanth.movieapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.prasanth.movieapplication.R;
import com.example.prasanth.movieapplication.moviemodels.MoviesModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<MoviesModel> moviesData;
    private View.OnClickListener listener;

    public RecyclerViewAdapter(Context context, List<MoviesModel> moviesData, View.OnClickListener listener) {
        this.context = context;

        this.moviesData = moviesData;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_layout, null, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int image = moviesData.get(position).getMovieImage();
        Picasso.with(context).load(image).into(holder.imageView);
        holder.imageView.setTag(position);


    }

    @Override
    public int getItemCount() {
        return moviesData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.images);
            imageView.setOnClickListener(listener);

        }
    }
}
