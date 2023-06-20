-- Create Database
CREATE DATABASE IF NOT EXISTS boards;

CREATE DATABASE IF NOT EXISTS users;

-- MariaDB ALTER TABLE Query
-- ALTER TABLE IF EXISTS users.user_address DROP CONSTRAINT address_fk_id;
-- ALTER TABLE IF EXISTS boards.board DROP CONSTRAINT board_fk_id;

-- MySQL ALTER TABLE Query
ALTER TABLE users.user_address DROP CONSTRAINT address_fk_id;
ALTER TABLE boards.board DROP CONSTRAINT board_fk_id;

DROP TABLE IF EXISTS users.user_address;
DROP TABLE IF EXISTS boards.board;
DROP TABLE IF EXISTS users.user;

-- Create user table on users database
CREATE TABLE IF NOT EXISTS users.user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(50) NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    user_password VARCHAR(50) NOT NULL,
    user_tel VARCHAR(50) NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NULL,
    PRIMARY KEY (id, user_id),
    UNIQUE KEY (user_id)
);

-- Create board table on boards database
CREATE TABLE IF NOT EXISTS boards.board (
    board_id BIGINT NOT NULL AUTO_INCREMENT,
    board_title VARCHAR(50) NOT NULL,
    -- board_author VARCHAR(30) NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    board_content VARCHAR(200) NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NULL,
    PRIMARY KEY (board_id, user_id),
    CONSTRAINT board_fk_id FOREIGN KEY (user_id) REFERENCES users.user(user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


-- Create user address tables on users database
CREATE TABLE IF NOT EXISTS users.user_address (
    user_id VARCHAR(50) NOT NULL,
    user_address VARCHAR(100) NOT NULL,
    address_zipcode VARCHAR(30) NOT NULL,
    PRIMARY KEY(user_id),
    CONSTRAINT address_fk_id FOREIGN KEY(user_id) REFERENCES users.user(user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);