# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table calendario (
  id                            integer auto_increment not null,
  title                         varchar(255),
  start                         datetime(6),
  end                           datetime(6),
  color                         varchar(255),
  constraint pk_calendario primary key (id)
);

create table cargo (
  id                            integer auto_increment not null,
  nome                          varchar(255),
  salario                       double,
  constraint pk_cargo primary key (id)
);

create table carrinho (
  produtos_id                   integer,
  venda_id                      integer,
  quantidade                    integer
);

create table categoria (
  id                            integer auto_increment not null,
  descricao                     varchar(255),
  constraint pk_categoria primary key (id)
);

create table cidade (
  id                            integer auto_increment not null,
  nome                          varchar(255),
  estado_id                     integer,
  constraint pk_cidade primary key (id)
);

create table cliente (
  id                            integer auto_increment not null,
  usuario_id                    integer,
  constraint uq_cliente_usuario_id unique (usuario_id),
  constraint pk_cliente primary key (id)
);

create table copa (
  id                            integer auto_increment not null,
  constraint pk_copa primary key (id)
);

create table endereco (
  id                            integer auto_increment not null,
  rua                           varchar(255),
  numero                        integer,
  complemento                   varchar(255),
  cidade_id                     integer,
  cep                           varchar(255),
  constraint pk_endereco primary key (id)
);

create table estado (
  id                            integer auto_increment not null,
  nome                          varchar(255),
  sigla                         varchar(255),
  pais_id                       integer,
  constraint pk_estado primary key (id)
);

create table funcionario (
  id                            integer auto_increment not null,
  usuario_id                    integer,
  cargo_id                      integer,
  constraint uq_funcionario_usuario_id unique (usuario_id),
  constraint pk_funcionario primary key (id)
);

create table pagamento (
  id                            integer auto_increment not null,
  valor                         double,
  data_pagamento                date,
  tipopagamento_id              integer,
  constraint pk_pagamento primary key (id)
);

create table pais (
  id                            integer auto_increment not null,
  nome                          varchar(255),
  constraint pk_pais primary key (id)
);

create table produto_entrada (
  id                            integer auto_increment not null,
  quantidade                    integer,
  valor                         double,
  data_entrada                  date,
  produtos_id                   integer,
  constraint pk_produto_entrada primary key (id)
);

create table produto_saida (
  id                            integer auto_increment not null,
  quantidade                    integer,
  valor                         double,
  data_saida                    date,
  produtos_id                   integer,
  constraint pk_produto_saida primary key (id)
);

create table produtos (
  id                            integer auto_increment not null,
  nome                          varchar(255),
  valor_compra                  double,
  preco_unitario                double,
  quantidade                    integer,
  copa_id                       integer,
  categoria_id                  integer,
  constraint pk_produtos primary key (id)
);

create table quadra (
  id                            integer auto_increment not null,
  valor_locacao                 double,
  copa_id                       integer,
  tipoquadra_id                 integer,
  constraint pk_quadra primary key (id)
);

create table reserva (
  id                            integer auto_increment not null,
  data_reserva                  datetime(6),
  data_entrada                  datetime(6),
  usuario_id                    integer,
  quadra_id                     integer,
  pagamento_id                  integer,
  constraint pk_reserva primary key (id)
);

create table tipo_pagamento (
  id                            integer auto_increment not null,
  tipo                          varchar(255),
  constraint pk_tipo_pagamento primary key (id)
);

create table tipo_quadra (
  id                            integer auto_increment not null,
  descricao                     varchar(255),
  constraint pk_tipo_quadra primary key (id)
);

create table usuario (
  id                            integer auto_increment not null,
  nome                          varchar(255),
  senha                         varchar(255),
  email                         varchar(255),
  cpf                           varchar(255),
  endereco_id                   integer,
  constraint pk_usuario primary key (id)
);

create table venda (
  id                            integer auto_increment not null,
  data_compra                   date,
  usuario_id                    integer,
  pagamento_id                  integer,
  valor_total                   double,
  copa_id                       integer,
  constraint pk_venda primary key (id)
);

alter table carrinho add constraint fk_carrinho_produtos_id foreign key (produtos_id) references produtos (id) on delete restrict on update restrict;
create index ix_carrinho_produtos_id on carrinho (produtos_id);

alter table carrinho add constraint fk_carrinho_venda_id foreign key (venda_id) references venda (id) on delete restrict on update restrict;
create index ix_carrinho_venda_id on carrinho (venda_id);

alter table cidade add constraint fk_cidade_estado_id foreign key (estado_id) references estado (id) on delete restrict on update restrict;
create index ix_cidade_estado_id on cidade (estado_id);

alter table cliente add constraint fk_cliente_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;

alter table endereco add constraint fk_endereco_cidade_id foreign key (cidade_id) references cidade (id) on delete restrict on update restrict;
create index ix_endereco_cidade_id on endereco (cidade_id);

alter table estado add constraint fk_estado_pais_id foreign key (pais_id) references pais (id) on delete restrict on update restrict;
create index ix_estado_pais_id on estado (pais_id);

alter table funcionario add constraint fk_funcionario_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;

alter table funcionario add constraint fk_funcionario_cargo_id foreign key (cargo_id) references cargo (id) on delete restrict on update restrict;
create index ix_funcionario_cargo_id on funcionario (cargo_id);

alter table pagamento add constraint fk_pagamento_tipopagamento_id foreign key (tipopagamento_id) references tipo_pagamento (id) on delete restrict on update restrict;
create index ix_pagamento_tipopagamento_id on pagamento (tipopagamento_id);

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

alter table reserva add constraint fk_reserva_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_reserva_usuario_id on reserva (usuario_id);

alter table reserva add constraint fk_reserva_quadra_id foreign key (quadra_id) references quadra (id) on delete restrict on update restrict;
create index ix_reserva_quadra_id on reserva (quadra_id);

alter table reserva add constraint fk_reserva_pagamento_id foreign key (pagamento_id) references pagamento (id) on delete restrict on update restrict;
create index ix_reserva_pagamento_id on reserva (pagamento_id);

alter table usuario add constraint fk_usuario_endereco_id foreign key (endereco_id) references endereco (id) on delete restrict on update restrict;
create index ix_usuario_endereco_id on usuario (endereco_id);

alter table venda add constraint fk_venda_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_venda_usuario_id on venda (usuario_id);

alter table venda add constraint fk_venda_pagamento_id foreign key (pagamento_id) references pagamento (id) on delete restrict on update restrict;
create index ix_venda_pagamento_id on venda (pagamento_id);

alter table venda add constraint fk_venda_copa_id foreign key (copa_id) references copa (id) on delete restrict on update restrict;
create index ix_venda_copa_id on venda (copa_id);


# --- !Downs

alter table carrinho drop foreign key fk_carrinho_produtos_id;
drop index ix_carrinho_produtos_id on carrinho;

alter table carrinho drop foreign key fk_carrinho_venda_id;
drop index ix_carrinho_venda_id on carrinho;

alter table cidade drop foreign key fk_cidade_estado_id;
drop index ix_cidade_estado_id on cidade;

alter table cliente drop foreign key fk_cliente_usuario_id;

alter table endereco drop foreign key fk_endereco_cidade_id;
drop index ix_endereco_cidade_id on endereco;

alter table estado drop foreign key fk_estado_pais_id;
drop index ix_estado_pais_id on estado;

alter table funcionario drop foreign key fk_funcionario_usuario_id;

alter table funcionario drop foreign key fk_funcionario_cargo_id;
drop index ix_funcionario_cargo_id on funcionario;

alter table pagamento drop foreign key fk_pagamento_tipopagamento_id;
drop index ix_pagamento_tipopagamento_id on pagamento;

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

alter table reserva drop foreign key fk_reserva_usuario_id;
drop index ix_reserva_usuario_id on reserva;

alter table reserva drop foreign key fk_reserva_quadra_id;
drop index ix_reserva_quadra_id on reserva;

alter table reserva drop foreign key fk_reserva_pagamento_id;
drop index ix_reserva_pagamento_id on reserva;

alter table usuario drop foreign key fk_usuario_endereco_id;
drop index ix_usuario_endereco_id on usuario;

alter table venda drop foreign key fk_venda_usuario_id;
drop index ix_venda_usuario_id on venda;

alter table venda drop foreign key fk_venda_pagamento_id;
drop index ix_venda_pagamento_id on venda;

alter table venda drop foreign key fk_venda_copa_id;
drop index ix_venda_copa_id on venda;

drop table if exists calendario;

drop table if exists cargo;

drop table if exists carrinho;

drop table if exists categoria;

drop table if exists cidade;

drop table if exists cliente;

drop table if exists copa;

drop table if exists endereco;

drop table if exists estado;

drop table if exists funcionario;

drop table if exists pagamento;

drop table if exists pais;

drop table if exists produto_entrada;

drop table if exists produto_saida;

drop table if exists produtos;

drop table if exists quadra;

drop table if exists reserva;

drop table if exists tipo_pagamento;

drop table if exists tipo_quadra;

drop table if exists usuario;

drop table if exists venda;

