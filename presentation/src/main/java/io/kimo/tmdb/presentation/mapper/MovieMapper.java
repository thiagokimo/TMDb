package io.kimo.tmdb.presentation.mapper;


import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import io.kimo.tmdb.domain.BaseMapper;
import io.kimo.tmdb.domain.entity.CompanyEntity;
import io.kimo.tmdb.domain.entity.MovieDetailEntity;
import io.kimo.tmdb.domain.entity.MovieEntity;
import io.kimo.tmdb.presentation.Utils;
import io.kimo.tmdb.presentation.mvp.model.MovieModel;

public class MovieMapper extends BaseMapper<MovieEntity, MovieModel> {

    @Override
    public MovieModel toModel(MovieEntity entity) {
        MovieModel movieModel = new MovieModel();

        movieModel.setId(entity.getId());
        movieModel.setName(entity.getTitle());
        movieModel.setYearOfRelease(Utils.getYearFromServerDate(entity.getRelease_date()));

        if(!TextUtils.isEmpty(entity.getPoster_path())) {
            movieModel.setSmallCover(Utils.buildCompleteImageURL(entity.getPoster_path(), "w154"));
            movieModel.setBigCover(Utils.buildCompleteImageURL(entity.getPoster_path(), "original"));
        }

        return movieModel;
    }

    @Override
    public MovieModel deserializeModel(String serializedModel) {
        return gson.fromJson(serializedModel, MovieModel.class);
    }

    public MovieModel addDetails(MovieModel receiver, MovieDetailEntity detailsToBeInserted) {

        receiver.setHomepage(detailsToBeInserted.getHomepage());

        List<String> companies = new ArrayList<>();
        for(CompanyEntity company : detailsToBeInserted.getProduction_companies()) {
            companies.add(company.getName());
        }
        //remove the first and the last character
        receiver.setCompanies(companies.toString().substring(1,companies.toString().length()-1));

        receiver.setTagline(detailsToBeInserted.getTagline());
        receiver.setOverview(detailsToBeInserted.getOverview());

        return receiver;
    }
}
