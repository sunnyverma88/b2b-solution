create sequence if not exists category_seq start 101;

create table if not exists categories (
    cid  integer primary key default nextval('category_seq'),
    name varchar(50) not null check (name <> ''),
    description varchar(300) not null check (description <> ''),
    is_visible boolean,
    created_at timestamp,
    updated_at timestamp
);