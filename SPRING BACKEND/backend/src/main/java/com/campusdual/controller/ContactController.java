package com.campusdual.controller;

import com.campusdual.api.IContactService;
import com.campusdual.model.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private IContactService contactService;

    @GetMapping(value = "/test")
    public String testController() {
        return "Hola!!!!!";
    }

    @PostMapping(value = "/test")
    public String testController(@RequestBody String name) {
        return "Hola " + name;
    }

    @PostMapping(value="/get")
    public ContactDto queryContact(@RequestBody ContactDto contactDto) {
        try {
            return contactService.queryContact(contactDto);
        } catch (EntityNotFoundException e) {
            return new ContactDto();
        }
    }
    @GetMapping(value="/getAll")
    public List<ContactDto> queryAllContacts() {
        return contactService.queryAllContacts();
    }

    @PostMapping("/add")
    public int addContact(@RequestBody ContactDto contactDto) {
        return contactService.insertContact(contactDto);
    }

    @PutMapping("/update")
    public int updateContact(@RequestBody ContactDto contactDto) {
        return contactService.updateContact(contactDto);
    }

    @DeleteMapping("/delete")
    public int deleteContact(@RequestBody ContactDto contactDto) {
        return contactService.deleteContact(contactDto);
    }

    @PostMapping("/getname")
    public List<ContactDto> queryContactsByName(@RequestBody ContactDto contactDto) {
        return contactService.queryContactsByName(contactDto);
    }
}
