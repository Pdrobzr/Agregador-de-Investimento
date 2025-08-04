package tech.buildrun.Agregador.de.Investimento.exceptions;

public class InvestmentNotFoundException extends RuntimeException{

    public InvestmentNotFoundException() {
        super("Investimento não encontrado!");
    }
}
