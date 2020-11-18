create schema IF NOT EXISTS kutuphane_schema;

INSERT INTO kutuphane_schema."role" (id,name) VALUES
	 (1,'ADMIN'),
	 (2,'MODERATOR'),
	 (3,'USER');

INSERT INTO kutuphane_schema.account (account_id,"password",user_name,name,surname,mail,role_id) VALUES
	 ('47852672-38f0-4cf6-adde-fdf0cfae5178','$2a$10$q2awP67azUvY0a9aJgMJaeQhmN4JrO.4jJrihHulwOWyL3p4H4Dlm','admin','Furkan Tarık','Göçmen','f.tarikgocmen@gmail.com',1);


INSERT INTO kutuphane_schema.book (id,ısbn,name) VALUES
	 ('08273528-d2cc-427e-ba88-f27c3a594cc5','9789757064916','Aldatma Sanatı');

INSERT INTO kutuphane_schema.author (id,name,description,surname) VALUES
	 ('3a685a07-9993-4f1b-923e-842d1e86a116','Kevin David','Kevin David Mitnick, internet dünyasının ilk ve en meşhur hackerlarından olan Amerikalı bilgisayar bilimci. 15 Şubat 1995''te FBI tarafından düzenlenen bir operasyonda yakalanmıştı.','Mitnick');

INSERT INTO kutuphane_schema.publisher (id,name) VALUES
	 ('497d1977-5e96-4640-bac8-7deb068d6ab5','ODTÜ Yayıncılık');

INSERT INTO kutuphane_schema.book_author (author_id,book_id) VALUES
	 ('3a685a07-9993-4f1b-923e-842d1e86a116','08273528-d2cc-427e-ba88-f27c3a594cc5');

INSERT INTO kutuphane_schema.book_publisher (publisher_id,book_id) VALUES
	 ('497d1977-5e96-4640-bac8-7deb068d6ab5','08273528-d2cc-427e-ba88-f27c3a594cc5');
