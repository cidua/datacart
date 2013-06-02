CREATE TABLE "user" (
	id             BIGSERIAL PRIMARY KEY,
	email          CHARACTER VARYING,
	password       CHARACTER VARYING,
	enabled        BOOLEAN,
	passwordanswer CHARACTER VARYING,
	firstName      CHARACTER VARYING,
	lastName       CHARACTER VARYING,
	displayName    CHARACTER VARYING,
	company        CHARACTER VARYING,
	country        CHARACTER VARYING,
	city           CHARACTER VARYING,
	zipCode        CHARACTER VARYING,
	phone          CHARACTER VARYING,
	createdAt      TIMESTAMP WITH TIME ZONE DEFAULT NOW() NOT NULL,
	activatedAt    TIMESTAMP WITH TIME ZONE
);

ALTER TABLE "user" OWNER TO datacart;