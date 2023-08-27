-- bellow fake data is only used in dev or test mode

INSERT INTO addresses(id,name,house,street,city,state,zip) VALUES (nextval('hibernate_sequence'), 'ShopRite of Hoboken', '900', 'Madison St', 'Hoboken', 'NJ', '07030');

INSERT INTO products(id,name,price,category,stock) VALUES (nextval('hibernate_sequence'),'Tiger Toy', 1200, 'toy', 3);
INSERT INTO products(id,name,price,category,stock) VALUES (nextval('hibernate_sequence'),'ZOROBOT', 6500, 'toy', 2);
INSERT INTO products(id,name,price,category,stock) VALUES (nextval('hibernate_sequence'),'Pokeman Cards Kit', 1430, 'card', 5);

INSERT INTO fruits(id,name,season) VALUES (nextval('hibernate_sequence'),'Mango','Spring');
INSERT INTO fruits(id,name,season) VALUES (nextval('hibernate_sequence'),'Strawberry','Spring');
INSERT INTO fruits(id,name,season) VALUES (nextval('hibernate_sequence'),'Orange','Winter');
INSERT INTO fruits(id,name,season) VALUES (nextval('hibernate_sequence'),'Lemon','Winter');
INSERT INTO fruits(id,name,season) VALUES (nextval('hibernate_sequence'),'Blueberry','Summer');
INSERT INTO fruits(id,name,season) VALUES (nextval('hibernate_sequence'),'Banana','Summer');
INSERT INTO fruits(id,name,season) VALUES (nextval('hibernate_sequence'),'Watermelon','Summer');
INSERT INTO fruits(id,name,season) VALUES (nextval('hibernate_sequence'),'Apple','Fall');
INSERT INTO fruits(id,name,season) VALUES (nextval('hibernate_sequence'),'Pear','Fall');