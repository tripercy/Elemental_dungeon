DROP DATABASE IF EXISTS element_cg;

CREATE DATABASE element_cg;

USE element_cg;

CREATE TABLE cards(
    name VARCHAR(255) NOT NULL,
    cost INT NOT NULL,
    element VARCHAR(255) NOT NULL,
    effects TEXT NOT NULL,
    PRIMARY KEY (name)
);