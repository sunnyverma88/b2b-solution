create sequence if not exists category_seq start 101;

create table if not exists category (
id  bigint primary key default nextval('category_seq'),
name varchar(50) not null check (name <> ''),
description varchar(300) not null check (description <> ''),
visible boolean,
created_at timestamp,
updated_at timestamp
);

insert into category (name, description, visible, created_at, updated_at) values ('Office Supplies', 'Office Supplies' , 'Y', current_timestamp,  current_timestamp);
insert into category (name, description, visible, created_at, updated_at) values ('Emergency Supplies', 'Emergency Supplies' , 'Y', current_timestamp,  current_timestamp);