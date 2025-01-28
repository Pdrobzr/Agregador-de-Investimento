package tech.buildrun.Agregador.de.Investimento.dto;

import jakarta.validation.constraints.NotBlank;

public record RecordInvestmentDTO(@NotBlank Double amount) {
}
