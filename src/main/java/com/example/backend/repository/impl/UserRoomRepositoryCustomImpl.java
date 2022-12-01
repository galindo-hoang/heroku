package com.example.backend.repository.impl;

import com.example.backend.model.entity.UserRoomEntity;
import com.example.backend.repository.UserRoomRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.example.backend.model.entity.QUserRoomEntity.userRoomEntity;

@Repository
public class UserRoomRepositoryCustomImpl implements UserRoomRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<UserRoomEntity> fetchDataFromAccountAndRoom(Long userId, Long roomId) {
        return new JPAQueryFactory(entityManager)
                .from(userRoomEntity)
                .where(userRoomEntity.roomId.id.eq(roomId).and(userRoomEntity.userId.id.eq(userId)))
                .select(userRoomEntity).fetch();
    }
}
