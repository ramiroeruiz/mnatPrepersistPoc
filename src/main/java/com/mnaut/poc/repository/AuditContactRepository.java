package com.mnaut.poc.repository;

import com.mnaut.poc.entity.AuditContact;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface AuditContactRepository extends CrudRepository<AuditContact, Long> {

    Optional<AuditContact> findByIdNumber(String idNumber);

}
