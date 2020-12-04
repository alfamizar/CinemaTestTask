package com.test.task.moviestesttask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResponse {

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<SingleMovie> singleMovies = null;

    public MoviesResponse() {
    }

    public MoviesResponse(Integer page, Integer totalResults, Integer totalPages, List<SingleMovie> singleMovies) {
        super();
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.singleMovies = singleMovies;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<SingleMovie> getSingleMovies() {
        return singleMovies;
    }

    public void setSingleMovies(List<SingleMovie> singleMovies) {
        this.singleMovies = singleMovies;
    }

}