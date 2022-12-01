package com.example.backend.controller;

import com.example.backend.common.controller.BaseController;
import com.example.backend.common.model.EmailDto;
import com.example.backend.common.model.Role;
import com.example.backend.mapper.RoomMapper;
import com.example.backend.mapper.UserRoomMapper;
import com.example.backend.model.dto.RoomDto;
import com.example.backend.model.dto.UserRoomDto;
import com.example.backend.model.entity.AccountEntity;
import com.example.backend.model.entity.QuestionEntity;
import com.example.backend.model.entity.RoomEntity;
import com.example.backend.model.entity.UserRoomEntity;
import com.example.backend.model.request.CreateRoomRequest;
import com.example.backend.model.request.JoinRequest;
import com.example.backend.model.request.RemoveMemberRequest;
import com.example.backend.service.EmailService;
import com.example.backend.service.RoomService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("room")
@RequiredArgsConstructor
public class RoomController extends BaseController {
    private final EmailService emailService;
    private final RoomMapper roomMapper;
    private final RoomService roomService;
    private final UserRoomMapper userRoomMapper;
    @GetMapping("invite/sendEmail")
    public ResponseEntity<String> sendEmail(String email) {
        return new ResponseEntity<>(emailService.sendEmailInviteToRoom(new EmailDto("hello world",email)), HttpStatus.OK);
    }

    @PostMapping("generate")
    public ResponseEntity<RoomDto> generate(@RequestBody CreateRoomRequest createRoomRequest) {
        return new ResponseEntity<>(roomMapper.entityToDto(roomService.createRoom(createRoomRequest)),HttpStatus.ACCEPTED);
    }

    @GetMapping("detail")
    public ResponseEntity<RoomDto> getDetail(String name) {
        List<Tuple> data = roomService.getDetail(name);
        RoomEntity roomEntity = (RoomEntity) data.get(0).toArray()[0];
        RoomDto roomDto = roomMapper.entityToDto(roomEntity);
        LinkedList<UserRoomDto> users = new LinkedList<>();
        data.forEach(it -> {
            UserRoomDto temp = new UserRoomDto();
            AccountEntity account = (AccountEntity) it.toArray()[1];
            temp.setEmail(account.getEmail());
            temp.setUserName(account.getUserName());
            temp.setImageURL(account.getImageURL());
            temp.setScore((Integer) it.toArray()[2]);
            temp.setRole((Role) it.toArray()[3]);
            users.add(temp);
        });
        roomDto.setUserRoom(users);
        return ResponseEntity.status(HttpStatus.OK).body(roomDto);
    }

    @PostMapping("join")
    public ResponseEntity<UserRoomDto> join(@RequestBody JoinRequest joinRequest) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userRoomMapper.entityToDto(roomService.join(joinRequest)));
    }

    @PostMapping("remove")
    public ResponseEntity<Boolean> removeMember(@RequestBody RemoveMemberRequest removeMemberRequest) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(roomService.removeMember(removeMemberRequest));
    }

}
