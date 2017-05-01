# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table usuario (
  id                            bigint auto_increment not null,
  email                         varchar(255),
  senha                         varchar(255),
  constraint pk_usuario primary key (id)
);

create table categoria (
  id                            bigint auto_increment not null,
  descricao                     varchar(255),
  constraint pk_categoria primary key (id)
);

create table produtos (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  valor_compra                  double,
  preco_unitario                double,
  quantidade                    integer,
  fornecedor                    varchar(255),
  copa_id                       integer,
  categoria_id                  integer,
  constraint pk_produtos primary key (id)
);


# --- !Downs

drop table if exists usuario;

drop table if exists categoria;

drop table if exists produtos;

