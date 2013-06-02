CREATE TABLE grantedrole (
	id       BIGSERIAL PRIMARY KEY,
	rolename CHARACTER VARYING,
	entityid BIGSERIAL NOT NULL
);

ALTER TABLE grantedrole OWNER TO datacart;