package com.perfonalprojects.videochatmvp.textchat;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Embeddable
@Data
@Builder
public class TextChatDetails {

    @Nonnull
    @NotNull
    @Min(1)
    private String title;

    @Min(1)
    private String description;
}
