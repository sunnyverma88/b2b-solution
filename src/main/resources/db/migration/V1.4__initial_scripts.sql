Alter table APP_USERS ADD COLUMN password_reset_required boolean not null;
insert into app_privileges (name , created_at, updated_at) values ('PRIV_ADD_PRODUCT', current_timestamp,  current_timestamp);
