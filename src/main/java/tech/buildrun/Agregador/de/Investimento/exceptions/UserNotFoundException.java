package tech.buildrun.Agregador.de.Investimento.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("Id de usuário não encontrado!");
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
