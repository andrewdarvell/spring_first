package ru.darvell.gb.spring.exception;

public class ShopException extends RuntimeException{

    public ShopException() {
        super();
    }

    public ShopException(String message) {
        super(message);
    }

    public ShopException(Throwable cause) {
        super(cause);
    }
}
