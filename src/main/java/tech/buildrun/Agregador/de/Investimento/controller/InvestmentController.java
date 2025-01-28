package tech.buildrun.Agregador.de.Investimento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.Agregador.de.Investimento.dto.RecordInvestmentDTO;
import tech.buildrun.Agregador.de.Investimento.entity.Investment;
import tech.buildrun.Agregador.de.Investimento.entity.User;
import tech.buildrun.Agregador.de.Investimento.service.InvestmentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    @Autowired
    InvestmentService investmentService;

    @PostMapping("/{idUser}")
    public ResponseEntity<UUID> createInvestment(@PathVariable UUID idUser, @RequestBody RecordInvestmentDTO recordInvestmentDTO) {

        var createInvestiment = investmentService.createInvestment(idUser, recordInvestmentDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createInvestiment);
    }

    @GetMapping
    public ResponseEntity<List<Investment>> getAllInvestments() {
        List<Investment> investments = investmentService.getAllInvestments();

        return ResponseEntity.status(HttpStatus.OK).body(investments);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<List<Investment>> getInvestmentsByUserId(@PathVariable UUID idUser) {
        List<Investment> investments = investmentService.getInvestimentsByUserId(idUser);

        return ResponseEntity.status(HttpStatus.OK).body(investments);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Object> deleteInvestment(@PathVariable UUID idUser) {
        investmentService.deleteInvestment(idUser);

        return ResponseEntity.status(HttpStatus.OK).body("Investimento deletado com sucesso!");
    }

    @PutMapping("/{idInvestment}")
    public ResponseEntity<Object> updateInvestment(@PathVariable UUID idInvestment, @RequestBody RecordInvestmentDTO recordInvestmentDTO) {
        investmentService.updateInvestment(idInvestment, recordInvestmentDTO);

        return ResponseEntity.status(HttpStatus.OK).body("Investimento atualizado com sucesso!");
    }
}
