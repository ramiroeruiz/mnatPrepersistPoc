package com.mnaut.poc.repository;

import com.mnaut.poc.entity.Contact;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    Optional<Contact> findByIdNumber(String idNumber);

}
