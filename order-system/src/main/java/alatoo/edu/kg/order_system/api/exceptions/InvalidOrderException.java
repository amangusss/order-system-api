package alatoo.edu.kg.order_system.api.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidOrderException extends BaseException {
    private static final String DEFAULT_MESSAGE = "Invalid order.";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.BAD_REQUEST;

    public InvalidOrderException() {
        super(DEFAULT_MESSAGE, DEFAULT_STATUS);
    }

    public InvalidOrderException(String message) {
        super(message, DEFAULT_STATUS);
    }
}
