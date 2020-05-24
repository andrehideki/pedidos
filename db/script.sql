CREATE TABLE IF NOT EXISTS produto (
	id INT IDENTITY PRIMARY KEY,
	descricao VARCHAR(100) NOT NULL,
	valor DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS hist_prod (
	id INT IDENTITY PRIMARY KEY,
	prod_id INT NOT NULL,
	data DATE NOT NULL,
	valor DOUBLE NOT NULL,
	FOREIGN KEY(prod_id) REFERENCES produto(id)
);

CREATE TABLE IF NOT EXISTS fornecedor (
	id INT IDENTITY PRIMARY KEY,
	empresa VARCHAR(255) NOT NULL,
	vendedor VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS pedido (
	id INT IDENTITY PRIMARY KEY,
	data DATE NOT NULL,
	forn_id INT NOT NULL,
	total DOUBLE NOT NULL,
	FOREIGN KEY(forn_id) REFERENCES fornecedor(id)
);

CREATE TABLE IF NOT EXISTS form_prod (
	forn_id INT NOT NULL,
	prod_id INT NOT NULL,
	PRIMARY KEY(forn_id, prod_id),
	FOREIGN KEY(forn_id) REFERENCES fornecedor(id),
	FOREIGN KEY(prod_id) REFERENCES produto(id)
);

CREATE TABLE IF NOT EXISTS ped_prod (
	ped_id INT NOT NULL,
	prod_id INT NOT NULL,
	qtde INT NOT NULL,
	PRIMARY KEY(ped_id, prod_id),
	FOREIGN KEY(ped_id) REFERENCES pedido(id),
	FOREIGN KEY(prod_id) REFERENCES produto(id)
);


