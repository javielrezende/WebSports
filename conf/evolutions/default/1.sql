# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table categoria (
  id                            bigint auto_increment not null,
  descricao                     varchar(255),
  constraint pk_categoria primary key (id)
);

create table copa (
  id                            bigint auto_increment not null,
  constraint pk_copa primary key (id)
);

create table pagamento (
  id                            bigint auto_increment not null,
  valor                         double,
  data_pagamento                date,
  tipo_pagamento                integer,
  constraint pk_pagamento primary key (id)
);

create table produto_entrada (
  id                            bigint auto_increment not null,
  quantidade                    integer,
  valor                         double,
  data_entrada                  date,
  produtos_id                   bigint,
  constraint pk_produto_entrada primary key (id)
);

create table produto_saida (
  id                            bigint auto_increment not null,
  quantidade                    integer,
  valor                         double,
  data_saida                    date,
  produtos_id                   bigint,
  constraint pk_produto_saida primary key (id)
);

create table produtos (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  valor_compra                  double,
  preco_unitario                double,
  quantidade                    integer,
  copa_id                       bigint,
  categoria_id                  bigint,
  constraint pk_produtos primary key (id)
);

create table quadra (
  id                            bigint auto_increment not null,
  valor_locacao                 double,
  copa_id                       bigint,
  tipoquadra_id                 bigint,
  constraint pk_quadra primary key (id)
);

create table reserva (
  id                            bigint auto_increment not null,
  data_reserva                  date,
  data_entrada                  date,
  quadra_id                     bigint,
  qtd_horas                     time,
  constraint pk_reserva primary key (id)
);

create table tipo_quadra (
  id                            bigint auto_increment not null,
  descricao                     varchar(255),
  constraint pk_tipo_quadra primary key (id)
);

create table usuario (
  id                            bigint auto_increment not null,
  email                         varchar(255),
  senha                         varchar(255),
  constraint pk_usuario primary key (id)
);

alter table produto_entrada add constraint fk_produto_entrada_produtos_id foreign key (produtos_id) references produtos (id) on delete restrict on update restrict;
create index ix_produto_entrada_produtos_id on produto_entrada (produtos_id);

alter table produto_saida add constraint fk_produto_saida_produtos_id foreign key (produtos_id) references produtos (id) on delete restrict on update restrict;
create index ix_produto_saida_produtos_id on produto_saida (produtos_id);

alter table produtos add constraint fk_produtos_copa_id foreign key (copa_id) references copa (id) on delete restrict on update restrict;
create index ix_produtos_copa_id on produtos (copa_id);

alter table produtos add constraint fk_produtos_categoria_id foreign key (categoria_id) references categoria (id) on delete restrict on update restrict;
create index ix_produtos_categoria_id on produtos (categoria_id);

alter table quadra add constraint fk_quadra_copa_id foreign key (copa_id) references copa (id) on delete restrict on update restrict;
create index ix_quadra_copa_id on quadra (copa_id);

alter table quadra add constraint fk_quadra_tipoquadra_id foreign key (tipoquadra_id) references tipo_quadra (id) on delete restrict on update restrict;
create index ix_quadra_tipoquadra_id on quadra (tipoquadra_id);

alter table reserva add constraint fk_reserva_quadra_id foreign key (quadra_id) references quadra (id) on delete restrict on update restrict;
create index ix_reserva_quadra_id on reserva (quadra_id);


# --- !Downs

alter table produto_entrada drop foreign key fk_produto_entrada_produtos_id;
drop index ix_produto_entrada_produtos_id on produto_entrada;

alter table produto_saida drop foreign key fk_produto_saida_produtos_id;
drop index ix_produto_saida_produtos_id on produto_saida;

alter table produtos drop foreign key fk_produtos_copa_id;
drop index ix_produtos_copa_id on produtos;

alter table produtos drop foreign key fk_produtos_categoria_id;
drop index ix_produtos_categoria_id on produtos;

alter table quadra drop foreign key fk_quadra_copa_id;
drop index ix_quadra_copa_id on quadra;

alter table quadra drop foreign key fk_quadra_tipoquadra_id;
drop index ix_quadra_tipoquadra_id on quadra;

alter table reserva drop foreign key fk_reserva_quadra_id;
drop index ix_reserva_quadra_id on reserva;

drop table if exists categoria;

drop table if exists copa;

drop table if exists pagamento;

drop table if exists produto_entrada;

drop table if exists produto_saida;

drop table if exists produtos;

drop table if exists quadra;

drop table if exists reserva;

drop table if exists tipo_quadra;

drop table if exists usuario;

