package com.emsi.voiture.Controllers;

import com.emsi.voiture.Entities.Client;
import com.emsi.voiture.Entities.Voiture;
import com.emsi.voiture.Repositories.VoitureRepository;
import com.emsi.voiture.Services.ClientService;
import com.emsi.voiture.Services.VoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class VoitureController {
    @Autowired
    VoitureRepository voitureRepository;

    @Autowired
    VoitureService voitureService;

    @Autowired
    ClientService clientService;

    @GetMapping(value =  "/voitures",  produces  =  {"application/json"})
    public ResponseEntity<Object> findAll() {
        try  {
            List<Voiture> voitures = voitureRepository.findAll();
            return  ResponseEntity.ok(voitures);
        } catch (Exception e) {
            return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error  fetching  voitures:  "  +  e.getMessage());
        }
    }

    @GetMapping("/voitures/{Id}")
    public  ResponseEntity<Object>  findById(@PathVariable  Long  Id)  {
        try  {
            Voiture voiture   =   voitureRepository.findById(Id)
                    .orElseThrow(()  ->  new  Exception("Voiture  Introuvable"));

            //  Fetch  the  client  details  using  the  clientService
            // voiture.setClient(clientService.clientById(voiture.getId_client())); // Removed
            // Now access client through the 'client' property
            return  ResponseEntity.ok(voiture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Voiture not  found with ID: "  + Id);
        }
    }

    @GetMapping("/voitures/client/{Id}")
    public ResponseEntity<List<Voiture>> findByClient(@PathVariable Long Id) {
        try  {
            Client client    =    clientService.clientById(Id);
            if (client != null) {
                List<Voiture>  voitures  =  voitureRepository.findByClient(client); // Updated
                return ResponseEntity.ok(voitures);
            }  else  {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/voitures/{clientId}")
    public ResponseEntity<Object> save(@PathVariable Long clientId, @RequestBody Voiture voiture) {
        try {
            //  Fetch  the  client  details  using  the  clientService
            Client client = clientService.clientById(clientId);

            if (client != null) {
                //  Set  the  fetched  client  in  the  voiture  object
                voiture.setClient(client);

                // Save the Voiture with the associated Client
                // voiture.setId_client(clientId); // Removed
                Voiture savedVoiture = voitureService.enregistrerVoiture(voiture);
                return ResponseEntity.ok(savedVoiture);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Client not found with ID: " + clientId);

            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving voiture: "+ e.getMessage());

        }
    }
    @PutMapping("/voitures/{Id}")
    public ResponseEntity<Object> update(@PathVariable Long Id, @RequestBody Voiture updatedVoiture) {
        try
        {
            Voiture existingVoiture = voitureRepository.findById(Id)
                    .orElseThrow(() -> new Exception("Voiture not found with ID: "+ Id));

            // Update only the non-null fields from the request body
            if (updatedVoiture.getMatricule() != null && !updatedVoiture.getMatricule().isEmpty()) {
                existingVoiture.setMatricule(updatedVoiture.getMatricule());
            }
            if (updatedVoiture.getMarque() != null && !updatedVoiture.getMarque().isEmpty()) {
                existingVoiture.setMarque(updatedVoiture.getMarque());
            }
            if (updatedVoiture.getModel() != null && !updatedVoiture.getModel().isEmpty()) {
                existingVoiture.setModel(updatedVoiture.getModel());
            }
            // Save the updated Voiture
            Voiture savedVoiture = voitureRepository.save(existingVoiture);
            return ResponseEntity.ok(savedVoiture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating voiture: " + e.getMessage());

        }
    }


}

