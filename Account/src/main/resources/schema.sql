CREATE TABLE IF NOT EXISTS t_person
(
    id       UUID PRIMARY KEY,
    username VARCHAR NOT NULL,
    fio      VARCHAR,
    age      INTEGER,
    phone    BIGINT,
    email    VARCHAR NOT NULL,
    card     BIGINT
);

CREATE TABLE IF NOT EXISTS customer
(
    id      UUID PRIMARY KEY,
    cart_id UUID
);

CREATE TABLE IF NOT EXISTS admin_shop
(
    id UUID PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS t_seller
(
    id    UUID PRIMARY KEY,
    phone BIGINT  NOT NULL,
    email VARCHAR NOT NULL,
    card  BIGINT
);

CREATE TABLE IF NOT EXISTS company
(
    id           UUID PRIMARY KEY REFERENCES t_seller (id),
    company_name VARCHAR
);

CREATE TABLE IF NOT EXISTS sole_trader
(
    id        UUID PRIMARY KEY REFERENCES t_seller (id),
    sole_name VARCHAR
);

CREATE TABLE IF NOT EXISTS coupon
(
    id          UUID PRIMARY KEY,
    coupon_name VARCHAR,
    discount    DOUBLE PRECISION,
    valid       BOOLEAN,
    creat_at    TIMESTAMP
);

CREATE TABLE IF NOT EXISTS t_order
(
    id           UUID PRIMARY KEY,
    coupon_id    UUID,
    sub_total    NUMERIC,
    total        NUMERIC,
    order_status VARCHAR,
    pay          VARCHAR,
    creat_at     TIMESTAMP,
    customer_id  UUID,
    seller_id    UUID,
    FOREIGN KEY (coupon_id) REFERENCES Coupon (id),
    FOREIGN KEY (customer_id) REFERENCES Customer (id),
    FOREIGN KEY (seller_id) REFERENCES t_seller (id)
);

CREATE TABLE IF NOT EXISTS unit_order
(
    id         UUID PRIMARY KEY,
    item_id    BIGINT,
    item_title VARCHAR,
    quantity   INTEGER,
    price_item NUMERIC,
    price_unit NUMERIC,
    order_id   UUID,
    FOREIGN KEY (order_id) REFERENCES t_order (id)
);


-- INSERT INTO t_person (id, username, fio, age, phone, email, card)
-- VALUES ('da2ab780-6686-4f60-87e1-95b8e32aeb3a', 'tom', 'ФИО', 20, 911, 'tom@mail.ru', 1235321);
--
-- INSERT INTO customer (id, cart_id)
-- VALUES ('da2ab780-6686-4f60-87e1-95b8e32aeb3a', '747e74ce-0ed8-46a5-9ff5-981814d52076');