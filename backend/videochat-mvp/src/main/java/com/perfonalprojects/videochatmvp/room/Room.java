package com.perfonalprojects.videochatmvp.room;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.perfonalprojects.videochatmvp.textchat.TextChat;
import com.perfonalprojects.videochatmvp.user.AppUser;

import java.util.List;
import java.util.ArrayList;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonFilter("RoomFilter")
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Nonnull
    @Size(min=1)
    private String roomPassword;

    @Embedded
    @Nonnull
    @NotNull
    @JsonFilter("RoomDetailsFilter")
    private RoomDetails roomDetails;


    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonFilter("AdminFilter")
    private AppUser admin;
    
    @ManyToMany(cascade = CascadeType.DETACH)
    @JsonFilter("ParticipantsFilter")
    private List<AppUser> participants = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonFilter("TextChatsFilter")
    private List<TextChat> textChats = new ArrayList<>();
}
