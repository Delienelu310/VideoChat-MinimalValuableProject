package com.perfonalprojects.videochatmvp.room;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class RoomDetails {

    @NotNull
    @Nonnull
    @Size(min=1)
    private String title;

    @Size(min=1)
    private String description;
}
