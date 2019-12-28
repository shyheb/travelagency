package com.ditraacademy.travelagency.core.hotel;

import com.ditraacademy.travelagency.core.chambre.chambre.Chambre;
import com.ditraacademy.travelagency.core.chambre.chambre.ChambreRepository;
import com.ditraacademy.travelagency.utility.ErrorResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServices {
    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    ChambreRepository chambreRepository;

    public ResponseEntity<?> createHotel(Hotel hotel){
        hotel = hotelRepository.save(hotel);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    public ResponseEntity<?> geHotels(){
        List<Hotel> hotels = hotelRepository.findAll();
        return  new ResponseEntity<>(hotels,HttpStatus.OK);
    }


    public ResponseEntity<?> updateHotel(int id, HotelUpdateModel hotelUpdateModel) {
        Optional<Hotel> hotelOptional = hotelRepository.findById(id);
        if (!hotelOptional.isPresent())
            return new ResponseEntity<>(new ErrorResponseEntity("Invalid hotel"), HttpStatus.BAD_REQUEST);

        Hotel hotel = hotelOptional.get();

        for (int chambreId : hotelUpdateModel.getChambreId()){
            Optional<Chambre> chambreOptional = chambreRepository.findById(chambreId);

            if (!chambreOptional.isPresent())
                return new ResponseEntity<>(new ErrorResponseEntity("Invalid chambre"), HttpStatus.BAD_REQUEST);

            hotel.addChambre(chambreOptional.get());
        }
        hotelRepository.save(hotel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
