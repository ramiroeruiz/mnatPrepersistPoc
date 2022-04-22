CREATE TABLE poc_contact (
    id bigserial primary key,
    version bigint,
    idnumber varchar(50) not null,
    firstname varchar(50) not null,
    lastname varchar(50) not null
);

CREATE TABLE poc_auditcontact (
	id bigserial primary key,
	version bigint default 0,
	dateCreated timestamp without time zone,
	dateUpdated timestamp without time zone,
	createuser varchar(50) null,
	updateuser varchar(50) null,
	idnumber varchar(50) not null,
	firstname varchar(50) not null,
	lastname varchar(50) not null
);