package com.campusdual.model.mapper;

import com.campusdual.model.Contact;
import com.campusdual.model.dto.ContactDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);
    ContactDto toDTO(Contact contact);
    List<ContactDto> toDTOList(List<Contact> contacts);
    Contact toEntity(ContactDto contactDto);
}
