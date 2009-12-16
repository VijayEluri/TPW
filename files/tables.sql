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

INSERT INTO usuario VALUES ('admin','21232f297a57a5a743894a0e4a801fc3','administrador',NULL,'ADMINISTRADOR');

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
	qtVagas integer,
	qtInscritos integer,
	nome varchar(255),
	data timestamp,
	descricao text,
	responsavel varchar(255)
);

create table Evento_Usuario(
	id integer references evento(id) on delete cascade,
	login varchar(50) references usuario(login) on delete cascade
);

create table minicurso(
	id serial not null primary key,
	qtVagas integer,
	qtInscritos integer,
	nome varchar(255),
	data timestamp,
	descricao text,
	responsavel varchar(255)
);

create table Minicurso_Usuario(
	id integer references minicurso(id) on delete cascade,
	login varchar(50) references usuario(login) on delete cascade
);

create language 'plpgsql';

CREATE OR REPLACE FUNCTION minicurso_evento_usuario_delete() RETURNS trigger AS '
BEGIN
  UPDATE minicurso SET qtInscritos = qtInscritos-1 WHERE EXISTS( select 1 from minicurso_usuario where minicurso_usuario.id = minicurso.id and minicurso_usuario.login = OLD.login);
  UPDATE evento SET qtInscritos = qtInscritos-1 WHERE EXISTS( select 1 from evento_usuario where evento_usuario.id = evento.id and evento_usuario.login = OLD.login);
  RETURN old;
END
' LANGUAGE plpgsql;

CREATE TRIGGER TG_minicurso_evento_usuario_delete BEFORE DELETE ON usuario FOR EACH ROW EXECUTE PROCEDURE minicurso_evento_usuario_delete();
