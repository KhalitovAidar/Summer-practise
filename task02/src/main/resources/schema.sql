create table shop (
      id serial primary key ,
      name varchar(20) not null ,
      address varchar(200) not null
);

create table customer (
      id serial primary key ,
      first_name varchar(20) not null ,
      last_name varchar(20) not null ,
      company_name varchar(20)
);

create table "order" (
     id serial primary key ,
     shop_id int,
     customer_id int,
     foreign key (shop_id) references shop(id),
     foreign key (customer_id) references customer(id)
);

create table product (
     id serial primary key ,
     price int ,
     name varchar(100) unique
);

create table order_product (
       id serial primary key ,
       count int check ( count > 0 ) ,
       order_id int ,
       product_id int ,
       foreign key (order_id) references "order"(id),
       foreign key (product_id) references  product(id)
);

create table shop_product (
      id serial primary key ,
      count int check ( count >= 0 ),
      shop_id int ,
      product_id int ,
      foreign key (product_id) references product(id),
      foreign key (shop_id) references shop(id)
);