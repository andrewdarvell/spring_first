package ru.darvell.gb.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ShopException {

    public UserNotFoundException(){
        super("User not found");
    }

    public UserNotFoundException(String s){
        super(s);
    }
}
