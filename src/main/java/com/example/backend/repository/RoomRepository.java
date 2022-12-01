package com.example.backend.repository;

import com.example.backend.model.entity.AccountEntity;
import com.example.backend.model.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long>, RoomRepositoryCustom {
    Optional<RoomEntity> findRoomEntityByUrl(String url);
    Optional<RoomEntity> findRoomEntityByName(String name);

    List<RoomEntity> findRoomEntitiesByAccountEntity_Email(String email);

}
