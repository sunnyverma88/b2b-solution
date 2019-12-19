
ALTER TABLE orders ADD COLUMN group_id bigint,
ADD FOREIGN KEY (group_id)
REFERENCES groups (id) MATCH SIMPLE;


