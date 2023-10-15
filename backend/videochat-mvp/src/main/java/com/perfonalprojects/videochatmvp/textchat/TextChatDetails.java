package com.perfonalprojects.videochatmvp.textchat;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Embeddable
@Data
@Builder
public class TextChatDetails {

    @Nonnull
    @NotNull
    @Size(min=1)
    private String title;

    @Size(min=1)
    private String description;
}
