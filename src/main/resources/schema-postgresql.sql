CREATE TABLE IF NOT EXISTS produto (
     id serial PRIMARY KEY,
     nome varchar(50),
     descricao varchar(200),
     preco DOUBLE PRECISION
);