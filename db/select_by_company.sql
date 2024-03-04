CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name) values (1, 'Сбер'), (2, 'ГПБ'), (3, 'Альфа'),
(4, 'Тинькоф'), (5, 'Авангард');

insert into person(id, name, company_id) values (1, 'Иванов', 1), (2, 'Петров', 2), 
(3, 'Сидоров', 3), (4, 'Семенов', 4), (5, 'Сергеев', 5), (6, 'Дмитриев', 1), (7, 'Павлов', 1),
(8, 'Михайлов', 4), (9, 'Игнатьев', 4);

SELECT p.name as "Имена людей", c.name as "Название компании"
FROM person p
JOIN company c ON p.company_id = c.id
WHERE c.id != 5;

SELECT c.name as "Название компании", COUNT(p.name) as "Количество человек"
FROM person p JOIN company c ON p.company_id = c.id 
GROUP BY c.name
HAVING COUNT(p.name) >= ALL(SELECT COUNT(p.name)
	   FROM person p JOIN company c ON p.company_id = c.id
	   GROUP BY c.name
						   );