package alatoo.edu.kg.order_system.api.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {
    protected HttpStatus httpStatus;

    public BaseException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

