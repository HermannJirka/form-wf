package cz.intv.lundegaard.demo.mapper;

import cz.intv.lundegaard.demo.dto.RequestTypeDto;
import cz.intv.lundegaard.demo.dto.request.CreateRequestTypeDto;
import cz.intv.lundegaard.demo.dto.request.UpdateRequestTypeDto;
import cz.intv.lundegaard.demo.model.RequestType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RequestTypeMapper {
    RequestTypeMapper MAPPER = Mappers.getMapper(RequestTypeMapper.class);

    @Mapping(target = "id", ignore = true)
    RequestType toRequestType(CreateRequestTypeDto requestTypeDto);

    RequestTypeDto toRequestTypeDto(RequestType requestType);

    @Mapping(target = "id", ignore = true)
    RequestType updateRequestType(@MappingTarget RequestType requestType,
                                  UpdateRequestTypeDto updateRequestTypeDto);
}
