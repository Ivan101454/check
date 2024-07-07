package ru.clevertec.check.exception;

public class CustomException extends Exception {

    public CustomException(TextErrorException textErrorException) {
        super(textErrorException.name());
    }
}
