package com.ditraacademy.travelagency.core.user;

import com.ditraacademy.travelagency.core.user.model.SignInRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        return userServices.createUser(user);
    }

    @GetMapping("/users")
    public List<User> afficheUsers(){
        return userServices.afficheUsers();
    } 

    @GetMapping("/user/{id}")
    public ResponseEntity<?> Afficheuser(@PathVariable int id){
        return userServices.Afficheuser(id);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> DeleteUser(@PathVariable int id){
        return userServices.DeleteUser(id);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> UpdateUser(@PathVariable int id,@RequestBody User updateduser){
        return userServices.UpdateUser(id,updateduser);
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<?> signInUser(@RequestBody SignInRequest signInRequest){
        return userServices.signInUser(signInRequest);
    }




}
