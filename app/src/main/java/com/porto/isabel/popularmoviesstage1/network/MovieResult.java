package com.porto.isabel.popularmoviesstage1.network;

import com.google.gson.annotations.SerializedName;
import com.porto.isabel.popularmoviesstage1.model.moviedb.Movie;

import java.io.Serializable;
import java.util.List;

/**
 * Created by belporto on 11/06/2017.
 */

public class MovieResult implements Serializable {

    @SerializedName("page")
    private Integer page;

    @SerializedName("total_results")
    private Integer totalResults;

    @SerializedName("total_pages")
    private Integer totalPages;

    @SerializedName("results")
    private List<Movie> results;


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

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}