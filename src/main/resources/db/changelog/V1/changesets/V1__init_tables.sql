-- liquibase formatted sql

-- changeset yluttsev:create_users_table
CREATE TABLE IF NOT EXISTS users
(
    id          bigint PRIMARY KEY,
    name        varchar(255)   NOT NULL,
    email       varchar(254)   NOT NULL UNIQUE,
    category    bigint         NOT NULL,
    total_spent numeric(11, 2) NOT NULL DEFAULT 0.0
);

-- changeset yluttsev:create_purchase_table
CREATE TABLE IF NOT EXISTS purchase
(
    id            bigint PRIMARY KEY,
    amount        numeric(11, 2)               NOT NULL DEFAULT 0.0,
    user_id       bigint REFERENCES users (id) NOT NULL,
    purchase_date timestamp                    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- changeset yluttsev:create_product_table
CREATE TABLE IF NOT EXISTS product
(
    id       bigint PRIMARY KEY,
    name     varchar(255)   NOT NULL,
    category bigint         NOT NULL,
    price    numeric(11, 2) NOT NULL DEFAULT 0.0
);

-- changeset yluttsev:create_purchase_items_table
CREATE TABLE IF NOT EXISTS purchase_items
(
    product_id  bigint REFERENCES product (id) ON DELETE CASCADE  NOT NULL,
    purchase_id bigint REFERENCES purchase (id) ON DELETE CASCADE NOT NULL
);