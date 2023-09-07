package org.kamenchuk.mapper;

import org.kamenchuk.dto.carDTO.model_markDTO.MarkDto;
import org.kamenchuk.models.Mark;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarkMapper {
    Mark toModel(MarkDto dto);
}
