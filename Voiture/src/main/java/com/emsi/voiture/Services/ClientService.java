package com.emsi.voiture.Services;

import com.emsi.voiture.Entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Service
@FeignClient(name="SERVICE-CLIENT")
public interface ClientService {
    @GetMapping(path="/clients/{id}")
    public Client clientById(@PathVariable Long id);
}
