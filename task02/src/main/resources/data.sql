insert into customer(first_name, last_name, company_name)
values ('Marsel', 'Sidikov', 'FoxCorp.');
insert into customer(first_name, last_name, company_name)
values ('Maxim', 'Pozdeev', 'WolfCorp.');
insert into customer(first_name, last_name, company_name)
values ('Viktor', 'Evlampiev', 'CatCorp.');

insert into shop(name, address)
values ('Ozon', 'Gafy Gaynullina 23');
insert into shop(name, address)
values ('5ochka', 'Karima Faizullina 35');
insert into shop(name, address)
values ('Magnit', 'Kauma Nasiyri 50');
insert into shop(name, address)
values ('Perekrestok', 'Baumana 83');

insert into "order"(shop_id, customer_id)
values (1, 2);
insert into "order"(shop_id, customer_id)
values (3, 2);
insert into "order"(shop_id, customer_id)
values (4, 2);
insert into "order"(shop_id, customer_id)
values (1, 1);
insert into "order"(shop_id, customer_id)
values (2, 3);
insert into "order"(shop_id, customer_id)
values (1, 3);

insert into product (price, name)
values (50, 'Хлеб сельский') ,
       (45, 'Хлеб белый' ) ,
       (100, 'Кола') ,
       (150, 'Напиток из черноголовки'),
       (200, 'Карась'),
       (600, 'Коробка конфет'),
       (800, 'Пицца') ,
       (30, 'Вода'),
       (85, 'Bud безалкогольный');

insert into order_product (count, order_id, product_id)
values (10, 2, 1),
       (5, 2, 3),
       (9, 2, 4),
       (1, 1, 2),
       (9, 3, 9),
       (1, 4, 5),
       (2, 5, 8),
       (3, 6, 6),
       (2, 6, 4);

insert into shop_product (count, shop_id, product_id)
values (100, 1, 1),
       (100, 1, 2),
       (100, 1, 3),
       (100, 1, 4),
       (100, 1, 5),
       (100, 1, 6),
       (100, 1, 7),
       (100, 1, 8),
       (100, 1, 9),
       (100, 2, 1),
       (100, 2, 2),
       (100, 2, 3),
       (100, 2, 4),
       (100, 2, 5),
       (100, 2, 6),
       (100, 2, 7),
       (100, 2, 8),
       (100, 2, 9),
       (100, 2, 7),
       (100, 3, 1),
       (100, 3, 2),
       (100, 3, 3),
       (100, 3, 4),
       (100, 3, 5),
       (100, 3, 6),
       (100, 3, 7),
       (100, 3, 8),
       (100, 3, 9),
       (100, 4, 1),
       (100, 4, 2),
       (100, 4, 3),
       (100, 4, 4),
       (100, 4, 5),
       (100, 4, 6),
       (100, 4, 7),
       (100, 4, 8),
       (100, 4, 9);