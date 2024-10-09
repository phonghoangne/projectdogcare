package com.app.service;

import java.util.List;

public interface BaseModel <T,ID>{
    List<T> findAll();
    T save(T id);
    T update(T id);
    void delete(ID id);
    T findById(ID id);
}
