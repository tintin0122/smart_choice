CREATE TABLE IF NOT EXISTS product
(
    id           SERIAL PRIMARY KEY NOT NULL,
    product_name VARCHAR(200)       NOT NULL,
    shop         VARCHAR(50)        NOT NULL,
    information  VARCHAR(255),
    url          VARCHAR(255)       NOT NULL,
    price        numeric(19, 2),
    title        VARCHAR(255),
    discount     numeric(3),
    created_date TIMESTAMP,
    updated_date TIMESTAMP
);

CREATE SEQUENCE IF NOT EXISTS product_id_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    OWNED BY product.id;
