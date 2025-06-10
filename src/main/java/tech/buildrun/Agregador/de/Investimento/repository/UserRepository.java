package tech.buildrun.Agregador.de.Investimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.Agregador.de.Investimento.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

}
