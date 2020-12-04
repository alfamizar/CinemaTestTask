package com.test.task.moviestesttask.search;

import android.content.Context;

import androidx.appcompat.widget.SearchView;

public interface SearchPresenterInterface {

    void getSearchResultsBasedOnQuery(Context context, String s);
}

