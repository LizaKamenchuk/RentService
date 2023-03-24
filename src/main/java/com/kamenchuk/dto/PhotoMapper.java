package com.kamenchuk.dto;

import com.kamenchuk.model.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PhotoMapper {
    @Mapping(source = "file.bytes", target = "bytes")
    @Mapping(source = "file.name", target = "name")
    PhotoResponse toDto(Photo photo);
}
