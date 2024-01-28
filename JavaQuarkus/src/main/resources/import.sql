-- bellow fake data is only used in dev or test mode

INSERT INTO addresses(id,name,house,street,city,state,zip) VALUES (1, 'ShopRite of Hoboken', '900', 'Madison St', 'Hoboken', 'NJ', '07030');

INSERT INTO products(id,name,price,category,stock) VALUES (2,'Tiger Toy', 1200, 'toy', 3);
INSERT INTO products(id,name,price,category,stock) VALUES (3,'ZOROBOT', 6500, 'toy', 2);
INSERT INTO products(id,name,price,category,stock) VALUES (4,'Pokeman Cards Kit', 1430, 'card', 5);

INSERT INTO fruits(id,name,season) VALUES (nextval('fruits_SEQ'),'Mango','Spring');
INSERT INTO fruits(id,name,season) VALUES (nextval('fruits_SEQ'),'Strawberry','Spring');
INSERT INTO fruits(id,name,season) VALUES (nextval('fruits_SEQ'),'Orange','Winter');
INSERT INTO fruits(id,name,season) VALUES (nextval('fruits_SEQ'),'Lemon','Winter');
INSERT INTO fruits(id,name,season) VALUES (nextval('fruits_SEQ'),'Blueberry','Summer');
INSERT INTO fruits(id,name,season) VALUES (nextval('fruits_SEQ'),'Banana','Summer');
INSERT INTO fruits(id,name,season) VALUES (nextval('fruits_SEQ'),'Watermelon','Summer');
INSERT INTO fruits(id,name,season) VALUES (nextval('fruits_SEQ'),'Apple','Fall');
INSERT INTO fruits(id,name,season) VALUES (nextval('fruits_SEQ'),'Pear','Fall');

ALTER sequence addresses_SEQ RESTART WITH 2;
ALTER sequence products_SEQ RESTART WITH 5;
