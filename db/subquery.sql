CREATE TABLE customers (
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

CREATE TABLE orders (
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

insert into customers (first_name, last_name, age, country)
VALUES ('Иванов', 'Иван', 25, 'РФ');
insert into customers (first_name, last_name, age, country)
VALUES ('Петров', 'Петр', 30, 'Беларусь');
insert into customers (first_name, last_name, age, country)
VALUES ('Семенов', 'Семен', 35, 'Казахстан');

insert into orders (amount, customer_id)
VALUES (10, 1);
insert into orders (amount, customer_id)
VALUES (15, 2);

SELECT * FROM customers
WHERE age = (SELECT MIN(age) FROM customers);

SELECT * FROM customers
WHERE customers.id NOT IN (SELECT customer_id FROM orders);