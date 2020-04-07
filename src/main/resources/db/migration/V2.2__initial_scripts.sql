create sequence if not exists order_comment_seq start 1;
CREATE TABLE if not exists order_comment (
    id bigint default nextval('order_comment_seq'),
    description character varying(255),
    order_id bigint,
    user_id bigint,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT order_comment_pkey PRIMARY KEY (id),
    CONSTRAINT  order_fk FOREIGN KEY (order_id)
        REFERENCES orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
     CONSTRAINT  user_fk FOREIGN KEY (user_id)
        REFERENCES app_users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
