package tech.buildrun.Agregador.de.Investimento.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordDTO(@NotBlank String oldPassword, @NotBlank String password) {
}
