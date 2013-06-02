CREATE TABLE dictionary (
	id       BIGSERIAL PRIMARY KEY,
	word     CHARACTER VARYING,
	spelling CHARACTER VARYING
);

ALTER TABLE dictionary OWNER TO datacart;