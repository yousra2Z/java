package com.emsi.voiture.Services;

import com.emsi.voiture.Entities.Client;
import com.emsi.voiture.Entities.Voiture;
import com.emsi.voiture.Repositories.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoitureService {
    @Autowired
    private ClientService clientService;

    @Autowired
    private VoitureRepository voitureRepository;

    public Client clientById(Long id) {
        return clientService.clientById(id);
    }

    public Voiture enregistrerVoiture(Voiture voiture) {
        return voitureRepository.save(voiture);
    }

}
