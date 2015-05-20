package io.kimo.themoviedb.domain.mapper;


import io.kimo.themoviedb.Utils;
import io.kimo.themoviedb.domain.BaseMapper;
import io.kimo.themoviedb.domain.entity.MovieEntity;
import io.kimo.themoviedb.presentation.model.MovieModel;

public class MovieMapper extends BaseMapper<MovieEntity, MovieModel>{

    @Override
    public MovieModel toModel(MovieEntity entity) {
        MovieModel movieModel = new MovieModel();

        movieModel.setName(entity.getTitle());
        movieModel.setCover(Utils.buildCompleteImageURL(entity.getPoster_path()));

        return movieModel;
    }

    @Override
    public MovieModel deserializeModel(String serializedModel) {
        return gson.fromJson(serializedModel, MovieModel.class);
    }
}
