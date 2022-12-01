package com.example.backend.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class RoomDto {
    private String name;
    private Integer capacity;
    private String type;
    private String url;
    private String code;
    private List<QuestionDto> questions;
    private List<UserRoomDto> userRoom;
//    private List<UserQuestionDto> userQuestionEntities;
}
