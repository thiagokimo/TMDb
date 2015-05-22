package io.kimo.tmdb.domain.mapper;


import io.kimo.tmdb.Utils;
import io.kimo.tmdb.domain.BaseMapper;
import io.kimo.tmdb.domain.entity.MovieEntity;
import io.kimo.tmdb.presentation.model.MovieModel;

public class MovieMapper extends BaseMapper<MovieEntity, MovieModel> {

    @Override
    public MovieModel toModel(MovieEntity entity) {
        MovieModel movieModel = new MovieModel();

        movieModel.setName(entity.getTitle());
        movieModel.setYearOfRelease(Utils.getYearFromServerDate(entity.getRelease_date()));
        movieModel.setCover(Utils.buildCompleteImageURL(entity.getPoster_path()));

        return movieModel;
    }

    @Override
    public MovieModel deserializeModel(String serializedModel) {
        return gson.fromJson(serializedModel, MovieModel.class);
    }
}
