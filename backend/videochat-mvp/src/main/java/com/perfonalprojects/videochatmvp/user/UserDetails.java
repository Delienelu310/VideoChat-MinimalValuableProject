package com.perfonalprojects.videochatmvp.user;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;

import lombok.Builder;
import lombok.Data;

@Embeddable
@Data
@Builder
public class UserDetails {

    @Min(1)
    private String description;
}
