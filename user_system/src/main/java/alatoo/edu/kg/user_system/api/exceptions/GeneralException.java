package alatoo.edu.kg.user_system.api.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralException extends BaseException {
    public GeneralException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
