package com.test.task.moviestesttask.main;

import android.content.Context;

import com.test.task.moviestesttask.R;
import com.test.task.moviestesttask.models.MoviesResponse;
import com.test.task.moviestesttask.network.NetworkClient;
import com.test.task.moviestesttask.network.NetworkInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainPresenterInterface {

    private final MainViewInterface mvi;

    public MainPresenter(MainViewInterface mvi) {
        this.mvi = mvi;
    }

    @Override
    public void getMovies(Context context) {
        NetworkInterface networkInterface = NetworkClient.getRetrofit();
        Call<MoviesResponse> call = networkInterface.getMovies(context.getResources().getString(R.string.key_api));
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                mvi.displayMovies(response.body());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
            }
        });
    }
}