package com.mnaut.poc.service;

import com.mnaut.poc.dto.ContactDTO;
import com.mnaut.poc.entity.Contact;
import com.mnaut.poc.repository.ContactRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class ContactService implements IContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public ContactDTO getContact(String idNumber) {
        log.debug("getContact - {}", idNumber);
        Contact contact = contactRepository.findByIdNumber(idNumber)
                .orElseThrow();
        return contactToContactDTO(contact);
    }

    @Override
    public ContactDTO saveContact(ContactDTO contactDTO) {
        log.debug("saveContact");
        Contact contact = contactDTOToContact(contactDTO);
        contact = contactRepository.save(contact);
        return contactToContactDTO(contact);
    }

    @Override
    public ContactDTO updateContact(String idNumber, ContactDTO contactDTO) {
        log.debug("updateContact - {}", idNumber);
        Contact contact = contactRepository.findByIdNumber(idNumber)
                .orElseThrow();
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact = contactRepository.update(contact);

        return contactToContactDTO(contact);
    }

    @Override
    public void deleteContact(String idNumber) {
        log.debug("deleteContact - {}", idNumber);
        Contact contact = contactRepository.findByIdNumber(idNumber)
                .orElseThrow();
        contactRepository.delete(contact);
    }

    private Contact contactDTOToContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setIdNumber(contactDTO.getIdNumber());
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        return contact;
    }

    private ContactDTO contactToContactDTO(Contact contact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setIdNumber(contact.getIdNumber());
        contactDTO.setFirstName(contact.getFirstName());
        contactDTO.setLastName(contact.getLastName());
        return contactDTO;
    }

}
