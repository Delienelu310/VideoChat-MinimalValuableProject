package com.perfonalprojects.videochatmvp.textchat;

import java.time.LocalDateTime;

import com.perfonalprojects.videochatmvp.user.AppUser;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @Min(1)
    private String content;

    @Nonnull
    private LocalDateTime timestamp;

    @Nonnull
    private TextChat textChat; 

    @Nonnull
    private AppUser author;
}
