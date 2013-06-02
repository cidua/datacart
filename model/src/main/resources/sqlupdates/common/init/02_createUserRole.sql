CREATE TABLE userrole (
	id         BIGSERIAL PRIMARY KEY,
	accessrole CHARACTER VARYING
);

ALTER TABLE userrole OWNER TO datacart;