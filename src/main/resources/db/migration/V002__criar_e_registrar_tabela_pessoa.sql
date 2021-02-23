CREATE SEQUENCE sq_pessoa
INCREMENT 1
MINVALUE 1
START 1;

CREATE TABLE pessoa (
	id int8 NOT NULL PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	logradouro VARCHAR(100),
	numero int4,
	complemento VARCHAR(100),
	bairro VARCHAR(100),
	cep CHAR(10),
	cidade VARCHAR(100),
	estado CHAR(2),
	ativo smallint DEFAULT 1
);

INSERT INTO pessoa (id, nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
	values(nextval('sq_pessoa'), 'Jorge Marcelo de Souza Junior', 'Rua Novo Mundo', 108, 'Casa A', 'Novo Glória', '30.880-320', 'Belo Horizonte', 'MG', 1);

INSERT INTO pessoa (id, nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
	values(nextval('sq_pessoa'), 'Rúbia Mara de Oliveira Souza', 'Rua Novo Mundo', 108, 'Casa A', 'Novo Glória', '30.880-320', 'Belo Horizonte', 'MG', 1);
	
INSERT INTO pessoa (id, nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
	values(nextval('sq_pessoa'), 'Miriam de Fátima Reis de Souza', 'Rua Novo Mundo', 98, null, 'Novo Glória', '30.880-320', 'Belo Horizonte', 'MG', 1);
	
INSERT INTO pessoa (id, nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
	values(nextval('sq_pessoa'), 'Jorge Marcelo de Souza', 'Rua Novo Mundo', 108, 'Casa B', 'Novo Glória', '30.880-320', 'Belo Horizonte', 'MG', 0);
	
INSERT INTO pessoa (id, nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) 
	values(nextval('sq_pessoa'), 'Hugo Oliveira', 'Rua Mafra', 258, null, 'Coqueiros', '30.800.200', 'Belo Horizonte', 'MG', 0);