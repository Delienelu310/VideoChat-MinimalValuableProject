package com.perfonalprojects.videochatmvp.user;

import com.perfonalprojects.videochatmvp.room.Room;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.annotation.Nonnull;
import org.hibernate.annotations.Check;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;


@Entity
@Data
@Builder
public class AppUser {

    @Id
    @NotNull
    private String username;
    
    @NotNull
    @Nonnull
    @Min(7)
    @Check(constraints = "LENGTH(password) > 6")
    private String password;

    @Embedded
    @Nonnull
    private UserDetails userDetails;

    @Embedded
    @Nonnull
    @Default 
    private List<AppAuthority> authorities = new ArrayList<>();

    @ManyToOne
    private Room currentRoom;

    @OneToMany
    @Default
    private List<Room> createdRooms = new ArrayList<>();
}
