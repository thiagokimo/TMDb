package io.kimo.tmdb.domain.entity;


import java.util.List;

public class ConfigurationEntity {
    private String base_url;
    private List<String> poster_sizes;

    public String getBase_url() {
        return base_url;
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public List<String> getPoster_sizes() {
        return poster_sizes;
    }

    public void setPoster_sizes(List<String> poster_sizes) {
        this.poster_sizes = poster_sizes;
    }
}
