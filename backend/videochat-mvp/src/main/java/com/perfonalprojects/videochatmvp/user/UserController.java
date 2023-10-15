package com.perfonalprojects.videochatmvp.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.Valid;

@RestController
public class UserController {
    
    @Autowired
    public UserJpaRepository userJpaRepository;

    @GetMapping("/users/{user_id}")
    public MappingJacksonValue getUser(@PathVariable String username){

        Optional<AppUser> user = userJpaRepository.findById(username);
        if(user.isEmpty()){
            throw new RuntimeException("The user was not found");
        }

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user.get());

        //here i need to change something
        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("UserFilter", SimpleBeanPropertyFilter.filterOutAllExcept( 
                    "appUserDetails", 
                    "trainingsCount"
            )).addFilter("UserDetailsFilter", SimpleBeanPropertyFilter.serializeAllExcept(
                "password"
        ));

        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }

    @PostMapping("/login/{username}")
    public void login(@PathVariable("username") String username, @RequestBody String password){
        Optional<AppUser> user = userJpaRepository.findById(username);
        if(user.isEmpty()){
            throw new RuntimeException("The user with this id does not exist");
        }else if(! user.get().getPassword().equals(password)){
            throw new RuntimeException("The password is incorrect");
        }
    }

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody AppUser user){
        if(user.getAppUserDetails() == null){
            user.setAppUserDetails(new AppUserDetails(null));
        }
        userJpaRepository.save(user);
    }


}
