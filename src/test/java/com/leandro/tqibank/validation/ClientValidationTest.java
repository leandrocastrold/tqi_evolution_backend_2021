package com.leandro.tqibank.validation;

import com.leandro.tqibank.dtos.ClientDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

public class ClientValidationTest {

    @ParameterizedTest
    @ValueSource(strings = {"Leandro", "Maísa", "Paulo", "Ana"})
    void shouldReturnTrueWhenTheGivenNameIsValid(String name) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldNameIsValid(name);

        Assertions.assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "     ", "An", "Le"})
    void shouldReturnFalseWhenTheGivenNameIsNotValid(String name) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldNameIsValid(name);
        Assertions.assertFalse(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"leandro.castro@hotmail.com", "leandrocastro@hotmail.com", "leandrocastro@hotmail.com.br"})
    void shouldReturnTrueIfTheGivenEmailIsValid(String email) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldEmailIsValid(email);

        Assertions.assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"leandro.castro@hotmailcom", "leandrocastrohotmail.com", "leandrocastro@hotmail.com.br."})
    void shouldReturnFalseWhenTheGivenEmailIsNotValid(String email) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldEmailIsValid(email);

        Assertions.assertFalse(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234", "12345", "jfkdll"})
    void shouldReturnTrueWhenTheGivenPasswordIsValid(String password) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldPasswordIsValid(password);

        Assertions.assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"    ", "", "123"})
    void shouldReturnFalseWhenTheGivenPasswordIsNotValid(String password) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldPasswordIsValid(password);

        Assertions.assertFalse(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345678900", "095.095.095-44"})
    void shouldReturnTrueWhenTheGivenCPFIsValid(String cpf) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldCPFIsValid(cpf);
        Assertions.assertTrue(isValid);
    }


    @ParameterizedTest
    @ValueSource(strings = {"09344444444444444", "09509500", "", " ", "095.095.095/44"})
    void shouldReturnFalseWhenTheGivenCPFIsNotValid(String cpf) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldCPFIsValid(cpf);
        Assertions.assertFalse(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"8384771", "095095095", "09509509500"})
    void shouldReturnTrueWhenTheGivenRGIsValid(String rg) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldRGIsValid(rg);
        Assertions.assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"09344444444444444", "09509500A", "", " ", "095.095.095/44"})
    void shouldReturnFalseWhenTheGivenRGIsNotValid(String rg) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldRGIsValid(rg);
        Assertions.assertFalse(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Rua L", "Rua Luiz da Silva"})
    void shouldReturnTrueWhenTheGivenAddressIsValid(String address) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldAddressIsValid(address);

        Assertions.assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void shouldReturnFalseWhenTheGivenAddressIsNotValid(String address) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldAddressIsValid(address);
        Assertions.assertFalse(isValid);
    }

    @ParameterizedTest
    @ValueSource(doubles = {100, 1000, 1, 0.1})
    void shouldReturnTrueWhenTheGivenIncomeIsValid(double income) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldIncomeIsValid(income);

        Assertions.assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -0.1})
    void shouldReturnFalseWhenTheGivenIncomeIsNotValid(double income) {
        ClientValidation validation = new ClientValidation();
        boolean isValid = validation.checkIfFieldIncomeIsValid(income);

        Assertions.assertFalse(isValid);
    }

    @Test
    void shouldReturnTrueWhenTheGivenClientDTOIsValid() {
        ClientValidation validation = new ClientValidation();
        ClientDTO clientDTO = new ClientDTO("Leandro", "leandro@hotmail.com", "1234", "09500000000",
                "8300000", "Rua Luiz Antônio", new BigDecimal(10000));
        boolean isValid = validation.validateClientDTO(clientDTO);
        Assertions.assertTrue(isValid);
    }

    @Test
    void shouldReturnFalseWhenTheGivenClientDTOIsNotValid() {
        ClientValidation validation = new ClientValidation();
        ClientDTO clientDTO = new ClientDTO("Le", "leandro@hotmail.com", "1234", "09500000000",
                "8300000", "Rua Luiz Antônio", new BigDecimal(10000));
        boolean isValid = validation.validateClientDTO(clientDTO);
        Assertions.assertFalse(isValid);
    }
}

