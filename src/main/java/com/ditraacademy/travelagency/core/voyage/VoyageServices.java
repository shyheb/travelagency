package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.core.destination.DestinationRepository;
import com.ditraacademy.travelagency.utility.ErrorResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageServices {
    @Autowired
    private VoyageRepository voyageRepository;
    @Autowired
    private DestinationRepository destinationRepository;

    public ResponseEntity<?> createVoyage(Voyage voyage){

        if (voyage.getTitre() == null)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : TITRE IS NULL "), HttpStatus.BAD_REQUEST);
        if (voyage.getDescription() == null)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : DESCRIPTION IS NULL "),HttpStatus.BAD_REQUEST);
        //if (voyage.getDate() == null)
            //return new ResponseEntity<>(new ErrorResponseEntity("ERROR : DATE IS NULL "), HttpStatus.BAD_REQUEST);
        if (voyage.getNbPlaces() == null)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : NB PLACE IS NULL "),HttpStatus.BAD_REQUEST);
        if (voyage.getPrix() == null)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : PRIX IS NULL "), HttpStatus.BAD_REQUEST);

        if (voyage.getDestination().getId() == 0)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : ID DESTINATION IS NULL "), HttpStatus.BAD_REQUEST);

        Optional<Destination> destinationOptional = destinationRepository.findById(voyage.getDestination().getId());
        if (!destinationOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : DESTINATION IS NOT NOT FOUND "),HttpStatus.BAD_REQUEST);

        voyage.setDestination(destinationOptional.get());
        voyage = voyageRepository.save(voyage);

        return  new ResponseEntity<>(voyage, HttpStatus.OK);
    }

    public List<Voyage> getVoyages(){
        return voyageRepository.findAll();
    }

    public ResponseEntity<?> getVoyage(int id) {
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);
        if (!voyageOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : VOYAGE NOT FOUND "),HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(voyageOptional.get(),HttpStatus.OK);
    }

    public ResponseEntity<?> deleteVoyage(int id){
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);
        if (!voyageOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : VOYAGE NOT FOUND "),HttpStatus.BAD_REQUEST);

        voyageRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> updateVoyage(int id,Voyage voyage){
        Optional<Voyage> voyageOptional = voyageRepository.findById(id);
        if (!voyageOptional.isPresent()){
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR: VOYAGE NOT FOUND"), HttpStatus.BAD_REQUEST);
        }

        Voyage voyageLegency = voyageOptional.get();

        if (voyageLegency.getTitre() != null)
            voyageLegency.setTitre(voyage.getTitre());

        if (voyageLegency.getDescription() != null)
            voyageLegency.setDescription(voyage.getDescription());

        if (voyageLegency.getDate() != null)
            voyageLegency.setDate(voyage.getDate());

        if (voyageLegency.getNbPlaces() != null)
            voyageLegency.setNbPlaces(voyage.getNbPlaces());

        if (voyageLegency.getPrix() != null)
            voyageLegency.setPrix(voyage.getPrix());

        voyageRepository.save(voyageLegency);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    public List<Voyage> getVoyageByPrice(double minPrice, double maxPrice){
        return voyageRepository.getVoyageByPrixBetweenAndNbPlacesIsNot(minPrice,maxPrice,0);
    }

}
