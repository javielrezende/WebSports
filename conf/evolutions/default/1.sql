# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cargo (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  salario                       double,
  constraint pk_cargo primary key (id)
);

create table carrinho (
  protutos_id                   bigint,
  venda_id                      bigint,
  quantidade                    integer
);

create table categoria (
  id                            bigint auto_increment not null,
  descricao                     varchar(255),
  constraint pk_categoria primary key (id)
);

create table cidade (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  estado_id                     bigint,
  constraint pk_cidade primary key (id)
);

create table cliente (
  id                            bigint auto_increment not null,
  usuario_id                    bigint,
  constraint uq_cliente_usuario_id unique (usuario_id),
  constraint pk_cliente primary key (id)
);

create table copa (
  id                            bigint auto_increment not null,
  constraint pk_copa primary key (id)
);

create table endereco (
  id                            bigint auto_increment not null,
  rua                           varchar(255),
  numero                        integer,
  complemento                   varchar(255),
  cidade_id                     bigint,
  cep                           varchar(255),
  constraint pk_endereco primary key (id)
);

create table estado (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  sigla                         varchar(255),
  pais_id                       bigint,
  constraint pk_estado primary key (id)
);

create table funcionario (
  id                            bigint auto_increment not null,
  usuario_id                    bigint,
  cargo_id                      bigint,
  constraint uq_funcionario_usuario_id unique (usuario_id),
  constraint pk_funcionario primary key (id)
);

create table pagamento (
  id                            bigint auto_increment not null,
  valor                         double,
  data_pagamento                date,
  tipopagamento_id              bigint,
  constraint pk_pagamento primary key (id)
);

create table pais (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  constraint pk_pais primary key (id)
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
  usuario_id                    bigint,
  quadra_id                     bigint,
  qtd_horas                     time,
  pagamento_id                  bigint,
  constraint pk_reserva primary key (id)
);

create table tipo_pagamento (
  id                            bigint auto_increment not null,
  tipo                          varchar(255),
  constraint pk_tipo_pagamento primary key (id)
);

create table tipo_quadra (
  id                            bigint auto_increment not null,
  descricao                     varchar(255),
  constraint pk_tipo_quadra primary key (id)
);

create table usuario (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  senha                         varchar(255),
  email                         varchar(255),
  cpf                           varchar(255),
  endereco_id                   bigint,
  constraint pk_usuario primary key (id)
);

create table venda (
  id                            bigint auto_increment not null,
  data_compra                   date,
  usuario_id                    bigint,
  pagamento_id                  bigint,
  valor_total                   double,
  copa_id                       bigint,
  constraint pk_venda primary key (id)
);

alter table carrinho add constraint fk_carrinho_protutos_id foreign key (protutos_id) references produtos (id) on delete restrict on update restrict;
create index ix_carrinho_protutos_id on carrinho (protutos_id);

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

alter table pagamento add constraint fk_pagamento_tipopagamento_id foreign key (tipopagamento_id) references pagamento (id) on delete restrict on update restrict;
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

alter table carrinho drop foreign key fk_carrinho_protutos_id;
drop index ix_carrinho_protutos_id on carrinho;

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

