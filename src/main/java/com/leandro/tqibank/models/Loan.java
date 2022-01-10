package com.leandro.tqibank.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal value;
    private LocalDate firstInstallmentDate;
    private Integer numberOfInstallments;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Loan() {
    }

    public Loan(BigDecimal value, LocalDate firstInstallmentDate, Integer numberOfInstallments, Client client) {
        this.value = value;
        this.firstInstallmentDate = firstInstallmentDate;
        this.numberOfInstallments = numberOfInstallments;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getFirstInstallmentDate() {
        return firstInstallmentDate;
    }

    public void setFirstInstallmentDate(LocalDate firstInstallmentDate) {
        this.firstInstallmentDate = firstInstallmentDate;
    }

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", value=" + value +
                ", firstInstallmentDate=" + firstInstallmentDate +
                ", numberOfInstallments=" + numberOfInstallments +
                ", client= " + client.getName() +
                '}';
    }
}
