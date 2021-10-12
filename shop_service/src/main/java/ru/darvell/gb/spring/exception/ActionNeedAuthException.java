package ru.darvell.gb.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ActionNeedAuthException extends ShopException {

    private static final String MESSAGE = "this action required auth user";

    public ActionNeedAuthException() {
        super(MESSAGE);
    }

    public ActionNeedAuthException(String message) {
        super(message);
    }

    public ActionNeedAuthException(Throwable cause) {
        super(cause);
    }

    public ActionNeedAuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
