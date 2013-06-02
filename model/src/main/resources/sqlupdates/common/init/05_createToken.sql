CREATE TABLE token (
	id            BIGSERIAL PRIMARY KEY,
	tokenid       CHARACTER VARYING,
	accessuserid  BIGINT NOT NULL,
	created       TIMESTAMP WITH TIME ZONE,
	validгntil    TIMESTAMP WITH TIME ZONE,
	invalid       BOOLEAN,
	type          CHARACTER VARYING,
	parentid      CHARACTER VARYING,
	invalidatedat TIMESTAMP WITH TIME ZONE
);

ALTER TABLE token OWNER TO datacart;