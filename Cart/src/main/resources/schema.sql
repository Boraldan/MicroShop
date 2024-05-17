
CREATE TABLE cart (
                      id UUID PRIMARY KEY  ,
                      customer_id UUID,
                      coupon_name varchar,
--                       sub_total DECIMAL(10, 2),
--                       total DECIMAL(10, 2),
                      creat_at TIMESTAMP
);

CREATE TABLE cart_unit (
                           id UUID PRIMARY KEY,
                           cart_id UUID,
                           item_id UUID,
                           quantity INT,
--                            price_item DECIMAL(10, 2),
--                            price DECIMAL(10, 2),
                           FOREIGN KEY (cart_id) REFERENCES cart(id)
);

