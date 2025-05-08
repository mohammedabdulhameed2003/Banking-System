CREATE DATABASE BankingDB;

USE BankingDB;

CREATE TABLE accounts (
    account_number INT PRIMARY KEY,
    password VARCHAR(255),
    balance DOUBLE
);
