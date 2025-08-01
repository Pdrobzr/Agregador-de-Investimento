package tech.buildrun.Agregador.de.Investimento.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.buildrun.Agregador.de.Investimento.exceptions.EmailAlreadyRegisteredException;
import tech.buildrun.Agregador.de.Investimento.exceptions.InvalidPasswordException;
import tech.buildrun.Agregador.de.Investimento.exceptions.UserNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<String> userNotFoundHandler(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    private ResponseEntity<String> invalidPasswordHandler(InvalidPasswordException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    private ResponseEntity<String> emailAlreadyRegisteredHandler(EmailAlreadyRegisteredException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}
