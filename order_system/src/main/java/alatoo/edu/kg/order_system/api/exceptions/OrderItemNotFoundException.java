package alatoo.edu.kg.order_system.api.exceptions;

import org.springframework.http.HttpStatus;

public class OrderItemNotFoundException extends BaseException{
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.NOT_FOUND;

    public OrderItemNotFoundException(Long id) {
        super("Order item with ID " + id + " is not found", DEFAULT_STATUS);
    }
}
