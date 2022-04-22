package com.mnaut.poc.listener;

import com.mnaut.poc.entity.AuditContact;
import io.micronaut.context.annotation.Factory;
import io.micronaut.data.event.listeners.PrePersistEventListener;
import io.micronaut.data.event.listeners.PreUpdateEventListener;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Factory
public class AuditContactListener {

    private static final String createUser = "CREATEADMIN";
    private static final String updateUser = "UPDATEADMIN";

    @Singleton
    PrePersistEventListener<AuditContact> beforeEntityPersist() {
        return (auditContact) -> {
            log.debug("Inserting auditContact: {}", auditContact.getIdNumber() );
            auditContact.setCreateUser(createUser);
            auditContact.setUpdateUser(createUser);
            return true;
        };
    }

    @Singleton
    PreUpdateEventListener<AuditContact> beforeEntityUpdate() {
        return (auditContact) -> {
            log.debug("Updating auditContact: {}", auditContact.getIdNumber() );
            auditContact.setUpdateUser(updateUser);
            return true;
        };
    }

}
