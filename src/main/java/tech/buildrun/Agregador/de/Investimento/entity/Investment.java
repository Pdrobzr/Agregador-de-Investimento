package tech.buildrun.Agregador.de.Investimento.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_investments")
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idInvestment;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;


    public Investment(UUID idInvestment, Double amount, User user) {
        this.idInvestment = idInvestment;
        this.amount = amount;
        this.user = user;
    }

    public Investment(Double amount, User user) {
        this.amount = amount;
        this.user = user;
    }

    public Investment() {

    }


    public UUID getIdInvestment() {
        return idInvestment;
    }

    public void setIdInvestment(UUID idInvestment) {
        this.idInvestment = idInvestment;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
