CREATE TABLE IF NOT EXISTS acl_sid (
                         id SERIAL PRIMARY KEY,
                         principal BOOLEAN NOT NULL,
                         sid VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS acl_class (
                           id SERIAL PRIMARY KEY,
                           class VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS acl_object_identity (
                                     id SERIAL PRIMARY KEY,
                                     object_id_class INT NOT NULL,
                                     object_id_identity BIGINT NOT NULL,
                                     parent_object INT DEFAULT NULL,
                                     owner_sid INT DEFAULT NULL,
                                     entries_inheriting BOOLEAN NOT NULL,
                                     CONSTRAINT unique_acl_object UNIQUE (object_id_class, object_id_identity)
);

CREATE TABLE IF NOT EXISTS acl_entry (
                           id SERIAL PRIMARY KEY,
                           acl_object_identity INT NOT NULL,
                           ace_order INT NOT NULL,
                           sid INT NOT NULL,
                           mask INTEGER NOT NULL,
                           granting BOOLEAN NOT NULL,
                           audit_success BOOLEAN NOT NULL,
                           audit_failure BOOLEAN NOT NULL
);
