create schema IF NOT EXISTS kutuphane_schema;

CREATE TABLE kutuphane_schema.role (
	id int4 NOT NULL,
	name varchar(255) NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id)
);

CREATE TABLE kutuphane_schema.account (
	account_id uuid NOT NULL,
	mail varchar(255) NULL,
	name varchar(255) NULL,
	password varchar(255) NULL,
	surname varchar(255) NULL,
	user_name varchar(255) NULL,
	role_id int4 NULL,
	CONSTRAINT account_pkey PRIMARY KEY (account_id),
	CONSTRAINT uk_f6xpj7h12wr185bqhfi1hqlbr UNIQUE (user_name)
);
ALTER TABLE kutuphane_schema.account ADD CONSTRAINT fkd4vb66o896tay3yy52oqxr9w0 FOREIGN KEY (role_id) REFERENCES kutuphane_schema.role(id);

CREATE TABLE kutuphane_schema.author (
	id uuid NOT NULL,
	description varchar(255) NULL,
	name varchar(255) NULL,
	surname varchar(255) NULL,
	CONSTRAINT author_pkey PRIMARY KEY (id)
);

CREATE TABLE kutuphane_schema.book (
	id uuid NOT NULL,
	description varchar(255) NULL,
	isbn varchar(255) NULL,
	name varchar(255) NULL,
	series_name varchar(255) NULL,
	sub_name varchar(255) NULL,
	CONSTRAINT book_pkey PRIMARY KEY (id),
	CONSTRAINT uk_j5pf3sow8q95wkhr2h4r16hq0 UNIQUE (isbn)
);

CREATE TABLE kutuphane_schema.publisher (
	id uuid NOT NULL,
	info varchar(255) NULL,
	name varchar(255) NULL,
	CONSTRAINT publisher_pkey PRIMARY KEY (id)
);

CREATE TABLE kutuphane_schema.book_author (
	author_id uuid NOT NULL,
	book_id uuid NOT NULL,
	CONSTRAINT book_author_pkey PRIMARY KEY (author_id, book_id),
	CONSTRAINT uk_q37qkj7serxg0bh56m450uigs UNIQUE (book_id)
);

CREATE TABLE kutuphane_schema.book_publisher (
	publisher_id uuid NOT NULL,
	book_id uuid NOT NULL,
	CONSTRAINT book_publisher_pkey PRIMARY KEY (publisher_id, book_id),
	CONSTRAINT uk_9n9w6tds52nocyw21mbsphh43 UNIQUE (book_id)
);

ALTER TABLE kutuphane_schema.book_author ADD CONSTRAINT fkbjqhp85wjv8vpr0beygh6jsgo FOREIGN KEY (author_id) REFERENCES kutuphane_schema.author(id);
ALTER TABLE kutuphane_schema.book_author ADD CONSTRAINT fkhwgu59n9o80xv75plf9ggj7xn FOREIGN KEY (book_id) REFERENCES kutuphane_schema.book(id);

ALTER TABLE kutuphane_schema.book_publisher ADD CONSTRAINT fk8ywuvxfycghsfmxvu363jllpq FOREIGN KEY (book_id) REFERENCES kutuphane_schema.book(id);
ALTER TABLE kutuphane_schema.book_publisher ADD CONSTRAINT fknihk8b6sfx2mvtstq87wpjsfu FOREIGN KEY (publisher_id) REFERENCES kutuphane_schema.publisher(id);
