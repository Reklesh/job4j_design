create table ages (
    id serial primary key,
    interval varchar(200)
);

create table students (
    id   serial primary key,
    name varchar(200),
	age_id integer references ages (id)
);

create table courses (
    id serial primary key,
    name varchar(200)
);

create table students_courses (
    id serial primary key,
    student_id integer references students (id),
    cours_id integer references courses (id)
);

insert into ages (interval) values ('18-30 лет'), ('30-40 лет'), ('40-50 лет');

insert into students (name, age_id) values ('Иван', 1), ('Катя', 1), ('Семен', 2),
('Петр', 2), ('Андрей', 3), ('Михаил', 3);

insert into courses (name) values ('Java'), ('JS'), ('Python'), ('C++');

insert into students_courses (student_id, cours_id) values (1, 1), (1, 2), (2, 3), (2, 4), 
(3, 1), (3, 3), (4, 1), (4, 4), (5, 1), (5, 4), (6, 3), (6, 4);

create view show_students_aged_30_40_years_studying_Java
as
select c.name "Курс", count(s.name) "Количество изучающих"
from courses c
         join students_courses sc on c.id = sc.cours_id
         join students s on sc.student_id = s.id
         join ages a on s.age_id = a.id where a.interval = '30-40 лет' and c.name = 'Java'
group by c.name;

select * from show_students_aged_30_40_years_studying_Java;