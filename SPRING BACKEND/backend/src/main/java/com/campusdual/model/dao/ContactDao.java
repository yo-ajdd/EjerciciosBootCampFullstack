package com.campusdual.model.dao;

import com.campusdual.model.Contact;
import com.campusdual.model.dto.ContactDto;
import com.campusdual.model.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactDao extends JpaRepository<Contact,Integer> {

    public List<Contact> findByNameLike(String name);
}

