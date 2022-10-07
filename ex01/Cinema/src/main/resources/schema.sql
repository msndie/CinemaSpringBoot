CREATE SCHEMA IF NOT EXISTS ex01_boot;

CREATE SEQUENCE IF NOT EXISTS ex01_boot.halls_id AS BIGINT START WITH 4;
CREATE SEQUENCE IF NOT EXISTS ex01_boot.films_id AS BIGINT START WITH 4;
CREATE SEQUENCE IF NOT EXISTS ex01_boot.sessions_id AS BIGINT START WITH 4;
CREATE SEQUENCE IF NOT EXISTS ex01_boot.messages_id AS BIGINT START WITH 7;

CREATE TABLE IF NOT EXISTS ex01_boot.images (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    uuid UUID NOT NULL ,
    name TEXT NOT NULL ,
    mime TEXT NOT NULL ,
    size BIGINT NOT NULL,
    type TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS ex01_boot.halls (
    id BIGINT DEFAULT nextval('ex01_boot.halls_id') PRIMARY KEY,
    serial_number BIGINT NOT NULL UNIQUE,
    number_of_seats INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS ex01_boot.films (
    id BIGINT DEFAULT nextval('ex01_boot.films_id') PRIMARY KEY,
    title TEXT NOT NULL,
    year INTEGER NOT NULL,
    age_restrictions INTEGER NOT NULL,
    description TEXT NOT NULL,
    image_id BIGINT,
    CONSTRAINT images
        FOREIGN KEY (image_id)
            REFERENCES ex01_boot.images(id)
);

CREATE TABLE IF NOT EXISTS ex01_boot.sessions (
    id BIGINT DEFAULT nextval('ex01_boot.sessions_id') PRIMARY KEY,
    film_id BIGINT NOT NULL,
    hall_id BIGINT NOT NULL,
    price NUMERIC NOT NULL,
    date_time TIMESTAMP NOT NULL,
    CONSTRAINT halls
        FOREIGN KEY (hall_id)
            REFERENCES ex01_boot.halls(id),
    CONSTRAINT films
        FOREIGN KEY (film_id)
            REFERENCES ex01_boot.films(id)
);

CREATE TABLE IF NOT EXISTS ex01_boot.messages (
    id BIGINT DEFAULT nextval('ex01_boot.messages_id') PRIMARY KEY,
    user_name TEXT NOT NULL,
    message TEXT NOT NULL,
    film_id BIGINT NOT NULL,
    CONSTRAINT films
        FOREIGN KEY (film_id)
            REFERENCES ex01_boot.films(id)
);


CREATE TABLE IF NOT EXISTS ex01_boot.users (
     id BIGSERIAL PRIMARY KEY,
     first_name TEXT NOT NULL,
     last_name TEXT NOT NULL,
     email TEXT NOT NULL UNIQUE,
     phone_number TEXT NOT NULL,
     password TEXT NOT NULL,
     role TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS ex01_boot.users_sessions (
    id BIGSERIAL PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    ip TEXT NOT NULL,
    user_id BIGINT,
    CONSTRAINT users
        FOREIGN KEY (user_id)
            REFERENCES ex01_boot.users(id)
);

ALTER SEQUENCE ex01_boot.messages_id OWNED BY ex01_boot.messages.id;
ALTER SEQUENCE ex01_boot.sessions_id OWNED BY ex01_boot.sessions.id;
ALTER SEQUENCE ex01_boot.films_id OWNED BY ex01_boot.films.id;
ALTER SEQUENCE ex01_boot.halls_id OWNED BY ex01_boot.halls.id;