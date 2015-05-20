package io.kimo.themoviedb.domain.service.response;

import java.util.List;

import io.kimo.themoviedb.domain.entity.MovieEntity;

public class SearchMovieResponse {
    private List<MovieEntity> results;

    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }
}
