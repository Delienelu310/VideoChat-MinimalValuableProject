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
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;

@Entity
@Data
@Builder
@JsonFilter("RoomFilter")
public class Room {

    @Id
    private Long id;

    @NotNull
    @Nonnull
    @Min(1)
    private String roomPassword;

    @Embedded
    @Nonnull
    @NotNull
    @JsonFilter("RoomDetailsFilter")
    private RoomDetails roomDetails;

    @Nonnull
    @ManyToOne(cascade = CascadeType.DETACH)
    @JsonFilter("AdminFilter")
    private AppUser admin;
    
    @Default
    @ManyToMany(cascade = CascadeType.DETACH)
    @JsonFilter("ParticipantsFilter")
    private List<AppUser> participants = new ArrayList<>();

    @Default
    @OneToMany(cascade = CascadeType.REMOVE)
    @JsonFilter("TextChatsFilter")
    private List<TextChat> textChats = new ArrayList<>();
}
