package com.example.backend.service.impl;

import com.example.backend.common.model.Role;
import com.example.backend.common.utils.CodeGeneratorUtils;
import com.example.backend.exception.ResourceInvalidException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.entity.AccountEntity;
import com.example.backend.model.entity.RoomEntity;
import com.example.backend.model.entity.UserRoomEntity;
import com.example.backend.model.request.CreateRoomRequest;
import com.example.backend.model.request.JoinRequest;
import com.example.backend.model.request.RemoveMemberRequest;
import com.example.backend.repository.AccountRepository;
import com.example.backend.repository.RoomRepository;
import com.example.backend.repository.UserRoomRepository;
import com.example.backend.service.RoomService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoomServiceImpl implements RoomService {
    private final EntityManager entityManager;
    private final AccountRepository accountRepository;
    private final RoomRepository roomRepository;
    private final UserRoomRepository userRoomRepository;

    public UserRoomEntity join(JoinRequest joinRequest) {
        AccountEntity accountEntity = accountRepository
                .findAccountEntityByEmail(joinRequest.getEmail())
                .get();
        RoomEntity roomEntity = roomRepository
                .findRoomEntityByUrl(joinRequest.getUrl())
                .get();
        List<UserRoomEntity> checking = userRoomRepository.fetchDataFromAccountAndRoom(accountEntity.getId(), roomEntity.getId());
        if(!checking.isEmpty()) throw new ResourceInvalidException("account " + joinRequest.getEmail() + " exists in room");
        if (roomEntity.getCode().equals(joinRequest.getCode())) {
            UserRoomEntity userRoomEntity = new UserRoomEntity(0, null, null, Role.MEMBER, 0);
            accountEntity.addUserRoom(userRoomEntity);
            roomEntity.addUserRoom(userRoomEntity);
            accountRepository.save(accountEntity);
            roomRepository.save(roomEntity);
            return userRoomRepository.save(userRoomEntity);
        } else throw new ResourceNotFoundException("code invalid");
    }

    @Override
    public RoomEntity createRoom(CreateRoomRequest createRoomRequest) {
        AccountEntity accountEntity = accountRepository
                .findAccountEntityByEmail(createRoomRequest.getEmail())
                .get();
        if (roomRepository.findRoomEntityByName(createRoomRequest.getName()) == null) {
            RoomEntity roomEntity = new RoomEntity();
            roomEntity.setName(createRoomRequest.getName());
            roomEntity.setCode(CodeGeneratorUtils.invoke());
            roomEntity.setUrl("http://localhost:3000/group/detail/" + createRoomRequest.getName());
            UserRoomEntity userRoomEntity = new UserRoomEntity();
            userRoomEntity.setRole(Role.OWNER);
            accountEntity.addUserRoom(userRoomEntity);
            accountEntity.addRoom(roomEntity);
            roomEntity.addUserRoom(userRoomEntity);
            accountRepository.save(accountEntity);
            roomRepository.save(roomEntity);
            userRoomRepository.save(userRoomEntity);
            return roomEntity;
        } else throw new ResourceInvalidException("name room {%s} exists");
    }


    @Override
    public Boolean removeMember(RemoveMemberRequest removeMemberRequest) {
        AccountEntity accountEntity = accountRepository.findAccountEntityByEmail(removeMemberRequest.getGmail()).get();
        RoomEntity roomEntity = roomRepository.findRoomEntityByName(removeMemberRequest.getNameRoom()).get();
        UserRoomEntity userRoomEntity = userRoomRepository.findUserRoomEntityByUserId_IdAndRoomId_Id(accountEntity.getId(), roomEntity.getId()).get();
        accountEntity.removeUserRoom(userRoomEntity);
        roomEntity.removeUserRoom(userRoomEntity);
        userRoomRepository.delete(userRoomEntity);
        return true;
    }

    @Override
    public List<RoomEntity> getListRoomCreated(String email) {
        return roomRepository.findRoomEntitiesByAccountEntity_Email(email);
    }

    @Override
    public List<RoomEntity> fetchRoomsJoined(String email) {
        if (email.isEmpty()) throw new ResourceInvalidException("email invalid");
        return roomRepository.getListRoomJoined(email);
    }

    @Override
    public List<Tuple> getDetail(String name) {
        return roomRepository.getGroupDetail(name);
    }
}
