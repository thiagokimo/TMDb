package io.kimo.tmdb.domain.service.response;


import io.kimo.tmdb.domain.entity.ConfigurationEntity;

public class GetImageConfigurationResponse {

    private ConfigurationEntity images;

    public ConfigurationEntity getImages() {
        return images;
    }

    public void setImages(ConfigurationEntity images) {
        this.images = images;
    }
}
