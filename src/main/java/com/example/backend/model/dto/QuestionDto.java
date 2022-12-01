package com.example.backend.model.dto;

import com.example.backend.common.model.GenreQuestion;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class QuestionDto {
    private GenreQuestion genreQuestion;
    private String text;
    private Integer score;
    private Double percentCorrect;
    private List<AnswerDto> answer;
}
