

-- -----------------------------------------------------
-- Schema websports
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS websports DEFAULT CHARACTER SET utf8 ;
USE websports ;

-- -----------------------------------------------------
-- Table pais
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pais (
  id INT NOT NULL COMMENT '',
  nome VARCHAR(150) NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  UNIQUE INDEX nome_UNIQUE (nome ASC)  COMMENT '')
;


-- -----------------------------------------------------
-- Table estado
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estado (
  id INT NOT NULL COMMENT '',
  nome VARCHAR(150) NOT NULL COMMENT '',
  sigla CHAR(2) NOT NULL COMMENT '',
  pais_id INT NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  UNIQUE INDEX sigla_UNIQUE (sigla ASC)  COMMENT '',
  UNIQUE INDEX nome_UNIQUE (nome ASC)  COMMENT '',
  INDEX fk_estado_pais1_idx (pais_id ASC)  COMMENT '',
  CONSTRAINT fk_estado_pais1
    FOREIGN KEY (pais_id)
    REFERENCES pais (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table cidade
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cidade (
  id INT NOT NULL AUTO_INCREMENT COMMENT '',
  nome VARCHAR(150) NOT NULL COMMENT '',
  estado_id INT NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  UNIQUE INDEX nome_UNIQUE (nome ASC)  COMMENT '',
  INDEX fk_cidade_estado_idx (estado_id ASC)  COMMENT '',
  CONSTRAINT fk_cidade_estado
    FOREIGN KEY (estado_id)
    REFERENCES estado (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table endereco
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS endereco (
  id INT NOT NULL AUTO_INCREMENT COMMENT '',
  rua VARCHAR(120) NOT NULL COMMENT '',
  numero INT(11) NOT NULL COMMENT '',
  complemento VARCHAR(50) NULL DEFAULT NULL COMMENT '',
  cidade_id INT NOT NULL COMMENT '',
  cep CHAR(9) NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  INDEX fk_endereco_cidade_idx (cidade_id ASC)  COMMENT '',
  CONSTRAINT fk_endereco_cidade
    FOREIGN KEY (cidade_id)
    REFERENCES cidade (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table usuario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
  id INT NOT NULL COMMENT '',
  nome VARCHAR(150) NOT NULL COMMENT '',
  senha VARCHAR(32) NOT NULL COMMENT '',
  email VARCHAR(120) NOT NULL COMMENT '',
  cpf CHAR(11) NOT NULL COMMENT '',
  endereco_id INT NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  INDEX fk_usuario_endereco1_idx (endereco_id ASC)  COMMENT '',
  CONSTRAINT fk_usuario_endereco1
    FOREIGN KEY (endereco_id)
    REFERENCES endereco (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table copa
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS copa (
  id INT NOT NULL AUTO_INCREMENT COMMENT '',
  PRIMARY KEY (id)  COMMENT '')
;


-- -----------------------------------------------------
-- Table tipoQuadra
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tipoQuadra (
  id INT NOT NULL COMMENT '',
  descricao VARCHAR(100) NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '')
;


-- -----------------------------------------------------
-- Table quadra
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS quadra (
  id INT NOT NULL AUTO_INCREMENT COMMENT '',
  valorLocacao DOUBLE NOT NULL COMMENT '',
  copa_id INT NOT NULL COMMENT '',
  tipoQuadra_id INT NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  INDEX fk_quadras_copa1_idx (copa_id ASC)  COMMENT '',
  INDEX fk_quadra_tipoQuadra1_idx (tipoQuadra_id ASC)  COMMENT '',
  CONSTRAINT fk_quadras_copa1
    FOREIGN KEY (copa_id)
    REFERENCES copa (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_quadra_tipoQuadra1
    FOREIGN KEY (tipoQuadra_id)
    REFERENCES tipoQuadra (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table tipoPagamento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tipoPagamento (
  id INT NOT NULL AUTO_INCREMENT COMMENT '',
  tipo VARCHAR(100) NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '')
;


-- -----------------------------------------------------
-- Table pagamento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pagamento (
  id INT NOT NULL AUTO_INCREMENT COMMENT '',
  valor DOUBLE(9,2) NOT NULL COMMENT '',
  dataPagamento DATETIME NOT NULL COMMENT '',
  tipoPagamento_id INT NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  INDEX fk_pagamento_tipoPagamento1_idx (tipoPagamento_id ASC)  COMMENT '',
  CONSTRAINT fk_pagamento_tipoPagamento1
    FOREIGN KEY (tipoPagamento_id)
    REFERENCES tipoPagamento (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table reserva
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS reserva (
  id INT NOT NULL COMMENT '',
  dataReservada DATETIME NOT NULL COMMENT '',
  dataEntrada DATETIME NOT NULL COMMENT '',
  usuario_id INT NOT NULL COMMENT '',
  quadra_id INT NOT NULL COMMENT '',
  qtdHoras TIME NOT NULL COMMENT '',
  pagamento_id INT NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  INDEX fk_reserva_usuario1_idx (usuario_id ASC)  COMMENT '',
  INDEX fk_reserva_quadras1_idx (quadra_id ASC)  COMMENT '',
  INDEX fk_reserva_pagamento1_idx (pagamento_id ASC)  COMMENT '',
  CONSTRAINT fk_reserva_usuario1
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_reserva_quadras1
    FOREIGN KEY (quadra_id)
    REFERENCES quadra (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_reserva_pagamento1
    FOREIGN KEY (pagamento_id)
    REFERENCES pagamento (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table venda
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS venda (
  id INT NOT NULL AUTO_INCREMENT COMMENT '',
  dataCompra DATETIME NOT NULL COMMENT '',
  usuario_id INT NOT NULL COMMENT '',
  pagamento_id INT NOT NULL COMMENT '',
  valorTotal DOUBLE(9,2) NOT NULL COMMENT '',
  copa_id INT NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  INDEX fk_compra_usuario1_idx (usuario_id ASC)  COMMENT '',
  INDEX fk_venda_pagamento1_idx (pagamento_id ASC)  COMMENT '',
  INDEX fk_venda_copa1_idx (copa_id ASC)  COMMENT '',
  CONSTRAINT fk_compra_usuario1
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_venda_pagamento1
    FOREIGN KEY (pagamento_id)
    REFERENCES pagamento (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_venda_copa1
    FOREIGN KEY (copa_id)
    REFERENCES copa (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table categoria
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS categoria (
  id INT NOT NULL AUTO_INCREMENT COMMENT '',
  descricao VARCHAR(45) NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '')
;


-- -----------------------------------------------------
-- Table produtos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produtos (
  id INT NOT NULL AUTO_INCREMENT COMMENT '',
  nome VARCHAR(150) NOT NULL COMMENT '',
  valorCompra DOUBLE NOT NULL COMMENT '',
  preçoUnitario DOUBLE NOT NULL COMMENT '',
  quantidade INT(11) NOT NULL COMMENT '',
  copa_id INT NOT NULL COMMENT '',
  fornecedor_id INT NOT NULL COMMENT '',
  categoria_id INT NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  INDEX fk_produtos_copa1_idx (copa_id ASC)  COMMENT '',
  INDEX fk_produtos_categoria1_idx (categoria_id ASC)  COMMENT '',
  CONSTRAINT fk_produtos_copa1
    FOREIGN KEY (copa_id)
    REFERENCES copa (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_produtos_categoria1
    FOREIGN KEY (categoria_id)
    REFERENCES categoria (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table carrinho
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS carrinho (
  produtos_id INT NOT NULL COMMENT '',
  venda_id INT NOT NULL COMMENT '',
  quantidade INT NOT NULL COMMENT '',
  INDEX fk_carrinho_produtos1_idx (produtos_id ASC)  COMMENT '',
  INDEX fk_carrinho_compra1_idx (venda_id ASC)  COMMENT '',
  PRIMARY KEY (produtos_id, venda_id)  COMMENT '',
  CONSTRAINT fk_carrinho_produtos1
    FOREIGN KEY (produtos_id)
    REFERENCES produtos (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_carrinho_compra1
    FOREIGN KEY (venda_id)
    REFERENCES venda (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table produtoEntrada
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produtoEntrada (
  id INT NOT NULL COMMENT '',
  quantidade INT NOT NULL COMMENT '',
  valor DOUBLE(9,2) NOT NULL COMMENT '',
  dataEntrada DATETIME NOT NULL COMMENT '',
  produtos_id INT NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  INDEX fk_produtoEntrada_produtos1_idx (produtos_id ASC)  COMMENT '',
  CONSTRAINT fk_produtoEntrada_produtos1
    FOREIGN KEY (produtos_id)
    REFERENCES produtos (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table produtoSaida
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produtoSaida (
  id INT NOT NULL COMMENT '',
  quantidade INT NOT NULL COMMENT '',
  valor DOUBLE(9,2) NOT NULL COMMENT '',
  dataSaida DATETIME NOT NULL COMMENT '',
  produtos_id INT NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  INDEX fk_produtoSaida_produtos1_idx (produtos_id ASC)  COMMENT '',
  CONSTRAINT fk_produtoSaida_produtos1
    FOREIGN KEY (produtos_id)
    REFERENCES produtos (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

-- -----------------------------------------------------
-- Table cargo
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cargo (
  id INT NOT NULL AUTO_INCREMENT COMMENT '',
  nome VARCHAR(60) NOT NULL COMMENT '',
  salario DOUBLE NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '')
;


-- -----------------------------------------------------
-- Table cliente
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cliente (
  id INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  usuario_id INT NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  INDEX fk_cliente_usuario1_idx (usuario_id ASC)  COMMENT '',
  CONSTRAINT fk_cliente_usuario1
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table funcionario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS funcionario (
  id INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  usuario_id INT NOT NULL COMMENT '',
  cargo_id INT NOT NULL COMMENT '',
  PRIMARY KEY (id)  COMMENT '',
  INDEX fk_funcionario_usuario1_idx (usuario_id ASC)  COMMENT '',
  INDEX fk_funcionario_cargo1_idx (cargo_id ASC)  COMMENT '',
  CONSTRAINT fk_funcionario_usuario1
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_funcionario_cargo1
    FOREIGN KEY (cargo_id)
    REFERENCES cargo (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

