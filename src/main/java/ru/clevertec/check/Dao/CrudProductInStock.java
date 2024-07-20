package ru.clevertec.check.Dao;

import java.util.Optional;

public interface CrudProductInStock<E, T> {
    public Optional<E> findById(T id);
    public void update(E e);
    public E save(E e);
    public boolean delete(T id);
}
