package com.test.task.moviestesttask.detailed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.task.moviestesttask.genre.GenrePresenter;
import com.test.task.moviestesttask.R;
import com.test.task.moviestesttask.models.Genre;
import com.test.task.moviestesttask.models.SingleMovie;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DetailedActivity extends AppCompatActivity implements DetailedViewInterface {

    private SingleMovie selectedMovie;
    private GenrePresenter genrePresenter;
    private DetailedPresenter detailedPresenter;

    private ImageView posterImageView;
    private TextView overviewTextView, dateTextView, genresTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Intent intentIGot = getIntent();

        selectedMovie = (SingleMovie) intentIGot.getSerializableExtra("movie");

        setupMVP();
        setupViews();
        getGenreList();
        getDetails();
    }

    private void getDetails() {
        detailedPresenter.getDetails();
    }


    private void setupMVP() {
        genrePresenter = new GenrePresenter(this);
        detailedPresenter = new DetailedPresenter(this);
    }

    private void setupViews() {
        posterImageView = findViewById(R.id.posterImageView);
        overviewTextView = findViewById(R.id.overviewTextView);
        dateTextView = findViewById(R.id.dateTextView);
        genresTextView = findViewById(R.id.genresTextView);
    }

    private void getGenreList() {
        genrePresenter.getGenres(getApplicationContext());
    }


    @Override
    public void displayDetails() {
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + selectedMovie.getPosterPath()).into(posterImageView);
        overviewTextView.setText(String.format(Locale.US, "%s%s", getString(R.string.overview), selectedMovie.getOverview()));
        dateTextView.setText(String.format(Locale.US, "%s%s", getString(R.string.releaseDate), selectedMovie.getReleaseDate()));
    }

    public void initGenres(List<Genre> genreList) {

        Map<Integer, String> genreMap = new HashMap<>();

        for (int i = 0; i < genreList.size(); i++) {
            genreMap.put(genreList.get(i).getId(), genreList.get(i).getName() + " ");
        }
        StringBuilder genresString = new StringBuilder();
        List<Integer> genresIds = selectedMovie.getGenreIds();
        for (Integer i : genresIds) {
            genresString.append(genreMap.get(i));
        }
        genresTextView.setText(String.format(Locale.US, "%s%s", getString(R.string.genre), genresString.toString()));
    }
}