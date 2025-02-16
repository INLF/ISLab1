BEGIN;

CREATE TABLE IF NOT EXISTS coordinates (
    ID SERIAL PRIMARY KEY,
    x FLOAT NOT NULL,
    y FLOAT NULL
);


CREATE TABLE IF NOT EXISTS client (
    ID SERIAL PRIMARY KEY,
    role VARCHAR(255) NOT NULL,
    is_approved BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    name VARCHAR(255) CHECK (LENGTH(password) > 0) NOT NULL,
    login VARCHAR(255) UNIQUE,
    password VARCHAR(64) CHECK (LENGTH(password) >= 64)
);

CREATE TABLE IF NOT EXISTS ring (
    ID SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (name <> ''),
    power INT CHECK(power > 0),
    weight FLOAT CHECK (weight > 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creator_id INT REFERENCES client(ID),
    updater_id INT REFERENCES client(ID)
);

CREATE TABLE IF NOT EXISTS magic_city (
    ID SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (name <> ''),
    area INT NOT NULL CHECK (area > 0),
    population INT NOT NULL CHECK (area > 0),
    establishment_at DATE,
    governor_type VARCHAR(255) NOT NULL,
    population_density INT CHECK (population > 0),
    is_capital BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creator_id INT REFERENCES client(ID),
    updater_id INT REFERENCES client(ID)
);

CREATE TABLE IF NOT EXISTS book_creature (
    ID SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    id_coordinates INT REFERENCES coordinates(ID) NOT NULL,
    age INT NOT NULL CHECK (age > 0),
    id_type VARCHAR(255) NOT NULL,
    id_city INT REFERENCES magic_city(ID) NOT NULL,
    id_ring INT REFERENCES ring(ID) NOT NULL,
    attack_level FLOAT CHECK (attack_level > 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creator_id INT REFERENCES client(ID),
    updater_id INT REFERENCES client(ID)
);


COMMIT;
