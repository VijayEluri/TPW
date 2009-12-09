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

create table evento(
	id serial not null primary key,
	nome varchar(255),
	data varchar(15),
	descricao text,
	responsavel varchar(255)
);

create table minicurso(
	id serial not null primary key,
	nome varchar(255),
	data varchar(15),
	descricao text,
	responsavel varchar(255)
);
