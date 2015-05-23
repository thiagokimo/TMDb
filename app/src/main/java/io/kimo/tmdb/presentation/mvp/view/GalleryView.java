package io.kimo.tmdb.presentation.mvp.view;

import java.util.List;

import io.kimo.tmdb.presentation.mvp.model.ImageModel;

public interface GalleryView extends LoadDataView {
    void renderImages(List<ImageModel> images);
    void clearImages();
}
