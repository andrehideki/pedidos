CREATE TABLE IF NOT EXISTS produto (
	id INT IDENTITY PRIMARY KEY,
	descricao VARCHAR(100) NOT NULL,
	valor DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS hist_prod (
	id INT NOT NULL,
	data TIMESTAMP NOT NULL,
	valor DOUBLE NOT NULL,
    PRIMARY KEY(id, data),
	FOREIGN KEY(id) REFERENCES produto(id)
);

CREATE TABLE IF NOT EXISTS fornecedor (
	id INT IDENTITY PRIMARY KEY,
	empresa VARCHAR(255) NOT NULL,
	vendedor VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS pedido (
	id INT IDENTITY PRIMARY KEY,
	data TIMESTAMP NOT NULL,
	forn_id INT NOT NULL,
	total DOUBLE NOT NULL,
    observacao VARCHAR(255),
	FOREIGN KEY(forn_id) REFERENCES fornecedor(id)
);

CREATE TABLE IF NOT EXISTS forn_prod (
	forn_id INT NOT NULL,
	prod_id INT NOT NULL,
	FOREIGN KEY(forn_id) REFERENCES fornecedor(id),
	FOREIGN KEY(prod_id) REFERENCES produto(id)
);

ALTER TABLE forn_prod
ADD PRIMARY KEY (forn_id, prod_id);

CREATE TABLE IF NOT EXISTS ped_prod (
	ped_id INT NOT NULL,
	prod_id INT NOT NULL,
	qtde INT NOT NULL,
	PRIMARY KEY(ped_id, prod_id),
	FOREIGN KEY(ped_id) REFERENCES pedido(id),
	FOREIGN KEY(prod_id) REFERENCES produto(id)
);


