package com.leandro.tqibank.dtos;

import com.leandro.tqibank.models.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class LoanDTO
{
    private BigDecimal value;
    private LocalDate firstInstallmentDate;
    private Integer numberOfInstallments;
    private Client client;
}
