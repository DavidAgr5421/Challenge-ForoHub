create table topics(
    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(255) not null unique,
    fecha_creacion DATETIME not null,
    status tinyint,
    autor varchar(100) not null,
    curso varchar(100) not null,

    primary key(id)
);