create table sites_rss (
	link varchar(255),
	site varchar(255),
	primary key (link)
);

create table usuario (
	login varchar(50),
	password varchar(50),
	nome varchar(100),
	email varchar(50),
	tipo varchar(50),
	primary key(login)
);

INSERT INTO usuario VALUES ('admin','admin','administrador',NULL,'ADMINISTRADOR');

create table post (
	id int,
	titulo varchar(100),
	data timestamp,
	texto text,
	login varchar(50),
	primary key (id)
);

create table evento(
	id serial not null primary key,
	nome varchar(255),
	data timestamp,
	descricao text,
	responsavel varchar(255)
);

create table Evento_Usuario(
	id integer,
	login varchar(50)
);

create table minicurso(
	id serial not null primary key,
	nome varchar(255),
	data timestamp,
	descricao text,
	responsavel varchar(255)
);
