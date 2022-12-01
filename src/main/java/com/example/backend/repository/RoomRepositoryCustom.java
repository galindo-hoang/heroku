package com.example.backend.repository;

import com.example.backend.model.entity.RoomEntity;
import com.querydsl.core.Tuple;

import java.util.List;

public interface RoomRepositoryCustom {
    List<Tuple> getGroupDetail(String name);
    List<RoomEntity> getListRoomJoined(String email);

}
