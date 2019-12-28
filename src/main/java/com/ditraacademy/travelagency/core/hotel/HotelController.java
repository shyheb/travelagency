package com.ditraacademy.travelagency.core.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
public class HotelController {

   @Autowired
   HotelServices hotelServices;

    @PostMapping("/hotel")
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel){
        return  hotelServices.createHotel(hotel);
    }

    @GetMapping("/hotels")
    public ResponseEntity<?> getHotels(){
        return hotelServices.geHotels();
    }

    @PutMapping("/hotel/{id}")
    public ResponseEntity<?> updateHotel(@PathVariable int id, @RequestBody HotelUpdateModel hotelUpdateModel){
        return hotelServices.updateHotel(id,hotelUpdateModel);
    }
}
