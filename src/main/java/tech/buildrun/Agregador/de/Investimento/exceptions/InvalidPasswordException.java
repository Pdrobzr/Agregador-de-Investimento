package tech.buildrun.Agregador.de.Investimento.exceptions;

public class InvalidPasswordException extends RuntimeException{

    public InvalidPasswordException() {
        super("Erro! Senha inválida!");
    }
}
