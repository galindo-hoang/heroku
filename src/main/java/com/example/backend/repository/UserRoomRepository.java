package com.example.backend.repository;

import com.example.backend.model.entity.UserRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoomRepository extends JpaRepository<UserRoomEntity,Long>, UserRoomRepositoryCustom {
    Optional<UserRoomEntity> findUserRoomEntityByUserId_IdAndRoomId_Id(Long user, Long room);
}
