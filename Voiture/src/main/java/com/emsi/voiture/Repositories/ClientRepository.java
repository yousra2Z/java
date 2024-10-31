package com.emsi.voiture.Repositories;

import com.emsi.voiture.Entities.Client;
import com.emsi.voiture.Entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
