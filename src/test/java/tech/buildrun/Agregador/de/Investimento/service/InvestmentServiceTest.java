package tech.buildrun.Agregador.de.Investimento.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.buildrun.Agregador.de.Investimento.entity.User;
import tech.buildrun.Agregador.de.Investimento.repository.InvestmentRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InvestmentServiceTest {

    @InjectMocks
    InvestmentService investmentService;

    @Mock
    InvestmentRepository investmentRepository;

    @Test
    void createInvestment() {
    }

    @Test
    void getAllInvestments() {
    }

    @Test
    void getInvestimentsByUserId() {
    }

    @Test
    void deleteInvestment() {
    }

    @Test
    void updateInvestment() {
    }
}