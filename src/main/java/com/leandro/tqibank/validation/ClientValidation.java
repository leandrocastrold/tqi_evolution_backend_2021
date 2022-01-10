package com.leandro.tqibank.validation;

import com.leandro.tqibank.dtos.ClientDTO;

public class ClientValidation {

    private final int MIN_NUMBER_LETTERS = 3;
    private final int MIN_PASSWORD_CHARACTERS = 4;

    public Boolean validateClientDTO(ClientDTO clientDTO) {
        int errorsCount = 0;

        if (!checkIfFieldNameIsValid(clientDTO.getName())) {
            errorsCount++;
            System.out.println("O campo NOME não pode ser vazio e deve ter no mínimo " + MIN_NUMBER_LETTERS + " caracteres");
        }
        if (!checkIfFieldEmailIsValid(clientDTO.getEmail())) {
            System.out.println("Email inserido é inválido");
            errorsCount++;
        }
        if (!checkIfFieldPasswordIsValid(clientDTO.getPassword())) {
            System.out.println("Campo PASSWORD não pode ser vazio e deve ter no mínimo " + MIN_PASSWORD_CHARACTERS + " caracteres");
            errorsCount++;
        }
        if (!checkIfFieldCPFIsValid(clientDTO.getCpf())) {
            System.out.println("Campo CPF inválido");
            errorsCount++;
        }
        if (!checkIfFieldRGIsValid(clientDTO.getRg())) {
            System.out.println("Campo RG inválido");
            errorsCount++;
        }
        if (!checkIfFieldAddressIsValid(clientDTO.getAddress())) {
            System.out.println("Campo ENDEREÇO não pode ser vazio.");
            errorsCount++;
        }

        if (!checkIfFieldIncomeIsValid(clientDTO.getIncome().doubleValue())) {
            System.out.println("Campo RENDA deve ter valor acima de 0 reais.");
            errorsCount++;
        }


        if (errorsCount > 0) {
            System.out.println("Erro encontrado!");
            return false;
        }
        return true;
    }

    protected boolean checkIfFieldNameIsValid(String name) {
        if (!name.isBlank() && name.trim().length() >= MIN_NUMBER_LETTERS) {
            return true;
        }
        return false;
    }

    protected boolean checkIfFieldEmailIsValid(String email) {
        if (email.matches("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
            return true;
        }
        return false;
    }

    protected boolean checkIfFieldPasswordIsValid(String password) {
        if (!password.isBlank() && password.trim().length() >= MIN_PASSWORD_CHARACTERS) {
            return true;
        }
        return false;
    }

    protected boolean checkIfFieldCPFIsValid(String cpf) {
        if (cpf.matches("^\\d{3}\\d{3}\\d{3}\\d{2}$") || cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$")) {
            return true;
        }
        return false;
    }

    protected boolean checkIfFieldRGIsValid(String rg) {
        if (rg.matches("(^\\d{1,2}).?(\\d{3}).?(\\d{3})-?(\\d{0,1}|X|x$)")) {
            return true;
        }
        return false;
    }

    protected boolean checkIfFieldAddressIsValid(String address) {
        if (!address.isBlank()) {
            return true;
        }
        return false;
    }

    protected boolean checkIfFieldIncomeIsValid(double income) {
        if (income > 0.0) {
            return true;
        }
        return false;
    }
}
