package com.perfonalprojects.videochatmvp.user;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Embeddable
@Data
@Builder
public class AppAuthority {

    @Nonnull
    @NotNull
    private String authority;
}
