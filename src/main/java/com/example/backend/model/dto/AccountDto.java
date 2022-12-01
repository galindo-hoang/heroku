package com.example.backend.model.dto;

import com.example.backend.model.entity.UserQuestionEntity;
import com.example.backend.model.entity.UserRoomEntity;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class AccountDto implements Serializable {
    private String userName;
    private String email;
    private String password;
    private String imageURL;
    private MultipartFile imageFile;
    private Set<UserRoomEntity> userRoomEntities = new HashSet<>();
    private Set<UserQuestionEntity> userQuestionEntities = new HashSet<>();
}
