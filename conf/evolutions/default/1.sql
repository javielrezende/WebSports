# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table categoria (
  id                            bigint auto_increment not null,
  descricao                     varchar(255),
  constraint pk_categoria primary key (id)
);

create table produto_entrada (
  id                            bigint auto_increment not null,
  quantidade                    integer,
  valor                         double,
  data_entrada                  date,
  produtos_id                   bigint,
  constraint pk_produto_entrada primary key (id)
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

alter table produto_entrada add constraint fk_produto_entrada_produtos_id foreign key (produtos_id) references produtos (id) on delete restrict on update restrict;
create index ix_produto_entrada_produtos_id on produto_entrada (produtos_id);

alter table produtos add constraint fk_produtos_categoria_id foreign key (categoria_id) references categoria (id) on delete restrict on update restrict;
create index ix_produtos_categoria_id on produtos (categoria_id);


# --- !Downs

alter table produto_entrada drop foreign key fk_produto_entrada_produtos_id;
drop index ix_produto_entrada_produtos_id on produto_entrada;

alter table produtos drop foreign key fk_produtos_categoria_id;
drop index ix_produtos_categoria_id on produtos;

drop table if exists categoria;

drop table if exists produto_entrada;

drop table if exists produtos;

drop table if exists usuario;

