create table teste (
	codigo int not null,
	descricao varchar(100) not null,
	valor float,
	primary key (codigo)
);

create table sites_rss (
	link varchar(255),
	site varchar(255),
	primary key (link)
);
