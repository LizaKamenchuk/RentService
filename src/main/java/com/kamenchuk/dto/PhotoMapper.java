package com.kamenchuk.dto;

import com.kamenchuk.model.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    @Mapping(source = "file.bytes", target = "fileBytes")
    @Mapping(source = "file.name", target = "fileName")
    @Mapping(source = "id",target = "fileId")
    PhotoResponse toDto(Photo photo);
}