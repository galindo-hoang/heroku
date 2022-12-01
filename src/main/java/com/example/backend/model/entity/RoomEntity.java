package com.example.backend.model.entity;

import com.example.backend.common.model.GenreRoom;
import com.example.backend.common.model.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class RoomEntity extends SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private Integer capacity = 1;
    private GenreRoom type = GenreRoom.CLASSIC;
    private String url;
    private String code;
    @ManyToOne
    private AccountEntity accountEntity;



    @OneToMany(mappedBy = "roomId")
    private List<QuestionEntity> questions = new ArrayList<>();
    public void addQuestion(QuestionEntity questionEntity) {
        this.questions.add(questionEntity);
        questionEntity.setRoomId(this);
    }
    public void removeQuestion(QuestionEntity questionEntity) {
        this.questions.remove(questionEntity);
        questionEntity.setRoomId(null);
    }

    @OneToMany(mappedBy = "roomId")
    private List<UserRoomEntity> userRoom = new ArrayList<>();
    public void addUserRoom(UserRoomEntity userRoomEntity) {
        this.userRoom.add(userRoomEntity);
        userRoomEntity.setRoomId(this);
    }
    public void removeUserRoom(UserRoomEntity userRoomEntity) {
        this.userRoom.remove(userRoomEntity);
        userRoomEntity.setRoomId(null);
    }

    @OneToMany(mappedBy = "roomId")
    private List<UserQuestionEntity> userQuestion = new ArrayList<>();
    public void addQuestion(UserQuestionEntity userQuestionEntity) {
        this.userQuestion.add(userQuestionEntity);
        userQuestionEntity.setRoomId(this);
    }
    public void removeQuestion(UserQuestionEntity userQuestionEntity) {
        this.userQuestion.remove(userQuestionEntity);
        userQuestionEntity.setRoomId(null);
    }
}
