package com.perfonalprojects.videochatmvp.textchat;

import com.perfonalprojects.videochatmvp.user.AppUser;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;

@Entity
@Data
@Builder
public class TextChat {
    @Id
    private Long id;

    @ManyToOne
    @Nonnull
    private AppUser admin;

    @Embedded
    @NotNull
    @Nonnull
    private TextChatDetails textChatDetails;

    @ManyToMany
    @Default private List<AppUser> patricipants = new ArrayList<>();

    @ManyToMany
    @Default private List<TextMessage> messages = new ArrayList<>();
}
