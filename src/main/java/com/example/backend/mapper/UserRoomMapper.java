package com.example.backend.mapper;

import com.example.backend.model.dto.UserRoomDto;
import com.example.backend.model.entity.UserRoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRoomMapper {
    @Mapping(target = "userName", source = "userId.userName")
    @Mapping(target = "email", source = "userId.email")
    @Mapping(target = "imageURL", source = "userId.imageURL")
    UserRoomDto entityToDto(UserRoomEntity userRoomEntity);
}
