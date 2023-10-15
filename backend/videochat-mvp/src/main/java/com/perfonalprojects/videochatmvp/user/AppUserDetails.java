package com.perfonalprojects.videochatmvp.user;

import jakarta.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class AppUserDetails {

    private String description;
}
