CREATE DATABASE olympics;

USE olympics;

CREATE TABLE medals (
    id INT AUTO_INCREMENT PRIMARY KEY,
    country VARCHAR(100) NOT NULL,
    gold INT NOT NULL,
    silver INT NOT NULL,
    bronze INT NOT NULL
);
