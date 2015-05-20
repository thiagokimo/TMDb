package io.kimo.themoviedb.domain;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapper<E, M> {
    protected Gson gson = new Gson();

    public abstract M toModel(E entity);

    public List<M> toModels(List<E> entities) {
        List<M> models = new ArrayList<>();

        for(E e : entities) {
            models.add(toModel(e));
        }

        return models;
    }

    public String serializeModel(M model) {
        return gson.toJson(model);
    }

    public abstract M deserializeModel(String serializedModel);
}
