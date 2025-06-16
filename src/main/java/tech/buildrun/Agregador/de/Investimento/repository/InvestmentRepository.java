package tech.buildrun.Agregador.de.Investimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.buildrun.Agregador.de.Investimento.entity.Investment;
import tech.buildrun.Agregador.de.Investimento.entity.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, UUID> {
    List<Investment> findByUser(User user);
}

