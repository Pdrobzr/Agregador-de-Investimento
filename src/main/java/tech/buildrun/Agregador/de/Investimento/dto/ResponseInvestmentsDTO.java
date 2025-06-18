package tech.buildrun.Agregador.de.Investimento.dto;

import java.util.UUID;

public record ResponseInvestmentsDTO(UUID idInvestment, Double amount, UUID userId) {
}
