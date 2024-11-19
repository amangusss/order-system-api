package alatoo.edu.kg.order_system.api.exceptions;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends BaseException{
    private static final String DEFAULT_MESSAGE = "Order is not found";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.NOT_FOUND;

    public OrderNotFoundException() {
        super(DEFAULT_MESSAGE, DEFAULT_STATUS);
    }

    public OrderNotFoundException(Long id) {
        super("Order with ID " + id + " is not found.", DEFAULT_STATUS);
    }
}
