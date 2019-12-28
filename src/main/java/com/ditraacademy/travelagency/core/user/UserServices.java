package com.ditraacademy.travelagency.core.user;

import com.ditraacademy.travelagency.core.user.model.SignInRequest;
import com.ditraacademy.travelagency.core.user.model.SignInResponse;
import com.ditraacademy.travelagency.utility.ErrorResponseEntity;
import com.ditraacademy.travelagency.utility.JwtUtils;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    public ResponseEntity<?> createUser(User user){

        if(user.getName() == null)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : Nom is NULL"), HttpStatus.BAD_REQUEST);
        if(user.getName().length()<3)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : Nom est inférieur a 3"),HttpStatus.BAD_REQUEST);
        if (user.getAge() == 0)
            return new ResponseEntity<>(new ErrorResponseEntity("ERROR : Age est null"),HttpStatus.BAD_REQUEST);
        if (user.getAge()<0)
            return new ResponseEntity<>(new ErrorResponseEntity("Error : Age est inferieur à 0"),HttpStatus.BAD_REQUEST);

        String passwordEncoder = passwordEncoder().encode(user.getPassword());
        user.setPassword(passwordEncoder);

        user=userRepository.save(user);

        return  new ResponseEntity<>(user,HttpStatus.OK);
    }

    public List<User> afficheUsers(){
        return userRepository.findAll();
    }

    public ResponseEntity<?> Afficheuser(int id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent())
            return new ResponseEntity(userOptional.get(),HttpStatus.OK);
        else
            return new ResponseEntity(new ErrorResponseEntity("Utilisateur pas disponible"),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> DeleteUser(int id){
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
        }else
            return new ResponseEntity<>(new ErrorResponseEntity("invalid id"),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> UpdateUser(int id,User updateduser){
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

    public ResponseEntity<?> signInUser(SignInRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(),signInRequest.getPassword()));
                String token = new JwtUtils().generateToken(signInRequest.getEmail());

                return new ResponseEntity<>(new SignInResponse(token),HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (!userOptional.isPresent())
            return null;

        return new org.springframework.security.core.userdetails.User(email,userOptional.get().getPassword(), AuthorityUtils.NO_AUTHORITIES);
    }

    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }


}
