create table car_bodies (
    id serial primary key,
    name varchar(255)
);

create table car_engines (
    id serial primary key,
    name varchar(255)
);

create table car_transmissions (
    id serial primary key,
    name varchar(255)
);

create table cars (
    id serial primary key,
    name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Кроссовер'), ('Купе'), ('Лифтбек');
insert into car_engines(name) values ('Бензиновый'), ('Дизельный'), ('Газовый');
insert into car_transmissions(name) values ('МКПП'), ('АКПП'), ('Робот');

insert into cars(name, body_id, engine_id, transmission_id) values ('Лада', 2, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('УАЗ', 1, 2, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('Аурус', 1, 1, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('Москвич', 2, 1, 2);
insert into cars(name, body_id) values ('Эволют', 1);

select c.id, c.name, cb.name, ce.name, ct.name
from cars c 
left join car_bodies cb  
on c.body_id = cb.id
left join car_engines ce 
on c.engine_id = ce.id
left join car_transmissions ct 
on c.transmission_id = ct.id;

select cb.id, cb.name from car_bodies cb
left join cars c on c.body_id = cb.id
where c.id is null;

select ce.id, ce.name from car_engines ce
left join cars c on c.engine_id = ce.id
where c.id is null;

select ct.id, ct.name from car_transmissions ct
left join cars c on c.transmission_id = ct.id
where c.id is null;