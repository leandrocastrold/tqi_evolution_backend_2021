package com.leandro.tqibank.validation;

import com.leandro.tqibank.dtos.LoanDTO;
import com.leandro.tqibank.models.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LoanValidationTest {

    @Test
    void shouldReturnTrueIfGivenLoanIsValid() {
        LoanDTO loanDTO = new LoanDTO(new BigDecimal(1000.00), LocalDate.now().plusDays(60), 59, new Client());
        LoanValidation validation = new LoanValidation();
        boolean isValid = validation.validateLoan(loanDTO);

        Assertions.assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(ints = {40, 90, 60, 70, 90})
    void shouldReturnTrueWhenTheFirstPaymentIsNotGreaterThan90Days(int days) {
        LocalDate d1 = LocalDate.now().plusDays(days);
        LoanValidation validation = new LoanValidation();
        boolean isValid = validation.checkFirstPaymentDateIsValid(d1);
        Assertions.assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(ints = {91, 93, 94, 100, 120})
    void shouldReturnFalseWhenTheFirstLoanPaymentIsGreaterThan90Days(int days) {
        LocalDate d1 = LocalDate.now().plusDays(days);
        LoanValidation validation = new LoanValidation();
        boolean isValid = validation.checkFirstPaymentDateIsValid(d1);
        Assertions.assertFalse(isValid);
    }

    @ParameterizedTest
    @ValueSource(ints = {45, 59, 30, 60, 36, 47})
    void shouldReturnTrueIfNumberOfParcelsIsNotGreaterThan60(int parcels) {
        LoanValidation validation = new LoanValidation();
        boolean isValid = validation.checkIfNumberOfParcelsIsNotGreaterThan60(parcels);

        Assertions.assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 61, 70, 80, 96, 77})
    void shouldReturnFalseIfNumberOfParcelsIsGreaterThan60(int parcels) {
        LoanValidation validation = new LoanValidation();
        boolean isValid = validation.checkIfNumberOfParcelsIsNotGreaterThan60(parcels);

        Assertions.assertFalse(isValid);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 3, 4, 5, 100, 1000})
    void shouldReturnTrueIfGivenValueIsGreaterThanZero(double value) {
        LoanValidation validation = new LoanValidation();
        boolean isValid = validation.checkIfValueIsGreaterThanZero(value);
        Assertions.assertTrue(isValid);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 0, -0.4, 0.0, -100, -1000})
    void shouldReturnFalseIfGivenValueIsNotGreaterThanZero(double value) {
        LoanValidation validation = new LoanValidation();
        boolean isValid = validation.checkIfValueIsGreaterThanZero(value);
        Assertions.assertFalse(isValid);
    }

    @Test
    void shouldReturnTrueWhenGivenClientIsValid() {
        Client client = new Client();
        LoanValidation validation = new LoanValidation();
        boolean isValid = validation.checkIfClientIsValid(client);
        Assertions.assertTrue(isValid);
    }

    @Test
    void shouldReturnFalseWhenGivenClientIsNotValid() {
        LoanValidation validation = new LoanValidation();
        boolean isValid = validation.checkIfClientIsValid(null);
        Assertions.assertFalse(isValid);
    }

    @Test
    void shouldReturnTheDifferenceBetweenDates() {
        LoanValidation validation = new LoanValidation();
        LocalDate d1 = LocalDate.now().plusDays(60);
        int diff = (int) validation.calculateIntervalBetweenDates(d1);
        Assertions.assertEquals(60, diff);
    }
}
