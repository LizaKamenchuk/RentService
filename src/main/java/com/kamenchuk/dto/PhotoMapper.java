package com.kamenchuk.dto;

import com.kamenchuk.model.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    @Mapping(source = "file.bytes", target = "bytes")
    PhotoResponse toDto(Photo photo);
}