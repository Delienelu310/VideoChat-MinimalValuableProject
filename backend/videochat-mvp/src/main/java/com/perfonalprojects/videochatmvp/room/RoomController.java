package com.perfonalprojects.videochatmvp.room;

import java.util.Optional;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.perfonalprojects.videochatmvp.user.AppUser;
import com.perfonalprojects.videochatmvp.user.UserJpaRepository;

import jakarta.validation.Valid;

@RestController
public class RoomController {
    
    public RoomJpaRepository roomJpaRepository;
    public UserJpaRepository userJpaRepository;

    @GetMapping("/rooms/{id}")
    public MappingJacksonValue getRoomById(@PathVariable("id") Long id){
        Optional<Room> room = roomJpaRepository.findById(id);
        if( room.isEmpty() ){ 
            throw new RuntimeException("The room with this id was not found");
        }

        MappingJacksonValue result = new MappingJacksonValue(room.get());
        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("RoomFilter", SimpleBeanPropertyFilter.serializeAllExcept("roomPassword", "textChats"))
            .addFilter("AdminFilter", SimpleBeanPropertyFilter.filterOutAllExcept("username", "userDetails"))
            .addFilter("ParticipantsFilter", SimpleBeanPropertyFilter.filterOutAllExcept("username", "userDetails"));
        
        result.setFilters(filterProvider);

        return result;
    }

    @GetMapping("/rooms")
    public MappingJacksonValue getRoomList(){

        List<Room> rooms = roomJpaRepository.findAll();

        MappingJacksonValue result = new MappingJacksonValue(rooms);
        FilterProvider filterProvider = new SimpleFilterProvider()
            .addFilter("RoomFilter", SimpleBeanPropertyFilter.serializeAllExcept("roomPassword", "textChats"))
            .addFilter("AdminFilter", SimpleBeanPropertyFilter.filterOutAllExcept("username", "userDetails"))
            .addFilter("ParticipantsFilter", SimpleBeanPropertyFilter.filterOutAllExcept("username", "userDetails"));
        
        result.setFilters(filterProvider);

        return result;
    }

    @DeleteMapping("/rooms/{room_id}")
    public void deleteRoom(@PathVariable Long roomId){
        Optional<Room> room = roomJpaRepository.findById(roomId);
        if(room.isEmpty()){
            throw new RuntimeException("The room with room id does not exist");
        }

        roomJpaRepository.delete(room.get());
    }

    @PostMapping("/users/{username}/rooms")
    public void createRoom(@PathVariable("username") String username, @Valid @RequestBody Room room){
        Optional<AppUser> user = userJpaRepository.findById(username);
        if(user.isEmpty()){
            throw new RuntimeException("User with this username does not exist");
        }

        roomJpaRepository.save(room);
    }

    @PutMapping("/rooms/{room_id}/add/participant/{username}")
    public void addParticipant(@PathVariable("room_id") Long roomId, @PathVariable("username") String username, @RequestBody String password){
        Optional<Room> room = roomJpaRepository.findById(roomId);
        if(room.isEmpty()){
            throw new RuntimeException("Room with this id was not found");
        }

        Optional<AppUser> user = userJpaRepository.findById(username);
        if(user.isEmpty()){
            throw new RuntimeException("User with this username does not exist");
        }

        if(! room.get().getRoomPassword().equals(password)){
            throw new RuntimeException("Password for the room is invalid");
        }

        room.get().getParticipants().add(user.get());

        roomJpaRepository.save(room.get());
    }

    @PutMapping("/rooms/{room_id}/remove/participant/{username}")
    public void removeParticipant(@PathVariable("room_id") Long roomId, @PathVariable("username") String username){
        Optional<Room> room = roomJpaRepository.findById(roomId);
        if(room.isEmpty()){
            throw new RuntimeException("Room with this id was not found");
        }

        Optional<AppUser> user = userJpaRepository.findById(username);
        if(user.isEmpty()){
            throw new RuntimeException("User with this username does not exist");
        }
        if(! room.get().getParticipants().contains(user.get())){
            throw new RuntimeException("The user is not in participants list right now");
        }

        room.get().getParticipants().remove(user.get());

        roomJpaRepository.save(room.get());
    }
}
