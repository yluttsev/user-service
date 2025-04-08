-- liquibase formatted sql

-- changeset yluttsev:create_users_table
CREATE TABLE IF NOT EXISTS users
(
    id          bigint PRIMARY KEY AUTO_INCREMENT,
    name        varchar(255) NOT NULL,
    email       varchar(254) NOT NULL UNIQUE,
    category    bigint       NOT NULL,
    total_spent numeric(11, 2) DEFAULT 0.0
);

-- changeset yluttsev:create_purchase_table
CREATE TABLE IF NOT EXISTS purchase
(
    id            bigint PRIMARY KEY AUTO_INCREMENT,
    amount        numeric(11, 2) NOT NULL DEFAULT 0.0,
    user_id       bigint         NOT NULL,
    purchase_date timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- changeset yluttsev:create_product_table
CREATE TABLE IF NOT EXISTS product
(
    id       bigint PRIMARY KEY AUTO_INCREMENT,
    name     varchar(255) NOT NULL,
    category bigint       NOT NULL,
    price    numeric(11, 2) DEFAULT 0.0
);

-- changeset yluttsev:create_purchase_items_table
CREATE TABLE IF NOT EXISTS purchase_items
(
    product_id  bigint NOT NULL,
    purchase_id bigint NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (id) ON DELETE CASCADE,
    FOREIGN KEY (purchase_id) REFERENCES purchase (id) ON DELETE CASCADE
);