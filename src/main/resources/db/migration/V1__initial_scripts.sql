create sequence if not exists category_seq start 1;

create table if not exists category (
id  bigint primary key default nextval('category_seq'),
name varchar(50) not null check (name <> ''),
description text not null check (description <> ''),
visible boolean,
created_at timestamp,
updated_at timestamp
);


create sequence if not exists app_role_seq start 1;

create table if not exists app_roles (
id bigint default nextval('app_role_seq'),
name character varying(255),
created_at timestamp,
updated_at timestamp,
CONSTRAINT app_role_pkey PRIMARY KEY (id)
);

-- Table: public.app_user


create sequence if not exists app_user_seq start 1;

CREATE TABLE if not exists app_users (
    id bigint default nextval('app_user_seq'),
    email character varying(255),
    enabled boolean NOT NULL,
    first_name character varying(255),
    last_name character varying(255) ,
    password character varying(255) ,
    phone character varying(255) ,
    username character varying(255) ,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT app_user_pkey PRIMARY KEY (id)
);


-- Table: public.user_role


create sequence if not exists user_role_seq start 1;
CREATE TABLE if not exists users_roles (
    user_role_id bigint default nextval('user_role_seq'),
    role_id bigint,
    user_id bigint,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT user_role_pkey PRIMARY KEY (user_role_id),
    CONSTRAINT fkg7fr1r7o0fkk41nfhnjdyqn7b FOREIGN KEY (user_id)
        REFERENCES app_users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkp6m37g6n6c288s096400uw8fw FOREIGN KEY (role_id)
        REFERENCES app_roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create sequence if not exists app_priv_seq start 1;

create table if not exists app_privileges (
id bigint default nextval('app_priv_seq'),
name character varying(255),
created_at timestamp,
updated_at timestamp,
CONSTRAINT app_priv_pkey PRIMARY KEY (id)
);


insert into app_privileges (name , created_at, updated_at) values ('PRIV_VIEW_PRODUCT', current_timestamp,  current_timestamp);


create sequence if not exists role_priv_seq start 1;
CREATE TABLE if not exists roles_privileges (
    role_priv_id bigint default nextval('role_priv_seq'),
    role_id bigint,
    priv_id bigint,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT role_priv_pkey PRIMARY KEY (role_priv_id),
    CONSTRAINT  role_fk FOREIGN KEY (role_id)
        REFERENCES app_roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT priv_fk FOREIGN KEY (priv_id)
        REFERENCES app_privileges (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create sequence if not exists product_seq start 1;
CREATE TABLE if not exists products (
    id bigint default nextval('product_seq'),
    name character varying(255),
    visible boolean NOT NULL,
    brand character varying(255),
    description text,
    specifications text ,
    actual_price NUMERIC,
    mrp_price NUMERIC ,
    hsn_code int ,
    shipping_days int,
    category_id bigint,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT product_pkey PRIMARY KEY (id),
    CONSTRAINT category_fk FOREIGN KEY (category_id)
        REFERENCES category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create sequence if not exists image_seq start 1;
CREATE TABLE if not exists Image (
    id bigint default nextval('image_seq'),
    img_url character varying(255),
    product_id bigint,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT image_pkey PRIMARY KEY (id),
    CONSTRAINT product_fk FOREIGN KEY (product_id)
        REFERENCES products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create sequence if not exists shopping_cart_seq start 1;
CREATE TABLE if not exists shopping_cart (
    id bigint default nextval('shopping_cart_seq'),
    total_items int,
    gst NUMERIC,
    cart_total NUMERIC,
    grand_total NUMERIC,
    user_id bigint,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT shopping_cart_pkey PRIMARY KEY (id),
    CONSTRAINT user_fk FOREIGN KEY (user_id)
        REFERENCES app_users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

create sequence if not exists cart_item_seq start 1;
CREATE TABLE if not exists cart_item (
    id bigint default nextval('cart_item_seq'),
    qty int,
    sub_total NUMERIC,
    product_id bigint,
    shopping_cart_id bigint,
    delivery_date timestamp,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT cart_item_pkey PRIMARY KEY (id),
    CONSTRAINT product_fk FOREIGN KEY (product_id)
        REFERENCES products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
        CONSTRAINT shopping_cart_fk FOREIGN KEY (shopping_cart_id)
        REFERENCES shopping_cart (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

);

create sequence if not exists hsn_seq start 1;
CREATE TABLE if not exists hsncode_gst_mapping (
    id bigint default nextval('hsn_seq'),
    hsn_code int,
    gst_percentage int,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT hsn_pkey PRIMARY KEY (id)
);

insert into hsncode_gst_mapping (hsn_code , gst_percentage , created_at, updated_at) values ( 12345 , 10 , current_timestamp,  current_timestamp);
insert into hsncode_gst_mapping (hsn_code , gst_percentage , created_at, updated_at) values ( 56789 , 5 , current_timestamp,  current_timestamp);





