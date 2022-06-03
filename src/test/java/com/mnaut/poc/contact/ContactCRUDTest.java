package com.mnaut.poc.contact;

import com.mnaut.poc.dto.ContactDTO;
import com.mnaut.poc.entity.Contact;
import com.mnaut.poc.repository.ContactRepository;
import com.mnaut.poc.service.IContactService;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@MicronautTest
@Testcontainers
public class ContactCRUDTest {

    @Inject
    IContactService contactService;

    @Inject
    ContactRepository contactRepository;

    @Test
    public void versionIncreaseTest() {
        String idNumber = "20892129";

        log.debug("Perform a Contact insertion");
        ContactDTO contactDTO = new ContactDTO(idNumber, "John", "Travolta");
        contactService.saveContact(contactDTO);

        log.debug("Retrieve inserted contact");
        Contact contact = contactRepository.findByIdNumber(idNumber)
                .orElseThrow();

        log.debug("Assert initial version is 0");
        Assertions.assertEquals(0L, contact.getVersion());

        ContactDTO contactUpdateDTO = new ContactDTO(idNumber, "John", "Wick");

        log.debug("Perform a Contact update");
        contactService.updateContact(idNumber, contactUpdateDTO);

        log.debug("Retrieve updated contact");
        Contact contactUpdate = contactRepository.findByIdNumber(idNumber)
                .orElseThrow();

        log.debug("Assert version is 1");
        Assertions.assertEquals(1L, contactUpdate.getVersion());
    }

}
