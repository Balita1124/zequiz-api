package com.zequiz.shared.service;

import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface RestFullService<T, D> {
    public Page<T> getAll(Integer page, Integer size, String sort);
    public T create(D object);
    public T update(T entity, D dto);
    public Optional<T> findById(UUID id);
    public void delete(T entity);
}
