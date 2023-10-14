package com.perfonalprojects.videochatmvp.user;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Entity
@Data
@Builder
public class AppUser {
    @Id
    private String username;
    @NonNull
    @NotNull
    private String password;

    @Embedded
    private UserDetails userDetails;

    @Embedded
    private AppAuthority[] authorities;
}
