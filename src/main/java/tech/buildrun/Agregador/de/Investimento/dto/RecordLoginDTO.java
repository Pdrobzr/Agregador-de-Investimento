package tech.buildrun.Agregador.de.Investimento.dto;

import jakarta.validation.constraints.NotBlank;

public record RecordLoginDTO(@NotBlank String email, @NotBlank String password) {
}
