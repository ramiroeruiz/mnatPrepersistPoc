package com.mnaut.poc.contact;

import com.mnaut.poc.dto.ContactDTO;
import com.mnaut.poc.entity.Contact;
import com.mnaut.poc.repository.ContactRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

@MicronautTest
@Testcontainers
@Slf4j
public class newPocTC {

    @Inject
    ContactRepository contactRepository;

    @Test
    void poc(){
        log.debug("Entro a test");
        Contact contactDTO = new Contact(11l,null,"123", "John", "Travolta");
        contactRepository.save(contactDTO);


    }
}
