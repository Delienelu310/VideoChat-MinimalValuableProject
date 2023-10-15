package com.perfonalprojects.videochatmvp.user;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Embeddable
@Data
@Builder
public class AppUserDetails {

    @Size(min=1)
    private String description;
}
