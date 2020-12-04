package com.test.task.moviestesttask.genre;

import android.content.Context;

import com.test.task.moviestesttask.R;
import com.test.task.moviestesttask.detailed.DetailedActivity;
import com.test.task.moviestesttask.models.Genres;
import com.test.task.moviestesttask.network.NetworkClient;
import com.test.task.moviestesttask.network.NetworkInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenrePresenter implements GenrePresenterInterface {

    private final DetailedActivity detailedActivity;

    public GenrePresenter(DetailedActivity detailedActivity) {
        this.detailedActivity = detailedActivity;
    }

    @Override
    public void getGenres(Context context) {

        NetworkInterface networkInterface = NetworkClient.getRetrofit();
        Call<Genres> call = networkInterface.getGenres(context.getResources().getString(R.string.key_api));
        call.enqueue(new Callback<Genres>() {
            @Override
            public void onResponse(Call<Genres> call, Response<Genres> response) {

                detailedActivity.initGenres(response.body().getGenres());
            }

            @Override
            public void onFailure(Call<Genres> call, Throwable t) {

            }
        });
    }
}