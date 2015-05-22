package io.kimo.tmdb.domain.service.response;


import io.kimo.tmdb.domain.entity.ImageConfigurationEntity;

public class GetConfigurationResponse {

    private ImageConfigurationEntity images;

    public ImageConfigurationEntity getImages() {
        return images;
    }

    public void setImages(ImageConfigurationEntity images) {
        this.images = images;
    }
}
