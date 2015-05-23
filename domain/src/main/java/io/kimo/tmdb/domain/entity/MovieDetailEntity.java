package io.kimo.tmdb.domain.entity;

import java.util.List;

public class MovieDetailEntity {

    private String homepage;
    private List<CompanyEntity> production_companies;
    private String tagline;
    private String overview;

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public List<CompanyEntity> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<CompanyEntity> production_companies) {
        this.production_companies = production_companies;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
}
