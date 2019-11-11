package com.ditraacademy.travelagency.core;

import com.ditraacademy.travelagency.utility.ErrorResponseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user){

        if(user.getName() == null)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : Nom is NULL"), HttpStatus.BAD_REQUEST);
        if(user.getName().length()<3)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : Nom est inférieur a 3"),HttpStatus.BAD_REQUEST);
        if (user.getAge() == 0)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : Age est null"),HttpStatus.BAD_REQUEST);
        if (user.getAge()<0)
            return new ResponseEntity<>(new ErrorResponseEntity("Error : Age est inferieur à 0"),HttpStatus.BAD_REQUEST);

        user=userRepository.save(user);
        return  new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<User> afficheUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User Afficheuser(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.get();
        return user;
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        }else
            return new ResponseEntity<>(new ErrorResponseEntity("invalid id"),HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> UpdateUser(@PathVariable int id,@RequestBody User updateduser){
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()){
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR: id invalid"), HttpStatus.BAD_REQUEST);
        }

        User legencyUser = userOptional.get();

        if (updateduser.getName() != null)
            if (updateduser.getName().length()<3)
                return new ResponseEntity<>(new ErrorResponseEntity("ERROR : LENGTH EST < 3 "),HttpStatus.OK);
            else
                legencyUser.setName(updateduser.getName());

        if (updateduser.getAge() != 0)
            if (updateduser.getAge() < 0)
                return new ResponseEntity<>(new ErrorResponseEntity("ERROR : AGE < 0"),HttpStatus.OK);
            else
                legencyUser.setAge(updateduser.getAge());

         userRepository.save(legencyUser);
         return new ResponseEntity<>(HttpStatus.OK);


    }



}
