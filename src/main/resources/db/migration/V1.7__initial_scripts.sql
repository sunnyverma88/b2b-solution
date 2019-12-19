create sequence if not exists task_item_seq start 1;
CREATE TABLE if not exists task_items (
    id bigint default nextval('role_priv_seq'),
    task_name character varying(255),
    task_type character varying(255),
    task_status character varying(255),
    parent_id bigint,
    order_id bigint,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT task_item_pkey PRIMARY KEY (id),
    CONSTRAINT  order_fk FOREIGN KEY (order_id)
        REFERENCES orders (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create TABLE if not exists tasks_users (
task_id bigint,
user_id bigint,
CONSTRAINT  task_fk FOREIGN KEY (task_id)
        REFERENCES task_items (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
CONSTRAINT  user_fk FOREIGN KEY (user_id)
        REFERENCES app_users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);



