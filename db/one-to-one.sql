create table wife(
	id serial primary key,
	name varchar(255),
	surname varchar(255)
);

create table spouse(
	id serial primary key,
	name varchar(255),
	surname varchar(255)
);

create table wife_spouse(
	id serial primary key,
	wife_id int references wife(id) unique,
	spouse_id int references spouse(id) unique
);

insert into wife(name, surname) values ('Ivanka', 'Ivanova');

insert into spouse(name, surname) values ('Ivan', 'Ivanov');

insert into wife_spouse(wife_id, spouse_id) values (1, 1);

select * from wife_spouse;