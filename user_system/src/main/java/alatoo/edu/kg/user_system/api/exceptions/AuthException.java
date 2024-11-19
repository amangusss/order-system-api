package alatoo.edu.kg.user_system.api.exceptions;

import org.springframework.http.HttpStatus;

public class AuthException extends BaseException {
    public AuthException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
