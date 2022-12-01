package com.example.backend.repository.impl;

import com.example.backend.model.entity.RoomEntity;
import com.example.backend.repository.RoomRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static com.example.backend.model.entity.QAccountEntity.accountEntity;
import static com.example.backend.model.entity.QRoomEntity.roomEntity;
import static com.example.backend.model.entity.QUserRoomEntity.userRoomEntity;


@Repository
public class RoomRepositoryCustomImpl implements RoomRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tuple> getGroupDetail(String name) {
        return new JPAQueryFactory(entityManager)
                .from(roomEntity).where(roomEntity.name.eq(name))
                .rightJoin(userRoomEntity).on(roomEntity.id.eq(userRoomEntity.roomId.id))
                .innerJoin(accountEntity).on(accountEntity.id.eq(userRoomEntity.userId.id))
                .select(roomEntity,accountEntity,userRoomEntity.score,userRoomEntity.role)
                .fetch();
    }

    @Override
    public List<RoomEntity> getListRoomJoined(String email) {
        return new JPAQueryFactory(entityManager)
                .from(userRoomEntity).where(userRoomEntity.userId.email.eq(email))
                .join(roomEntity).on(roomEntity.id.eq(userRoomEntity.roomId.id))
                .select(roomEntity)
                .fetch();
    }
}
