BEGIN;

CREATE TYPE user_role AS ENUM ('ADMIN', 'USER' , 'UNAUTHORIZED');

CREATE TABLE IF NOT EXISTS BookCreatureType (
    ID SERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS client (
    ID SERIAL PRIMARY KEY,
    role user_role NOT NULL,
    isApproved BOOLEAN DEFAULT FALSE,
    isActive BOOLEAN DEFAULT TRUE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    name VARCHAR(255) CHECK (LENGTH(password) > 0) NOT NULL,
    login VARCHAR(255) UNIQUE,
    password VARCHAR(64) CHECK (LENGTH(password) = 64)
);

CREATE TABLE IF NOT EXISTS Ring (
    ID SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (name <> ''),
    power INT CHECK(power > 0),
    weight FLOAT CHECK (weight > 0),
    isActive BOOLEAN DEFAULT TRUE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creatorId INT REFERENCES client(ID),
    updaterId INT REFERENCES client(ID)
);

CREATE TABLE IF NOT EXISTS MagicCity (
    ID SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL CHECK (name <> ''),
    area INT NOT NULL CHECK (area > 0),
    population INT NOT NULL CHECK (area > 0),
    establishmentAt DATE,
    id_governor_type INT REFERENCES BookCreatureType(ID) NOT NULL,
    population_density INT CHECK (population > 0),
    isCapital BOOLEAN DEFAULT FALSE,
    isActive BOOLEAN DEFAULT TRUE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creatorId INT REFERENCES client(ID),
    updaterId INT REFERENCES client(ID)
);

CREATE TABLE IF NOT EXISTS BookCreature (
    ID SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    coordinates POINT NOT NULL,
    age INT NOT NULL CHECK (age > 0),
    id_type INT REFERENCES BookCreatureType(ID) NOT NULL,
    id_city INT REFERENCES MagicCity(ID) NOT NULL,
    id_ring INT REFERENCES Ring(ID) NOT NULL,
    attack_level FLOAT CHECK (attack_level > 0),
    isActive BOOLEAN DEFAULT TRUE,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    creatorId INT REFERENCES client(ID),
    updaterId INT REFERENCES client(ID)
);

COMMIT;
