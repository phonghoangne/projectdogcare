package com.app.service;

import com.app.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface BaseModel <T,ID>{
    List<T> findAll();
    T save(T id);
    T update(T id);
    void delete(ID id);
    Optional<T> findById(ID id);

}
