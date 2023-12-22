create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) 
values ('Очиститель воздуха', 8589), ('Увлажнитель воздуха', 2290), ('Умная колонка', 17990),
('Аэрогриль', 10990), ('Генератор белого шума', 2990), ('Док‑станция', 4490);

insert into people(name) values ('Иванов'), ('Петров'), ('Сидоров');

insert into devices_people(device_id, people_id) values (1, 1), (2, 1);
insert into devices_people(device_id, people_id) values (3, 2), (4, 2);
insert into devices_people(device_id, people_id) values (5, 3), (6, 3); 

select avg(price) from devices;

select p.name, avg(d.price) 
from devices_people as dp 
join people p 
on dp.people_id = p.id
join devices d 
on dp.device_id = d.id 
group by p.name;

select p.name, avg(d.price) 
from devices_people as dp 
join people p 
on dp.people_id = p.id
join devices d 
on dp.device_id = d.id 
group by p.name
having avg(d.price) > 5000;