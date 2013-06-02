CREATE TABLE usertenantrelation (
	id         BIGSERIAL PRIMARY KEY,
	userid     BIGINT NOT NULL,
	tenantid   BIGINT NOT NULL,
	status     CHARACTER VARYING,
	createdate TIMESTAMP WITH TIME ZONE,
	acceptdate TIMESTAMP WITH TIME ZONE
);

ALTER TABLE usertenantrelation OWNER TO datacart;