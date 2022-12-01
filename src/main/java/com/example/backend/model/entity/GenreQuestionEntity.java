package com.example.backend.model.entity;

import com.example.backend.common.model.SuperEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "genre_question")
@NoArgsConstructor
public class GenreQuestionEntity extends SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

//    @OneToMany(mappedBy = "genreQuestionId")
//    private Set<QuestionEntity> questionEntitySet;
}
