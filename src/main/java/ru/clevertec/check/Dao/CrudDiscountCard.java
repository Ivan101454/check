package ru.clevertec.check.Dao;

import java.util.Optional;

public interface CrudDiscountCard<E, T, N> {
    public Optional<E> findById(T id);
    public Optional<E> findByNumber(N number);
    public void update(E e);
    public E save(E e);
    public boolean delete(T id);
}
