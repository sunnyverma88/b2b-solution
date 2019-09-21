create sequence if not exists addr_seq start 1;

CREATE TABLE if not exists address (
    id bigint default nextval('addr_seq'),
    name character varying(255),
    address_type character varying(255) NOT NULL,
    addressLine1 character varying(255),
    addressLine2 character varying(255) ,
    landmark character varying(255) ,
    zipcode int,
    city character varying(255) ,
    state character varying(255) ,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT address_pkey PRIMARY KEY (id)
);
create sequence if not exists group_seq start 1;

CREATE TABLE if not exists groups (
    id bigint default nextval('group_seq'),
    name character varying(255),
    website character varying(255),
    group_type character varying(255),
    proft_percentage NUMERIC ,
    created_at timestamp,
    updated_at timestamp,
    CONSTRAINT group_pkey PRIMARY KEY (id)
);

ALTER TABLE APP_USERS ADD COLUMN group_id bigint,
ADD FOREIGN KEY (group_id)
        REFERENCES groups (id) MATCH SIMPLE;






