package io.kimo.themoviedb.domain.service.response;


import io.kimo.themoviedb.domain.entity.ImageConfigurationEntity;

public class GetConfigurationResponse {

    private ImageConfigurationEntity images;

    public ImageConfigurationEntity getImages() {
        return images;
    }

    public void setImages(ImageConfigurationEntity images) {
        this.images = images;
    }
}
