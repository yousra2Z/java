package com.emsi.client.Controllers;

import com.emsi.client.Entities.Client;
import com.emsi.client.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    ClientRepository clientRepository;
    @GetMapping("/clients")
    public List findAll(){
        return clientRepository.findAll();
    }
    @GetMapping("/client/{id}")
    public Client findById (@PathVariable Long id) throws Exception{
        return this.clientRepository.findById(id).orElseThrow(()-> new Exception("Client inexistant"));
    }
}
