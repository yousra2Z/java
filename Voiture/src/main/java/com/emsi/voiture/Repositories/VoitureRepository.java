package com.emsi.voiture.Repositories;

import com.emsi.voiture.Entities.Client;
import com.emsi.voiture.Entities.Voiture;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface VoitureRepository extends JpaRepository<Voiture,Long> {
        List<Voiture> findByClient(Client client);

    }

