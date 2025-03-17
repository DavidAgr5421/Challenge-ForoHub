create table courses(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    categoria varchar(100) not null,

    primary key(id)
);

alter table topics modify curso bigint;
alter table topics add constraint fk_curso
foreign key (curso) references courses(id)
ON DELETE CASCADE ON UPDATE CASCADE;