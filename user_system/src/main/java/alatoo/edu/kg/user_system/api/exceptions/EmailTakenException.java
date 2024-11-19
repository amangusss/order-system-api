package alatoo.edu.kg.user_system.api.exceptions;

import org.springframework.http.HttpStatus;

public class EmailTakenException extends BaseException {
    public EmailTakenException() {
        super("Email is already taken", HttpStatus.CONFLICT);
    }
}
