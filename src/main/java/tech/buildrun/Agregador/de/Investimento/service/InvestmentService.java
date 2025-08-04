package tech.buildrun.Agregador.de.Investimento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.buildrun.Agregador.de.Investimento.dto.RecordInvestmentDTO;
import tech.buildrun.Agregador.de.Investimento.dto.ResponseInvestmentsDTO;
import tech.buildrun.Agregador.de.Investimento.entity.Investment;
import tech.buildrun.Agregador.de.Investimento.entity.User;
import tech.buildrun.Agregador.de.Investimento.exceptions.InvestmentNotFoundException;
import tech.buildrun.Agregador.de.Investimento.exceptions.UserNotFoundException;
import tech.buildrun.Agregador.de.Investimento.repository.InvestmentRepository;
import tech.buildrun.Agregador.de.Investimento.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvestmentService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InvestmentRepository investmentRepository;

    public UUID createInvestment(UUID idUser, RecordInvestmentDTO recordInvestmentDTO) {
        Optional<User> user = userRepository.findById(idUser);

        if(user.isEmpty()) {
            throw new UserNotFoundException();
        }

        var newInvestment = new Investment(recordInvestmentDTO.amount(), user.get());

        var createInvestment = investmentRepository.save(newInvestment);

        return createInvestment.getIdInvestment();

    }

    public List<ResponseInvestmentsDTO> getAllInvestments() {
        
        return investmentRepository.findAll().stream().map(investment ->
                new ResponseInvestmentsDTO(investment.getIdInvestment(), investment.getAmount(), investment.getUser().getUserId())).toList();
    }

    public List<ResponseInvestmentsDTO> getInvestimentsByUserId(UUID userId) {
        var getUser = userRepository.findById(userId);
        if(getUser.isPresent()) {
            return getUser.get().getInvestments().stream().map(investment ->
                    new ResponseInvestmentsDTO(investment.getIdInvestment(), investment.getAmount(), investment.getUser().getUserId())).toList();
        } else {
            throw new UserNotFoundException("Usuário não encontrado!");
        }
    }

    public void deleteInvestment(UUID id) {
        boolean investmentExists = investmentRepository.existsById(id);

        if(investmentExists) {
            investmentRepository.deleteById(id);
        } else {
            throw new InvestmentNotFoundException();
        }
    }

    public void updateInvestment(UUID id, RecordInvestmentDTO recordInvestmentDTO) {
        Optional<Investment> investment = investmentRepository.findById(id);

        if(investment.isPresent()) {
            var getInvestment = investment.get();

            Investment updatedInvestment = new Investment(id, recordInvestmentDTO.amount(), getInvestment.getUser());

            investmentRepository.save(updatedInvestment);
        } else {
            throw  new InvestmentNotFoundException();
        }

    }
}
