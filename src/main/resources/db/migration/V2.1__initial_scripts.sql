ALTER TABLE orders ADD COLUMN shipping_cost NUMERIC;
ALTER TABLE shopping_cart ADD COLUMN shipping_cost NUMERIC;


UPDATE shopping_cart
	SET shipping_cost=0
	WHERE shipping_cost = null;

UPDATE orders
	SET shipping_cost=0
	WHERE shipping_cost = null;