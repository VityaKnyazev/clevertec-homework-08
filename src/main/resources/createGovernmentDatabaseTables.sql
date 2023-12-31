CREATE TABLE IF NOT EXISTS person (
id UUID DEFAULT gen_random_uuid(),
name CHARACTER VARYING(10) NOT NULL,
surname CHARACTER VARYING(20) NOT NULL,
email CHARACTER VARYING(45) NOT NULL,
citizenship CHARACTER VARYING(25) NOT NULL,
age SMALLINT NOT NULL,

CHECK(email ~* '^[\w_-]+@[a-z]+\.[a-z]{2,3}$'),
CHECK (age >= 0),

UNIQUE(email),
PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS service (
id UUID DEFAULT gen_random_uuid(),
person_id UUID NOT NULL,
name CHARACTER VARYING(30) NOT NULL,
description CHARACTER VARYING(80) NOT NULL,
price NUMERIC(11, 2) NOT NULL,

CHECK(price >= 0::NUMERIC),

CONSTRAINT fk_person
FOREIGN KEY (person_id)
REFERENCES person (id)
ON DELETE NO ACTION ON UPDATE NO ACTION
);
