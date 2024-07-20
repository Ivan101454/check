package ru.clevertec.check.exception;

public class DaoException extends RuntimeException{
    public DaoException(Throwable throwable) {
        super(throwable);
    }
}
