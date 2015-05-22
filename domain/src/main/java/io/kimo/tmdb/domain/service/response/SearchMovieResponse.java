package io.kimo.tmdb.domain.service.response;

import java.util.List;

import io.kimo.tmdb.domain.entity.MovieEntity;

public class SearchMovieResponse {
    private List<MovieEntity> results;

    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }
}
