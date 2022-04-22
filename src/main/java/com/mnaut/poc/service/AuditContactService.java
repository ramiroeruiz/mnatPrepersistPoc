package com.mnaut.poc.service;

import com.mnaut.poc.dto.ContactDTO;
import com.mnaut.poc.entity.AuditContact;
import com.mnaut.poc.repository.AuditContactRepository;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class AuditContactService implements IAuditContactService {

    private final AuditContactRepository auditContactRepository;

    public AuditContactService(AuditContactRepository auditContactRepository) {
        this.auditContactRepository = auditContactRepository;
    }

    @Override
    public ContactDTO getContact(String idNumber) {
        log.debug("getContact - {}", idNumber);
        AuditContact auditContact = auditContactRepository.findByIdNumber(idNumber)
                .orElseThrow();
        return contactToContactDTO(auditContact);
    }

    @Override
    public ContactDTO saveContact(ContactDTO contactDTO) {
        log.debug("saveContact");
        AuditContact auditContact = contactDTOToContact(contactDTO);
        auditContact = auditContactRepository.save(auditContact);
        return contactToContactDTO(auditContact);
    }

    @Override
    public ContactDTO updateContact(String idNumber, ContactDTO contactDTO) {
        log.debug("updateContact - {}", idNumber);
        AuditContact auditContact = auditContactRepository.findByIdNumber(idNumber)
                .orElseThrow();
        auditContact.setFirstName(contactDTO.getFirstName());
        auditContact.setLastName(contactDTO.getLastName());
        auditContact = auditContactRepository.update(auditContact);

        return contactToContactDTO(auditContact);
    }

    @Override
    public void deleteContact(String idNumber) {
        log.debug("deleteContact - {}", idNumber);
        AuditContact auditContact = auditContactRepository.findByIdNumber(idNumber)
                .orElseThrow();
        auditContactRepository.delete(auditContact);
    }

    private AuditContact contactDTOToContact(ContactDTO contactDTO) {
        AuditContact auditContact = new AuditContact();
        auditContact.setIdNumber(contactDTO.getIdNumber());
        auditContact.setFirstName(contactDTO.getFirstName());
        auditContact.setLastName(contactDTO.getLastName());
        return auditContact;
    }

    private ContactDTO contactToContactDTO(AuditContact auditContact) {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setIdNumber(auditContact.getIdNumber());
        contactDTO.setFirstName(auditContact.getFirstName());
        contactDTO.setLastName(auditContact.getLastName());
        return contactDTO;
    }

}
