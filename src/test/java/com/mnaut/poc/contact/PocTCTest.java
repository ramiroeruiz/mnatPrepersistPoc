package com.mnaut.poc.contact;

import com.mnaut.poc.entity.AuditContact;
import com.mnaut.poc.repository.AuditContactRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

@MicronautTest
@Testcontainers
@Slf4j
public class PocTCTest {

    @Inject
    AuditContactRepository auditContactRepository;

    @Test
    void poc(){
        log.debug("Entro a test");
        AuditContact auditContact = new AuditContact();
        auditContact.setLastName("Juan");
        auditContact.setFirstName("Carlos");
        auditContact.setIdNumber("12345678");

        auditContactRepository.save(auditContact);

        AuditContact contact = auditContactRepository.findByIdNumber("12345678").orElseGet(null);
        Assertions.assertNotNull(contact);
        log.debug("Contact: {}",contact.getVersion());


    }
}
