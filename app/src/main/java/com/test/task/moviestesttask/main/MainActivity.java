package com.test.task.moviestesttask.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.test.task.moviestesttask.detailed.DetailedActivity;
import com.test.task.moviestesttask.R;
import com.test.task.moviestesttask.search.SearchPresenter;
import com.test.task.moviestesttask.search.SearchViewInterface;
import com.test.task.moviestesttask.adapters.MoviesAdapter;
import com.test.task.moviestesttask.models.MoviesResponse;


public class MainActivity extends AppCompatActivity implements MainViewInterface, SearchViewInterface {

    private final View.OnClickListener onItemClickListener = view -> {

        MoviesAdapter.MoviesHolder viewHolder = (MoviesAdapter.MoviesHolder) view.getTag();

        Intent intent = new Intent(MainActivity.this, DetailedActivity.class);

        intent.putExtra("movie", viewHolder.getSingleMovie());
        startActivity(intent);
        overridePendingTransition(0, 0);
    };

    private RecyclerView rvMovies;
    private Toolbar toolbar;
    private MainPresenter mainPresenter;
    private SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setupMVP();
        setupViews();
        getMovieList();

        setSupportActionBar(toolbar);
    }


    private void setupMVP() {
        mainPresenter = new MainPresenter(this);
        searchPresenter = new SearchPresenter(this);
    }

    private void setupViews() {
        rvMovies = findViewById(R.id.rvMovies);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        toolbar = findViewById(R.id.myToolbar);
        EditText editText = findViewById(R.id.editText);
        editText.setCursorVisible(true);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchPresenter.getSearchResultsBasedOnQuery(getApplicationContext(), s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getMovieList() {
        mainPresenter.getMovies(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displaySearchResult(MoviesResponse moviesResponse) {
        if (moviesResponse == null) {
            getMovieList();
        } else {
            initAdapter(moviesResponse);
        }
    }

    @Override
    public void displayMovies(MoviesResponse moviesResponse) {
        if (moviesResponse != null) {
            initAdapter(moviesResponse);
        }
    }

    private void initAdapter(MoviesResponse moviesResponse) {
        MoviesAdapter adapter = new MoviesAdapter(moviesResponse.getSingleMovies(), MainActivity.this);
        adapter.setItemClickListener(onItemClickListener);
        rvMovies.setAdapter(adapter);

    }
}