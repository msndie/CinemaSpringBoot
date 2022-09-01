CREATE TABLE IF NOT EXISTS images (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    uuid UUID NOT NULL ,
    name TEXT NOT NULL ,
    mime TEXT NOT NULL ,
    size BIGINT NOT NULL,
    type TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS halls (
    id BIGSERIAL PRIMARY KEY,
    serial_number BIGINT NOT NULL UNIQUE,
    number_of_seats INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS films (
    id BIGSERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    year INTEGER NOT NULL,
    age_restrictions INTEGER NOT NULL,
    description TEXT NOT NULL,
    image_id BIGINT,
    CONSTRAINT images
        FOREIGN KEY (image_id)
            REFERENCES images(id)
);

CREATE TABLE IF NOT EXISTS sessions (
    id BIGSERIAL PRIMARY KEY,
    film_id BIGINT NOT NULL,
    hall_id BIGINT NOT NULL,
    price NUMERIC NOT NULL,
    date_time TIMESTAMP NOT NULL,
    CONSTRAINT halls
        FOREIGN KEY (hall_id)
            REFERENCES halls(id),
    CONSTRAINT films
        FOREIGN KEY (film_id)
            REFERENCES films(id)
);

CREATE TABLE IF NOT EXISTS messages (
    id BIGSERIAL PRIMARY KEY,
    user_name TEXT NOT NULL,
    message TEXT NOT NULL,
    film_id BIGINT NOT NULL,
    CONSTRAINT films
        FOREIGN KEY (film_id)
            REFERENCES films(id)
);


CREATE TABLE IF NOT EXISTS users (
     id BIGSERIAL PRIMARY KEY,
     first_name TEXT NOT NULL,
     last_name TEXT NOT NULL,
     email TEXT NOT NULL UNIQUE,
     phone_number TEXT NOT NULL,
     password TEXT NOT NULL,
     role TEXT NOT NULL,
     unique_code UUID,
     enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS users_sessions (
    id BIGSERIAL PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    ip TEXT NOT NULL,
    user_id BIGINT,
    CONSTRAINT users
        FOREIGN KEY (user_id)
            REFERENCES users(id)
);