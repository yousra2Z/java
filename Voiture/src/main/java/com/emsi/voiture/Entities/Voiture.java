package com.emsi.voiture.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Voiture {
    @Id
    @GeneratedValue
    private Long id;
    private String marque;
    private String matricule;
    private String model;
    private Long id_client;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Voiture(Long id, String marque, String matricule, String model, Client client) {
        this.id=id;
        this.marque=marque;
        this.matricule=matricule;
        this.model=model;
        this.client=client;
    }
}



