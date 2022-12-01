package com.example.backend.model.entity;

import com.example.backend.common.model.SuperEntity;
import com.example.backend.common.model.Role;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_in_room")
public class UserRoomEntity extends SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_room")
    private AccountEntity userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room")
    private RoomEntity roomId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "role")
    private Role role;
    private Integer score = 0;
}
