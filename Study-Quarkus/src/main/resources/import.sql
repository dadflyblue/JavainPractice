-- bellow fake data is only used in dev or test mode

INSERT INTO Product(id,name,price,category) VALUES (nextval('hibernate_sequence'),'Tiger Toy', 1200, 'toy');
INSERT INTO Product(id,name,price,category) VALUES (nextval('hibernate_sequence'),'ZOROBOT', 6500, 'toy');
INSERT INTO Product(id,name,price,category) VALUES (nextval('hibernate_sequence'),'Pokeman Cards Kit', 1430, 'card');

INSERT INTO Fruit(id,name,season) VALUES (nextval('hibernate_sequence'),'Mango','Spring');
INSERT INTO Fruit(id,name,season) VALUES (nextval('hibernate_sequence'),'Strawberry','Spring');
INSERT INTO Fruit(id,name,season) VALUES (nextval('hibernate_sequence'),'Orange','Winter');
INSERT INTO Fruit(id,name,season) VALUES (nextval('hibernate_sequence'),'Lemon','Winter');
INSERT INTO Fruit(id,name,season) VALUES (nextval('hibernate_sequence'),'Blueberry','Summer');
INSERT INTO Fruit(id,name,season) VALUES (nextval('hibernate_sequence'),'Banana','Summer');
INSERT INTO Fruit(id,name,season) VALUES (nextval('hibernate_sequence'),'Watermelon','Summer');
INSERT INTO Fruit(id,name,season) VALUES (nextval('hibernate_sequence'),'Apple','Fall');
INSERT INTO Fruit(id,name,season) VALUES (nextval('hibernate_sequence'),'Pear','Fall');