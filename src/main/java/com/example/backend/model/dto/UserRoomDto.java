package com.example.backend.model.dto;

import com.example.backend.common.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRoomDto {
    private String email;
    private Role role;
    private String userName;
    private String imageURL;
    private Integer score;
}
