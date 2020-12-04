package com.test.task.moviestesttask.network;

import com.test.task.moviestesttask.models.Genres;
import com.test.task.moviestesttask.models.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkInterface {

    @GET("genre/movie/list")
    Call<Genres> getGenres(@Query("api_key") String api_key);

    @GET("trending/movie/week")
    Call<MoviesResponse> getMovies(@Query("api_key") String api_key);

    @GET("search/movie")
    Call<MoviesResponse> getMoviesBasedOnQuery(@Query("api_key") String api_key, @Query("query") String q);
}