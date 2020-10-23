package cz.intv.lundegaard.demo.mapper;

import cz.intv.lundegaard.demo.dto.ContactUsDto;
import cz.intv.lundegaard.demo.dto.request.CreateContactUsDto;
import cz.intv.lundegaard.demo.model.ContactUs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ContactUsMapper {
    ContactUsMapper MAPPER = Mappers.getMapper(ContactUsMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "requestType", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ContactUs toContactUs(CreateContactUsDto requestTypeDto);

    @Mapping(source = "requestType.id", target = "requestTypeId")
    @Mapping(source = "requestType.name", target = "requestTypeName")
    ContactUsDto toContactUsDto(ContactUs contactUs);
}
