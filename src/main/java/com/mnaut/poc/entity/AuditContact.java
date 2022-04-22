package com.mnaut.poc.entity;


import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "poc_auditcontact")
public class AuditContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    Long version;

    @DateCreated
    @Column(name = "datecreated")
    private LocalDateTime dateCreated;

    @DateUpdated
    @Column(name = "dateupdated")
    private LocalDateTime dateUpdated;

    @Column(name = "createuser")
    String createUser;

    @Column(name = "updateuser")
    String updateUser;

    @Column(name = "firstname")
    String firstName;

    @Column(name = "lastname")
    String lastName;

    @Column(name = "idnumber")
    String idNumber;

}
