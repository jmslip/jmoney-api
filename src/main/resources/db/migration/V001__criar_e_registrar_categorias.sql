CREATE SEQUENCE sq_categoria
INCREMENT 1
MINVALUE 1
START 1;

CREATE TABLE categoria (
	id int8 PRIMARY KEY,
	nome varchar(50) NOT null
);

INSERT INTO categoria (id, nome) VALUES (nextval('sq_categoria'), 'Lazer');
INSERT INTO categoria (id, nome) VALUES (nextval('sq_categoria'), 'Alimentação');
INSERT INTO categoria (id, nome) VALUES (nextval('sq_categoria'), 'Supermercado');
INSERT INTO categoria (id, nome) VALUES (nextval('sq_categoria'), 'Farmácia');
INSERT INTO categoria (id, nome) VALUES (nextval('sq_categoria'), 'Outros');