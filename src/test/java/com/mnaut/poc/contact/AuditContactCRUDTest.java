package com.mnaut.poc.contact;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mnaut.poc.dto.ContactDTO;
import com.mnaut.poc.entity.AuditContact;
import com.mnaut.poc.repository.AuditContactRepository;
import com.mnaut.poc.service.IAuditContactService;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
@MicronautTest
public class AuditContactCRUDTest {

    @Inject
    IAuditContactService auditContactService;

    @Inject
    AuditContactRepository auditContactRepository;

    @Test
    public void versionIncreaseTest() {
        String idNumber = "20892129";

        log.debug("Perform a Contact insertion");
        ContactDTO contactDTO = new ContactDTO(idNumber, "John", "Travolta");
        auditContactService.saveContact(contactDTO);

        log.debug("Retrieve inserted contact");
        AuditContact auditContact = auditContactRepository.findByIdNumber(idNumber)
                .orElseThrow();

        logAuditContactAsJson(auditContact);

        log.debug("Assert initial version is 0");
        Assertions.assertEquals(0L, auditContact.getVersion());

        ContactDTO contactUpdateDTO = new ContactDTO(idNumber, "John", "Wick");

        log.debug("Perform a Contact update");
        auditContactService.updateContact(idNumber, contactUpdateDTO);

        log.debug("Retrieve updated contact");
        AuditContact auditContactUpdate = auditContactRepository.findByIdNumber(idNumber)
                .orElseThrow();

        logAuditContactAsJson(auditContactUpdate);

        log.debug("Assert version is 1");
        Assertions.assertEquals(1L, auditContactUpdate.getVersion());
    }

    private void logAuditContactAsJson(AuditContact auditContact) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            log.debug("AuditContact as JSON --> {}", mapper.writeValueAsString(auditContact));
        } catch (JsonProcessingException e) {
            log.error("ERROR serializing auditContact " + this.getClass().getCanonicalName(), e);
        }
    }

}
