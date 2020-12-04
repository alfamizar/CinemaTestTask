package com.test.task.moviestesttask.search;

import android.content.Context;

import com.test.task.moviestesttask.R;
import com.test.task.moviestesttask.models.MoviesResponse;
import com.test.task.moviestesttask.network.NetworkClient;
import com.test.task.moviestesttask.network.NetworkInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter implements SearchPresenterInterface {

    private final SearchViewInterface searchviewInterface;
    private NetworkInterface networkInterface;

    public SearchPresenter(SearchViewInterface searchViewInterface) {
        this.searchviewInterface = searchViewInterface;
    }

    @Override
    public void getSearchResultsBasedOnQuery(Context context, String s) {
        networkInterface = NetworkClient.getRetrofit();
        makeRequest(context, s);

    }

    public void makeRequest(Context context, String s) {
        Call<MoviesResponse> call = networkInterface.getMoviesBasedOnQuery(context.getResources().getString(R.string.key_api), s);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                searchviewInterface.displaySearchResult(response.body());
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {

            }
        });
    }
}