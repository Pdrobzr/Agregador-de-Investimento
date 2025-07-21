package tech.buildrun.Agregador.de.Investimento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(@NotBlank String userName, @NotNull String email) {
}
