package com.perfonalprojects.videochatmvp.textchat;

import java.time.LocalDateTime;

import com.perfonalprojects.videochatmvp.user.AppUser;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class TextMessage {

    @Id
    private Long id;

    @Nonnull
    @NotNull
    @Size(min=1)
    private String content;

    @Nonnull
    private LocalDateTime timestamp;

    @Nonnull
    @ManyToOne
    private TextChat textChat; 

    @Nonnull
    @ManyToOne
    private AppUser author;
}
