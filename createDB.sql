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

CREATE TABLE characters(
    name VARCHAR(255) NOT NULL,
    element VARCHAR(255) NOT NULL,
    health INT NOT NULL,
    mana INT NOT NULL,
    PRIMARY KEY (name)
);

CREATE TABLE player_cards(
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (name),
    FOREIGN KEY (name) REFERENCES cards(name)
);

CREATE TABLE player_characters(
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (name),
    FOREIGN KEY (name) REFERENCES characters(name)
);