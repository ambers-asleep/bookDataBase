# This SQL Script creates the bookstore database

# Create and use the database
create database if not exists BookDatabase;
use BookDatabase;

CREATE TABLE authors (
    authorID INT PRIMARY KEY NOT NULL,
    author_name VARCHAR(100)
);

CREATE TABLE users (
    userID INT PRIMARY KEY NOT NULL,
    last_name VARCHAR(75),
    first_name VARCHAR(75),
    city VARCHAR(30),
    state VARCHAR(30),
    email VARCHAR(75)
);

CREATE TABLE publishers (
    publisherID INT PRIMARY KEY NOT NULL,
    publisher_name VARCHAR(50)
);

CREATE TABLE books (
    ISBN VARCHAR(50) PRIMARY KEY NOT NULL,
    title VARCHAR(300),
    authorID INT,
    publication_year VARCHAR(20),
    publisherID INT,
    imageURL VARCHAR(100),
    FOREIGN KEY (authorID)
        REFERENCES authors (authorID),
    FOREIGN KEY (publisherID)
        REFERENCES publishers (publisherID)
);

CREATE TABLE ratings (
    userID INT,
    ISBN VARCHAR(50),
    rating INT,
    FOREIGN KEY (userID)
        REFERENCES users (userID),
    FOREIGN KEY (ISBN)
        REFERENCES books (ISBN)
);

