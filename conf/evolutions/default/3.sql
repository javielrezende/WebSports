# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

INSERT INTO cargo(nome, salario) VALUES
("Administrador", 2000.00);


INSERT INTO pais(nome) VALUES
("Brasil");

INSERT INTO estado(nome, sigla, pais_id) VALUES
("Rio Grande do Sul", "RS", 1);

INSERT INTO cidade(nome, estado_id) VALUES
("Pelotas", 1);

# --- !Downs
