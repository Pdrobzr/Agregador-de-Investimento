package tech.buildrun.Agregador.de.Investimento.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseUserDTO(UUID userId, String userName, String email, String password, LocalDateTime createdAt, String roleName) {
}
