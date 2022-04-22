package com.mnaut.poc.service;

import com.mnaut.poc.dto.ContactDTO;

public interface IContactService {

    ContactDTO getContact(String idNumber);

    ContactDTO saveContact(ContactDTO contactDTO);

    ContactDTO updateContact(String idNumber, ContactDTO contactDTO);

    void deleteContact(String idNumber);

}
