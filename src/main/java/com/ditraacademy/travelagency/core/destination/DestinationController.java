package com.ditraacademy.travelagency.core.destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DestinationController {

    @Autowired
    private DestinationServices destinationServices;

    @PostMapping("/destination")
    public ResponseEntity<?> createDestination(@RequestBody Destination Destination){
        return destinationServices.createDestination(Destination);
    }

    @GetMapping("/destinations")
    public List<Destination> getDestinations(){
        return destinationServices.getDestinations();
    }

    @GetMapping("/destination/{id}")
    public ResponseEntity<?> getDestination(@PathVariable int id){
        return destinationServices.getDestination(id);
    }

    @DeleteMapping("/destination/{id}")
    public ResponseEntity<?> deleteDestination(@PathVariable int id){
        return destinationServices.deleteDestination(id);
    }

    @PutMapping("/destination/{id}")
    public ResponseEntity<?> updateDestination(@PathVariable int id,@RequestBody Destination updatedDestination){
        return destinationServices.updateDestination(id,updatedDestination);
    }
}
