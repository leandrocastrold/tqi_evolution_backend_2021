package com.leandro.tqibank.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ClientDTO {
    private String name;
    private String email;
    private String password;
    private String cpf;
    private String rg;
    private String address;
    private BigDecimal income;
}
