package com.example.backend.model.entity;

import com.example.backend.common.model.SuperEntity;
import com.example.backend.common.model.GenreQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "question")
@NoArgsConstructor
public class QuestionEntity extends SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "genre_question")
    private GenreQuestion genreQuestion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room")
    private RoomEntity roomId;
    private String text;
    private Integer score;


    @OneToMany(mappedBy = "questionId", cascade = CascadeType.REMOVE)
    private List<AnswerEntity> answer = new ArrayList<>();
    @OneToMany(mappedBy = "questionId")
    private List<UserQuestionEntity> userQuestionEntitySet = new ArrayList<>();
}
