package com.perfonalprojects.videochatmvp.user;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

@Embeddable
@Data
@Builder
public class UserDetails {
    private AppUser user;
}
