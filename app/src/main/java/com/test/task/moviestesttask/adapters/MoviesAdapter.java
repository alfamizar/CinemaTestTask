package com.test.task.moviestesttask.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.task.moviestesttask.R;
import com.test.task.moviestesttask.models.SingleMovie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder> {

    private final List<SingleMovie> movieList;
    private final Context context;
    private View.OnClickListener onItemClickListener;

    public MoviesAdapter(List<SingleMovie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }


    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    @Override
    public MoviesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_card_view, parent, false);
        return new MoviesHolder(v);
    }


    @Override
    public void onBindViewHolder(MoviesHolder holder, int position) {

        holder.tvTitle.setText(movieList.get(position).getTitle());
        holder.tvOverview.setText(movieList.get(position).getOverview());
        holder.singleMovie = movieList.get(position);
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + movieList.get(position).getPosterPath()).into(holder.ivMovie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MoviesHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvOverview;
        ImageView ivMovie;


        public SingleMovie getSingleMovie() {
            return singleMovie;
        }

        SingleMovie singleMovie;

        public MoviesHolder(View v) {
            super(v);
            v.setTag(this);
            v.setOnClickListener(onItemClickListener);
            tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            tvOverview = (TextView) v.findViewById(R.id.tvOverView);
            ivMovie = (ImageView) v.findViewById(R.id.ivMovie);
        }
    }
}