CREATE TABLE tenant (
	id   BIGSERIAL PRIMARY KEY,
	name CHARACTER VARYING
);

ALTER TABLE tenant OWNER TO datacart;