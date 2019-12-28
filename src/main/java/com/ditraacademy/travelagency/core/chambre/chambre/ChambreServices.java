package com.ditraacademy.travelagency.core.chambre.chambre;

import com.ditraacademy.travelagency.core.chambre.categorieChambre.CategoryChambre;
import com.ditraacademy.travelagency.core.chambre.categorieChambre.CategoryChambreRepository;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambre;
import com.ditraacademy.travelagency.core.chambre.typeChambre.TypeChambreRepository;
import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.utility.ErrorResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChambreServices {

    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    private TypeChambreRepository typeChambreRepository;
    @Autowired
    private CategoryChambreRepository categoryChambreRepository;

    public ResponseEntity<?> createChambre(Chambre chambre) {

        Optional<TypeChambre> typeChambreOptional = typeChambreRepository.findById(chambre.getTypeChambre().getId());
        if (!typeChambreOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseEntity("TyprChambre not Exist"),HttpStatus.BAD_REQUEST);

        Optional<CategoryChambre> categoryChambreOptional = categoryChambreRepository.findById(chambre.getCategoryChambre().getId());
        if (!categoryChambreOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseEntity("CategoryChambre not exist"),HttpStatus.BAD_REQUEST);
        chambre = chambreRepository.save(chambre);
        return  new ResponseEntity<>(chambre, HttpStatus.OK);
    }

    public ResponseEntity<?> getChambres() {
        List<Chambre> chambre = chambreRepository.findAll();
        return new ResponseEntity<>(chambre,HttpStatus.OK);
    }

    /*
    public ResponseEntity<?> updateChambre(int id, Chambre chambre) {
        Optional<Chambre> chambreOptional = chambreRepository.findById(id);
        if (!chambreOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseEntity("Chambre introuvable"),HttpStatus.BAD_REQUEST);
        Chambre chambreLegency = chambreRepository.save(chambre);
        return new ResponseEntity<>(chambreLegency,HttpStatus.OK);
    }
     */

    public ResponseEntity<?> deleteChambre(int id) {
        Optional<Chambre> chambreOptional = chambreRepository.findById(id);
        if (!chambreOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseEntity("ID du Chambre n'est pas valide"),HttpStatus.BAD_REQUEST);
        chambreRepository.delete(chambreOptional.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
