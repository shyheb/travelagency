package com.ditraacademy.travelagency.core.voyage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoyageController {

    @Autowired
    private VoyageServices voyageServices;

    @PostMapping("/voyage")
    public ResponseEntity<?> createvoyage(@RequestBody Voyage voyage){
        return voyageServices.createVoyage(voyage);
    }

    @GetMapping("/voyage")
    public List<Voyage> getVoyages(){
        return voyageServices.getVoyages();
    }

    @GetMapping("/voyage/{id}")
    public ResponseEntity<?> getvoyage(@PathVariable int id){
        return voyageServices.getVoyage(id);
    }

    @DeleteMapping("/voyage/{id}")
    public ResponseEntity<?> deletevoyage(@PathVariable int id){
        return voyageServices.deleteVoyage(id);
    }

    @PutMapping("/voyage/{id}")
    public ResponseEntity<?> updatevoyage(@PathVariable int id,@RequestBody Voyage updatedvoyage){
        return voyageServices.updateVoyage(id,updatedvoyage);
    }
}
