
CREATE TABLE cart (
                      id UUID PRIMARY KEY  ,
                      customer_id UUID,
                      coupon_id UUID,
                      sub_total DECIMAL(10, 2),
                      total DECIMAL(10, 2),
                      creat_at TIMESTAMP
);

CREATE TABLE unit_cart (
                           id UUID PRIMARY KEY,
                           cart_id UUID,
                           item_id BIGINT,
                           quantity INT,
                           price_item DECIMAL(10, 2),
                           price DECIMAL(10, 2),
                           FOREIGN KEY (cart_id) REFERENCES cart(id)
);

INSERT INTO cart (id,  sub_total)
VALUES ('747e74ce-0ed8-46a5-9ff5-981814d52076',  100.00 );
