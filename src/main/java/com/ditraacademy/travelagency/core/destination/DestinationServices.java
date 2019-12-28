package com.ditraacademy.travelagency.core.destination;

import com.ditraacademy.travelagency.utility.ErrorResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationServices {

    @Autowired
    private DestinationRepository destinationRepository;

    public ResponseEntity<?> createDestination(Destination destination){
        if (destination.getNom() == null)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : NAME IS NULL "),HttpStatus.BAD_REQUEST);
        if (destination.getDescription() == null)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : DESCRIPTION IS NULL "),HttpStatus.BAD_REQUEST);

        destinationRepository.save(destination);

        return  new ResponseEntity<>(destination, HttpStatus.OK);

    }

    public List<Destination> getDestinations(){
        return destinationRepository.findAll();
    }

    public ResponseEntity<?> getDestination(int id) {
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        if (!destinationOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : DESTINATION NOT FOUND "),HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(destinationOptional.get(),HttpStatus.OK);
    }

    public ResponseEntity<?> deleteDestination(int id){
        Optional<Destination> deleteOptional = destinationRepository.findById(id);
        if (!deleteOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : DESTINATION NOT FOUND "),HttpStatus.BAD_REQUEST);
        
        destinationRepository.deleteById(id);
        return new ResponseEntity<>(deleteOptional.get(), HttpStatus.OK);
    }

    public ResponseEntity<?> updateDestination(int id,Destination destination){
        Optional<Destination> destinationOptional = destinationRepository.findById(id);
        if (!destinationOptional.isPresent()){
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR: id invalid"), HttpStatus.BAD_REQUEST);
        }
        
        Destination destinationLegency = destinationOptional.get();

        if (destinationLegency.getNom() != null)
            destinationLegency.setNom(destination.getNom());

        if (destinationLegency.getDescription() != null)
            destinationLegency.setDescription(destination.getDescription());

        destinationRepository.save(destinationLegency);

        return new ResponseEntity<>(destinationLegency,HttpStatus.OK);
    }
}
