Alter table APP_USERS ADD COLUMN password_reset_required boolean not null;
insert into app_privileges (name , created_at, updated_at) values ('PRIV_ADMIN_CAP', current_timestamp,  current_timestamp);
insert into app_privileges (name , created_at, updated_at) values ('PRIV_APPROVERS', current_timestamp,  current_timestamp);

