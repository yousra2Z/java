package com.emsi.voiture.Controllers;

import com.emsi.voiture.Entities.Voiture;
import com.emsi.voiture.Repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class VoitureController {
    @Autowired
    VoitureRepository voitureRepository;
    @GetMapping("/voitures")
    public List findAll() {

        return voitureRepository.findAll();
    }

    
}

