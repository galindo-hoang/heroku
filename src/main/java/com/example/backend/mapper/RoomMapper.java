package com.example.backend.mapper;

import com.example.backend.model.dto.RoomDto;
import com.example.backend.model.entity.RoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomEntity dtoToEntity(RoomDto roomDto);
    @Mapping(target = "userRoom", ignore = true)
    RoomDto entityToDto(RoomEntity roomEntity);
}
