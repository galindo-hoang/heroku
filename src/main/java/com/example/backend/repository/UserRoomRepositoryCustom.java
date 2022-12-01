package com.example.backend.repository;

import com.example.backend.model.entity.UserRoomEntity;

import java.util.List;

public interface UserRoomRepositoryCustom {
    List<UserRoomEntity> fetchDataFromAccountAndRoom(Long userId, Long roomId);
}
