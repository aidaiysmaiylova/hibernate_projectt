package org.peaksoft.service;

import org.peaksoft.model.entities.StudentIdCard;

import java.util.List;

public interface Service<T> {
    void create(T t);

    void update(T t , Long id);

    List<T> getAll();

    T getById(Long id);

    String deleteById(Long id);
}
