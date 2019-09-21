ALTER TABLE ADDRESS RENAME COLUMN address_type To type;
ALTER TABLE GROUPS RENAME COLUMN proft_percentage To profit_percentage;
ALTER TABLE GROUPS RENAME COLUMN group_type To type;
ALTER TABLE GROUPS ADD COLUMN address_id bigint,
ADD FOREIGN KEY (address_id)
REFERENCES address (id) MATCH SIMPLE;
