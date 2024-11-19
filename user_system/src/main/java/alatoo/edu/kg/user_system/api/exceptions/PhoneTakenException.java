package alatoo.edu.kg.user_system.api.exceptions;

import org.springframework.http.HttpStatus;

public class PhoneTakenException extends BaseException {
    public PhoneTakenException() {
        super("Phone number is already taken", HttpStatus.CONFLICT);
    }
}
