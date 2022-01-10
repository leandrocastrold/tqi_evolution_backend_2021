package com.leandro.tqibank.services;

import com.leandro.tqibank.dtos.ClientDTO;
import com.leandro.tqibank.models.Client;
import com.leandro.tqibank.repositories.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder encoder;

    public List<Client> get() {
        return clientRepository.findAll();
    }

    public Client getById(Integer id) {
        return clientRepository.findById(id).get();
    }


    public Client create(ClientDTO clientDTO) {
        Client client = convertDTOtoClient(clientDTO);
        client.setPassword(encryptPassword(client.getPassword()));
        return clientRepository.save(client);
    }

    public Client getByEmail(String email) {
        Optional<Client> optional = clientRepository.findByEmail(email);
        if (optional.isPresent()) {
            return optional.get();
        }
       return null;
    }

    private Client convertDTOtoClient (ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return client;
    }

    private String encryptPassword(String password) {
        return encoder.encode(password);
    }


}
