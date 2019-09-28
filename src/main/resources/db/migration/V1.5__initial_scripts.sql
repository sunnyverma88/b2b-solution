create sequence if not exists order_seq start 1;
CREATE TABLE if not exists orders (
    id bigint default nextval('order_seq'),
    order_date timestamp,
    order_status character varying(255),
    shipping_address_id bigint,
    user_id bigint,
    order_total NUMERIC,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT order_pkey PRIMARY KEY (id),
    CONSTRAINT address_fk FOREIGN KEY (shipping_address_id)
        REFERENCES address (id) MATCH SIMPLE,
        CONSTRAINT user_fk FOREIGN KEY (user_id)
        REFERENCES app_users (id) MATCH SIMPLE
);


ALTER TABLE cart_item ADD COLUMN order_id bigint,
ADD FOREIGN KEY (order_id)
REFERENCES orders (id) MATCH SIMPLE;

