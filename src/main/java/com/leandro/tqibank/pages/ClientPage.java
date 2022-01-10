package com.leandro.tqibank.pages;

import com.leandro.tqibank.dtos.LoanDTO;
import com.leandro.tqibank.models.Client;
import com.leandro.tqibank.models.Loan;
import com.leandro.tqibank.services.LoanService;
import com.leandro.tqibank.validation.LoanValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

@Component
public class ClientPage {

    @Autowired
    private LoanService loanService;

    public ClientPage() {
    }

    Scanner scanner = new Scanner(System.in);
    Client loggedClient;

    public Client getLoggedClient() {
        return loggedClient;
    }

    public void setLoggedClient(Client loggedClient) {
        this.loggedClient = loggedClient;
    }

    public void showClientMenu() {

        System.out.println(
                "=================================\n" +
                        "  TQI BANK  - " + loggedClient.getName() + "  \n" +
                        "=================================\n");
        System.out.println(
                "[1] : SOLICITAR EMPRÉSTIMO\n" +
                        "[2] : SOLICITAÇÕES DE EMPRÉSTIMO\n" +
                        "[3] : DETALHES DA ÚLTIMA SOLICITAÇÃO\n" +
                        "[0] : SAIR\n");
        System.out.print("Escolha uma operação: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                showLoanRequestMenu();
                break;
            case 2:
                showTrackingRequestsMenu();
                break;
            case 3:
                showLoanDetails();
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    public void showLoanRequestMenu() {

        System.out.println(
                "=================================\n" +
                        "=   TQI BANK   -   Empréstimos  =\n" +
                        "=================================\n");
        System.out.print("Valor do empréstimo: ");
        String value = scanner.nextLine();
        System.out.print("Data da primeira parcela: ");
        LocalDate date = null;
        try {
             date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException err) {
            System.out.println("\n[[ Data deve seguir o formato 'DD/MM/YYYY - ex: 12/02/2020' ]]\n"  );
            showLoanRequestMenu();
       }

        System.out.print("Quantidade de parcelas: ");
        Integer qtd = scanner.nextInt();

        LoanDTO loanDTO = new LoanDTO(new BigDecimal(value), date, qtd, loggedClient);
        LoanValidation validation = new LoanValidation();
        if (validation.validateLoan(loanDTO)) {
            loanService.create(loanDTO);
            System.out.println("\n[[ Solicitação realizado com sucesso!]]\n");

        } else {
            System.out.println("\n[[ Erro no processamento da solicitação ]]\n");
        }
        pauseProgram();
        showClientMenu();
    }

    public void showTrackingRequestsMenu() {

        List<Loan> loans = getLoansByClientId();
        if (loans.size() > 0) {
            System.out.println("\n----------------------------");
            System.out.println("Empréstimos solicitados:");
            System.out.println("----------------------------");
            loans.forEach(l -> {
                System.out.println("Código: " + l.getId() + "\n" +
                        "Valor: " + l.getValue() + "\n" +
                        "Qtd. de Parcelas: " + l.getNumberOfInstallments() + "\n");
            });
        } else {
            System.out.println("\n[[ Nenhum empréstimo solicitado ]]");
        }

        pauseProgram();
        showClientMenu();
    }

    public void showLoanDetails() {
        List<Loan> loans = getLoansByClientId();
        if (loans.size() > 0) {
        int  lastId = loans.get(loans.size() - 1).getId(); // pega o ID do último empréstimo
        System.out.println("\n----------------------------");
        System.out.println("DETALHES:");
        System.out.println("----------------------------");
            Loan loan = loanService.getById(lastId);
            System.out.println("Código: " + loan.getId() + "\n" +
                    "Valor: " + loan.getValue() + "\n" +
                    "Qtd. de Parcelas: " + loan.getNumberOfInstallments() + "\n" +
                    "Data da 1º parcela: " + loan.getFirstInstallmentDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" +
                    "Email do cliente: " + loan.getClient().getEmail() + "\n" +
                    "Renda do cliente: " + loan.getClient().getIncome() + "\n");
        }else {
            System.out.println("\n[[ Não foi feita nenhuma solicitação]]\n");
        }
        pauseProgram();
        showClientMenu();
    }

    private List<Loan> getLoansByClientId() {
        return loanService.getByClientId(loggedClient.getId());
    }

    public void pauseProgram() {
        System.out.printf("\nTECLE ENTER PARA CONTINUAR...\n");
        scanner.nextLine();
    }

}
