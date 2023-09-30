package com.campusdual.service;

import com.campusdual.api.IContactService;
import com.campusdual.model.Contact;
import com.campusdual.model.dao.ContactDao;
import com.campusdual.model.dto.ContactDto;
import com.campusdual.model.mapper.ContactMapper;
import com.campusdual.model.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ContactService")
@Lazy
public class ContactService implements IContactService {

    @Autowired
    private ContactDao contactDao;

    @Override
    public ContactDto queryContact(ContactDto contactDto) {
        // paso el dto a contacto
        Contact contact = ContactMapper.INSTANCE.toEntity(contactDto);
        // Llamo al dao para que haga la consulta
        Contact contactResult = contactDao.getReferenceById(contact.getId());
        // paso la entidad result a dto
        ContactDto resultado = ContactMapper.INSTANCE.toDTO(contactResult);
        return null;
    }

    @Override
    public List<ContactDto> queryAllContacts() {
        List<Contact> contactList = contactDao.findAll();
        List<ContactDto> result = ContactMapper.INSTANCE.toDTOList(contactList);
        return result;
    }

    @Override
    public List<ContactDto> queryContactsByName(ContactDto contactDto) {
        String name = contactDto.getName();
        if (!(name.contains("%"))) {
            name = "%" + name + "%";
        }
        List<Contact> contactList = contactDao.findByNameLike(name);
        return ContactMapper.INSTANCE.toDTOList(contactList);
    }

    @Override
    public int insertContact(ContactDto contactDto) {
        Contact contact = ContactMapper.INSTANCE.toEntity(contactDto);
        contactDao.saveAndFlush(contact);
        return contact.getId();
    }

    @Override
    public int updateContact(ContactDto contactDto) {
        Contact contact = ContactMapper.INSTANCE.toEntity(contactDto);
        contactDao.saveAndFlush(contact);
        return contact.getId();
    }

    @Override
    public int deleteContact(ContactDto contactDto) {
        int id = contactDto.getId();
        Contact contact = new Contact();
        contact.setId(id);
        contactDao.delete(contact);
        return id;
    }
}
