package com.ditraacademy.travelagency.core.chambre.chambre;

import com.ditraacademy.travelagency.core.destination.DestinationRepository;
import com.ditraacademy.travelagency.core.destination.DestinationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChambreController {


    @Autowired
    private ChambreServices chambreServices;

    @PostMapping("/chambre/add")
    public ResponseEntity<?> createChambre(@RequestBody Chambre chambre){
        return chambreServices.createChambre(chambre);
    }

    @GetMapping("/chambre")
    public ResponseEntity<?> getChambres(){
        return chambreServices.getChambres();
    }
    /*
    @PutMapping("/chambre/{id}")
    public ResponseEntity<?> updateChambre(@PathVariable int id, @RequestBody Chambre chambre){
        return  chambreServices.updateChambre(id,chambre);
    }
    */
    @DeleteMapping("/chambre/{id}")
    public ResponseEntity<?> deleteChambre(@PathVariable int id){
        return chambreServices.deleteChambre(id);

    }
}
