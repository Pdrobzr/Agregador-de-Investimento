package tech.buildrun.Agregador.de.Investimento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecordUserDTO(@NotBlank String userName, @NotNull String email, @NotBlank String password) {
}
