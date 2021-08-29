package ru.darvell.gb.spring.exception;

public class ShopProductException extends RuntimeException{

    public ShopProductException() {
        super();
    }

    public ShopProductException(String message) {
        super(message);
    }

    public ShopProductException(Throwable cause) {
        super(cause);
    }
}
