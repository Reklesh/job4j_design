create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
	type_id int references type(id),
	expired_date timestamp,
	price float
);

insert into type(name) values ('Сыр'), ('Молоко'), ('Мясо');

insert into product(name, type_id, expired_date, price) 
values ('Фетакса', 1, '2023-12-23', 309), ('Пармезан', 1, '2023-12-24', 389), 
('Гауда', 1, '2023-12-25', 149), ('Чечил', 1, '2023-12-26', 141), 
('Кефир', 2, '2023-12-23', 110), ('Сметана', 2, '2023-12-24', 389),
('Йогурт', 2, '2023-12-25', 49), ('Мороженое', 2, '2023-12-26', 177),
('Масло', 2, '2023-12-27', 170), ('Колбаса', 3, '2023-12-25', 1100),
('Сосиски', 3, '2023-12-25', 400), ('Карбонат', 3, '2023-12-25', 2000),
('Хамон', 3, '2023-12-28', 458), ('Салями', 3, '2023-12-28', 580),
('Фарш', 3, '2023-12-27', 900), ('Сервелат', 3, '2023-12-25', 5000),
('Ветчина', 3, '2023-12-29', 800), ('Холодец', 3, '2023-12-29', 640),
('Окорок', 3, '2023-12-25', 1100);

select pr.id, pr.name, pr.type_id, pr.expired_date, pr.price
from product pr join type t on pr.type_id = t.id where t.name = 'Сыр';

select * from product where name like '%Мороженое%';

select * from product where expired_date <= current_date;

select * from product where price = (select max(price) from product);

select t.name as "Тип продукта", count(pr.name) as "Количество продуктов"
from product pr join type t on pr.type_id = t.id
group by t.name;

select pr.id, pr.name, pr.type_id, pr.expired_date, pr.price
from product pr join type t on pr.type_id = t.id where t.name = 'Сыр' or t.name = 'Молоко';

select t.name as "Тип продукта", count(pr.name) as "Количество продуктов"
from product pr join type t on pr.type_id = t.id
group by t.name
having count(pr.name) < 10;

select pr.id, pr.name, pr.type_id, pr.expired_date, pr.price, t.name
from product pr join type t on pr.type_id = t.id;