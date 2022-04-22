## Micronaut Prepersist Proof of Concept

The goal for this repo is to test the micronaut framework and micronaut-data. Particularly the PrePersistEventListener and PreUpdateEventListener event handlers 

 A connection to a DataBase is required in order to run this project. You will find the environment variables needed on application.yml. Those are 
- DB_HOST
- DB_NAME
- DB_USERNAME
- DB_PASSWORD
---
- A ddl.sql file is provided to create 2 tables involved in this POC
---

This project is pretty simple.
There is a POJO named **Contact** with just a few fields (idnumber, firstname, lastname) plus an auto-generated ID and a Version. This entity is handled by a **IContactService** service bean to perform basic CRUD operations. \
There is a second POJO named **AuditContact** which is similar to the previous named one but it has some additional audit fields (datecreated, dateupdated, createuser, updateuser). \
DateCreated and DateUpdated are annotated with the @DateCreated and @DateUpdated annotations provided by micronaut-data. \
On the other hand, The factory bean **AuditContactListener** manages the createuser and updateuser fields through the PrePersistEventListener and PreUpdateEventListener event listeners provided, also, by micronaut-data

When a new **Contact** is created, version is set to 0 and when it's updated for the first time after creation, version is set to 1.
In the case of **AuditContact**, after creation verion is set to 1 though a 0 would be expected. By checking the logs we could find that an insert plus an update are being performed

So the reason for this project is to find out whether this is a bug on micronaut-data or it is working as expected.

On the test package, there is a ContactCrudTest that checks the version is correctly working when saves and updates are performed, and a AuditContactCRUDTest that reproduces the alleged error.