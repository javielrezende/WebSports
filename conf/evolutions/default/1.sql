# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

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
  copa_id                       integer,
  categoria_id                  bigint,
  constraint pk_produtos primary key (id)
);

create table usuario (
  id                            bigint auto_increment not null,
  email                         varchar(255),
  senha                         varchar(255),
  constraint pk_usuario primary key (id)
);

alter table produtos add constraint fk_produtos_categoria_id foreign key (categoria_id) references categoria (id) on delete restrict on update restrict;
create index ix_produtos_categoria_id on produtos (categoria_id);


# --- !Downs

alter table produtos drop foreign key fk_produtos_categoria_id;
drop index ix_produtos_categoria_id on produtos;

drop table if exists categoria;

drop table if exists produtos;

drop table if exists usuario;

