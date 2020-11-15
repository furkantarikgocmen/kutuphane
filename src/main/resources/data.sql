create schema IF NOT EXISTS kutuphane_schema;

INSERT INTO kutuphane_schema."role" (id,name) VALUES
	 (1,'ADMIN'),
	 (2,'MODERATOR'),
	 (1,'USER');

INSERT INTO kutuphane_schema.account (account_id,"password",user_name,role_id) VALUES
	 ('47852672-38f0-4cf6-adde-fdf0cfae5178','$2a$10$q2awP67azUvY0a9aJgMJaeQhmN4JrO.4jJrihHulwOWyL3p4H4Dlm','user',1);
