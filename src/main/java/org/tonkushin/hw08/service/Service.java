package org.tonkushin.hw08.service;

import java.util.List;

public interface Service<T> {
    T insert(T item);

    T getById(String id) throws Exception;

    List<T> getAll();

    void deleteById(String id) throws Exception;
}
