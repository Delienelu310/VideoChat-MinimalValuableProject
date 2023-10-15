package com.perfonalprojects.videochatmvp.user;

import com.perfonalprojects.videochatmvp.room.Room;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.annotation.Nonnull;
import org.hibernate.annotations.Check;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class AppUser {

    @Id
    private String username;
    
    @NotNull
    @Nonnull
    @Size(min=7)
    @Check(constraints = "LENGTH(password) > 6")
    private String password;

    @Embedded
    @Nonnull
    private AppUserDetails appUserDetails;

    @ElementCollection
    private List<AppAuthority> authorities = new ArrayList<>();

    @ManyToOne
    private Room currentRoom;

    @OneToMany
    private List<Room> createdRooms = new ArrayList<>();
}