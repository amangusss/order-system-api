package alatoo.edu.kg.user_system.api.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameTakenException extends BaseException {
    public UsernameTakenException() {
        super("Username is already taken", HttpStatus.CONFLICT);
    }
}
