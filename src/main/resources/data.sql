DROP TABLE IF EXISTS Tables;

CREATE TABLE Tables (
	id INT AUTO_INCREMENT PRIMARY KEY,
	status VARCHAR(20) NOT NULL
);

INSERT INTO Tables (status) VALUES
	('available'),
	('available'),
	('available');

DROP TABLE IF EXISTS Dish;

CREATE TABLE Dish (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	cost INT NOT NULL
);

INSERT INTO Dish (name, cost) VALUES
	('Chicken', 3000),
	('Meat', 6000),
	('Mushed Potatos', 2000),
	('Frensh Fries', 2000),
	('Salad', 2000);

DROP TABLE IF EXISTS Orders;

CREATE TABLE Orders (
	id INT AUTO_INCREMENT PRIMARY KEY,
	table_id INT NOT NULL
);

INSERT INTO Orders (table_id) VALUES (1), (1), (1), (1), (1);

DROP TABLE IF EXISTS Orders_Dish;

CREATE TABLE Orders_Dish (
	order_id INT NOT NULL,
	dish_id INT NOT NULL
);

INSERT INTO Orders_Dish (order_id, dish_id) VALUES
	(1,1),
	(1,5),
	(2,2),
	(3,1),
	(3,4),
	(4,1),
	(5,2),
	(5,4);
	
DROP TABLE IF EXISTS Bill;

CREATE TABLE Bill (
	id INT AUTO_INCREMENT PRIMARY KEY,
	bill_date DATE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	table_id INT NOT NULL,
	total_cost INT NOT NULL,
	taxes INT NOT NULL,
	tipping INT NOT NULL
);

DROP TABLE IF EXISTS Bill_Item;

CREATE TABLE Bill_Item (
	id INT AUTO_INCREMENT PRIMARY KEY,
	bill_id INT NOT NULL,
	description VARCHAR(100) NOT NULL,
	cost INT NOT NULL
); 