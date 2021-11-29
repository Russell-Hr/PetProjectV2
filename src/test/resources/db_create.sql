CREATE DATABASE shop;
use shop;

CREATE TABLE receipt
(
    id INT auto_increment unique,
    userId INT,
    managerId INT,
    status VARCHAR(20),
    total DOUBLE,
    createDate DATE,
    paymentDate DATE
)ENGINE=InnoDB;

CREATE TABLE receipt_has_parcel
(
    receiptId INT,
    parcelId INT,
    price DOUBLE
)ENGINE=InnoDB;

CREATE TABLE user
(
    id INT auto_increment unique,
    role VARCHAR(20),
    name VARCHAR(20),
    surname VARCHAR(20),
    login VARCHAR(20),
    password VARCHAR(200)
)ENGINE=InnoDB;

ALTER TABLE user
    ADD PRIMARY KEY (id);


CREATE TABLE parcel
(
    id INT auto_increment unique,
    fromPoint VARCHAR(20),
    toPoint VARCHAR(20),
    deliveryAddress VARCHAR(100),
    category VARCHAR(20),
    distance INT,
    length INT,
    width INT,
    height INT,
    weight DOUBLE,
    price DOUBLE,
    status VARCHAR(20),
    createDate DATE,
    paymentDate DATE,
    deliveryDate DATE,
    userId INT,
    FOREIGN KEY (userId) REFERENCES user(Id) ON DELETE CASCADE
)ENGINE=InnoDB;

ALTER TABLE receipt_has_parcel ADD FOREIGN KEY (parcelId) REFERENCES parcel(Id) ON DELETE CASCADE;
ALTER TABLE receipt_has_parcel ADD FOREIGN KEY (receiptId) REFERENCES receipt(Id) ON DELETE CASCADE;
ALTER TABLE parcel ADD FOREIGN KEY (userId) REFERENCES user(Id) ON DELETE CASCADE;




