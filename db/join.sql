create table departments (
    id serial primary key,
    name varchar(255)
);

create table employees (
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('Бухгалтерия'), ('Охрана труда'), ('Отдел кадров');

insert into employees(name, department_id) values ('Иванова', 1), ('Петрова', 1),
('Сидорова', 1), ('Андреева', 2), ('Павлова', 2), ('Михайлов', 2);

select * from employees e
left join departments d on e.department_id = d.id;

select * from departments d
right join employees e on e.department_id = d.id;

select * from employees e
full join departments d on e.department_id = d.id;

select * from employees e
cross join departments d;

select d.id, d.name from departments d
left join employees e on e.department_id = d.id
where e.id is null;

select * from employees e
left join departments d on e.department_id = d.id;

select e.id, e.name, e.department_id, d.id, d.name from departments d
right join employees e on e.department_id = d.id;

create table teens (
    name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values ('Иван', 'муж'), ('Семен', 'муж'),
('Петр', 'муж'), ('Маша', 'жен'), ('Катя', 'жен');

select t1.name "М", t2.name "Ж", concat(t1.name, ' + ', t2.name) as "М + Ж" 
from teens t1 cross join teens t2 where t1.gender = 'муж' and t2.gender = 'жен';