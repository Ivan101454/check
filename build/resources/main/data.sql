CREATE TABLE IF NOT EXISTS discount_card (
    id_discount_card BIGSERIAL PRIMARY KEY,
    number_discount_card INT NOT NULL,
    discount_percent INT NOT NULL
);

CREATE TABLE IF NOT EXISTS product (
    id_product BIG SERIAL PRIMARY KEY,
    description_product VARCHAR NOT NULL,
    price_product DECIMAL NOT NULL,
    quantity_in_stock INT NOT NULL,
    wholesale_product BOOLEAN
);

INSERT INTO discount_card (
    number_discount_card,
    discount_percent
) VALUES
    (1111, 3),
    (2222, 3),
    (3333, 4),
    (4444, 5);

COPY product FROM '.\\src\\main\\resources' WITH (FORMAT csv);

--INSERT INTO product (
--    description_product,
--    price_product,
--    quantity_in_stock,
--    wholesale_product
--) VALUES
--    ()

