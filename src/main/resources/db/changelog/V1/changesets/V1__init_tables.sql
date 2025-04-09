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

-- changeset yluttsev:create_purchase_item_table
CREATE TABLE IF NOT EXISTS purchase_item
(
    id         bigint PRIMARY KEY AUTO_INCREMENT,
    product_id bigint NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS purchase_items
(
    purchase_id      bigint NOT NULL,
    purchase_item_id bigint NOT NULL,
    FOREIGN KEY (purchase_id) REFERENCES purchase (id) ON DELETE CASCADE,
    FOREIGN KEY (purchase_item_id) REFERENCES purchase_item (id) ON DELETE CASCADE
);