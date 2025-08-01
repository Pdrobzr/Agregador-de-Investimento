package tech.buildrun.Agregador.de.Investimento.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException{

    public EmailAlreadyRegisteredException() {
        super("Erro! Email já cadastrado!");
    }
}
