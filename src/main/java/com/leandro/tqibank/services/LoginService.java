package com.leandro.tqibank.services;

import com.leandro.tqibank.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {


    @Autowired
    private ClientService clientService;

    @Autowired
    private PasswordEncoder encoder;


    public Client validateLogin(String email, String password) {
        Client client = clientService.getByEmail(email);
        if (client != null) {
            boolean valid = encoder.matches(password, client.getPassword());
            if (valid) {
                return client;
            }
        }
        return null;
    }

}
