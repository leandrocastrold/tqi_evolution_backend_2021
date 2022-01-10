package com.leandro.tqibank.pages;

import com.leandro.tqibank.dtos.ClientDTO;
import com.leandro.tqibank.models.Client;
import com.leandro.tqibank.services.ClientService;

import com.leandro.tqibank.services.LoginService;
import com.leandro.tqibank.validation.ClientValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class HomePage {

    @Autowired
    private ClientService clientService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ClientPage clientPage;


    public HomePage(){}

    Scanner scanner = new Scanner(System.in);


    public void showHomePage() {
        System.out.println(
                      "\n==============================\n" +
                        "= TQI BANK  - Página inicial =\n" +
                        "==============================\n");
        System.out.println(
                "[1] : CADASTRE-SE\n" +
                        "[2] : FAZER LOGIN\n" +
                        "[0] : SAIR\n");

        System.out.print("Escolha uma operação: ");
        Integer option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                showRegisterClientMenu();
                break;
            case 2:
                login();
                break;
            case 0:
                System.exit(0);
                break;
        }
    }

    public void showRegisterClientMenu() {
        ClientDTO clientDTO;

        System.out.print("Nome: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("RG: ");
        String rg = scanner.nextLine();
        System.out.print("Endereço: ");
        String address = scanner.nextLine();
        System.out.print("Renda: ");
        String income = scanner.nextLine();

        clientDTO = new ClientDTO(name, email, password, cpf, rg, address, new BigDecimal(income));
        ClientValidation validation = new ClientValidation();

        if (validation.validateClientDTO(clientDTO)) {
            clientService.create(clientDTO);
            System.out.println("\n[[ Cliente cadastrado com sucesso! ]]");
        } else {
            System.out.println("\n[[ Erro(s) no formulário. Não foi possível cadastrar novo cliente]]");

        }
        pauseProgram();
        showHomePage();
    }

    public void login() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String password = scanner.nextLine();

        Client client = loginService.validateLogin(email, password);

        if (client != null) {
            System.out.println("\n[[ " + client.getName() + " logado(a) com sucesso! ]]");
            pauseProgram();
            clientPage.setLoggedClient(client);
            clientPage.showClientMenu();

        } else {
            System.out.println("[[ Login ou senha inválidos ]]");
            pauseProgram();
            showHomePage();
        }

    }

    public void pauseProgram() {
        System.out.printf("\nTECLE ENTER PARA CONTINUAR...\n");
        scanner.nextLine();
    }
}
