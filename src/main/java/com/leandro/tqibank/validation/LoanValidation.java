package com.leandro.tqibank.validation;

import com.leandro.tqibank.dtos.LoanDTO;
import com.leandro.tqibank.models.Client;

import java.time.Duration;
import java.time.LocalDate;

public class LoanValidation {

    public boolean validateLoan(LoanDTO loanDTO) {
        int errorsCount = 0;

        if (!checkIfValueIsGreaterThanZero(loanDTO.getValue().doubleValue())) {
            errorsCount++;
            System.out.println("Valor deve ser acima de 0 reais");
        }
        if (!checkIfNumberOfParcelsIsNotGreaterThan60(loanDTO.getNumberOfInstallments())) {
            errorsCount++;
            System.out.printf("O número máximo de parcelas é 60");
        }
        if (!checkFirstPaymentDateIsValid(loanDTO.getFirstInstallmentDate())) {
            errorsCount++;
            System.out.printf("O prazo para a 1º parcela é de no máximo 90 dias");
        }

        if (!checkIfClientIsValid(loanDTO.getClient())) {
            errorsCount++;
            System.out.printf("Operação não permitida");
        }

        if (errorsCount > 0) {
            return false;
        }

        return true;

    }

    protected boolean checkIfValueIsGreaterThanZero(Double value) {
        boolean isValid = value > 0 ? true : false;
        return isValid;
    }

    protected boolean checkIfNumberOfParcelsIsNotGreaterThan60(int parcels) {
        boolean isValid = parcels <= 60 ? true : false;
        return isValid;
    }

    protected boolean checkFirstPaymentDateIsValid(LocalDate date) {
        if (calculateIntervalBetweenDates(date) <= 90 && calculateIntervalBetweenDates(date) > 0) {
            return true;
        }
        return false;
    }

    protected boolean checkIfClientIsValid(Client client) {
        boolean isValid = client != null ? true : false;
        return isValid;
    }

    protected long calculateIntervalBetweenDates(LocalDate date) {
        LocalDate d1 = LocalDate.now();
        LocalDate d2 = date;
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        long diffDays = diff.toDays();
        System.out.println("Diferença entre dias:" + diffDays);
        return diffDays;
    }

}
