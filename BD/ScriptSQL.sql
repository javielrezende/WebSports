-- -----------------------------------------------------
-- Schema websports
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS websports DEFAULT CHARACTER SET utf8 ;
USE websports ;

-- -----------------------------------------------------
-- Table pais
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pais (
  id INT NOT NULL,
  nome VARCHAR(150) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX nome_UNIQUE (nome ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estado
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estado (
  id INT NOT NULL,
  nome VARCHAR(150) NOT NULL,
  sigla CHAR(2) NOT NULL,
  pais_id INT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX sigla_UNIQUE (sigla ASC),
  UNIQUE INDEX nome_UNIQUE (nome ASC),
  INDEX fk_estado_pais1_idx (pais_id ASC),
  CONSTRAINT fk_estado_pais1
    FOREIGN KEY (pais_id)
    REFERENCES pais (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table cidade
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cidade (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(150) NOT NULL,
  estado_id INT NOT NULL,
  PRIMARY KEY (id),
  UNIQUE INDEX nome_UNIQUE (nome ASC),
  INDEX fk_cidade_estado_idx (estado_id ASC),
  CONSTRAINT fk_cidade_estado
    FOREIGN KEY (estado_id)
    REFERENCES estado (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table endereco
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS endereco (
  id INT NOT NULL AUTO_INCREMENT,
  rua VARCHAR(120) NOT NULL,
  numero INT(11) NOT NULL,
  complemento VARCHAR(50) NULL DEFAULT NULL,
  cidade_id INT NOT NULL,
  cep CHAR(9) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_endereco_cidade_idx (cidade_id ASC),
  CONSTRAINT fk_endereco_cidade
    FOREIGN KEY (cidade_id)
    REFERENCES cidade (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table usuario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
  id INT NOT NULL,
  nome VARCHAR(150) NOT NULL,
  senha VARCHAR(32) NOT NULL,
  email VARCHAR(120) NOT NULL,
  cpf CHAR(11) NOT NULL,
  endereco_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_usuario_endereco1_idx (endereco_id ASC),
  CONSTRAINT fk_usuario_endereco1
    FOREIGN KEY (endereco_id)
    REFERENCES endereco (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table copa
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS copa (
  id INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table quadra
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS quadra (
  id INT NOT NULL AUTO_INCREMENT,
  valorLocacao DOUBLE NOT NULL,
  copa_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_quadras_copa1_idx (copa_id ASC),
  CONSTRAINT fk_quadras_copa1
    FOREIGN KEY (copa_id)
    REFERENCES copa (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table tipoPagamento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS tipoPagamento (
  id INT NOT NULL AUTO_INCREMENT,
  tipo VARCHAR(100) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table pagamento
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pagamento (
  id INT NOT NULL AUTO_INCREMENT,
  valor DOUBLE(9,2) NOT NULL,
  dataPagamento DATETIME NOT NULL,
  tipoPagamento_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_pagamento_tipoPagamento1_idx (tipoPagamento_id ASC),
  CONSTRAINT fk_pagamento_tipoPagamento1
    FOREIGN KEY (tipoPagamento_id)
    REFERENCES tipoPagamento (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table reserva
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS reserva (
  id INT NOT NULL,
  dataReservada DATETIME NOT NULL,
  dataEntrada DATETIME NOT NULL,
  usuario_id INT NOT NULL,
  quadra_id INT NOT NULL,
  qtdHoras TIME NOT NULL,
  pagamento_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_reserva_usuario1_idx (usuario_id ASC),
  INDEX fk_reserva_quadras1_idx (quadra_id ASC),
  INDEX fk_reserva_pagamento1_idx (pagamento_id ASC),
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table venda
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS venda (
  id INT NOT NULL AUTO_INCREMENT,
  dataCompra DATETIME NOT NULL,
  usuario_id INT NOT NULL,
  pagamento_id INT NOT NULL,
  valorTotal DOUBLE(9,2) NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_compra_usuario1_idx (usuario_id ASC),
  INDEX fk_venda_pagamento1_idx (pagamento_id ASC),
  CONSTRAINT fk_compra_usuario1
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_venda_pagamento1
    FOREIGN KEY (pagamento_id)
    REFERENCES pagamento (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table fornecedor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS fornecedor (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(150) NOT NULL,
  cep CHAR(9) NOT NULL,
  email VARCHAR(120) NOT NULL,
  cnpj CHAR(18) NOT NULL,
  inscricaoEstadual VARCHAR(45) NOT NULL,
  telefone CHAR(14) NOT NULL,
  endereco_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_fornecedor_endereco1_idx (endereco_id ASC),
  CONSTRAINT fk_fornecedor_endereco1
    FOREIGN KEY (endereco_id)
    REFERENCES endereco (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table categoria
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS categoria (
  id INT NOT NULL AUTO_INCREMENT,
  descricao VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table produtos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produtos (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(150) NOT NULL,
  valorCompra DOUBLE NOT NULL,
  preçoUnitario DOUBLE NOT NULL,
  quantidade INT(11) NOT NULL,
  copa_id INT NOT NULL,
  fornecedor_id INT NOT NULL,
  categoria_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_produtos_copa1_idx (copa_id ASC),
  INDEX fk_produtos_fornecedor1_idx (fornecedor_id ASC),
  INDEX fk_produtos_categoria1_idx (categoria_id ASC),
  CONSTRAINT fk_produtos_copa1
    FOREIGN KEY (copa_id)
    REFERENCES copa (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_produtos_fornecedor1
    FOREIGN KEY (fornecedor_id)
    REFERENCES fornecedor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_produtos_categoria1
    FOREIGN KEY (categoria_id)
    REFERENCES categoria (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table carrinho
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS carrinho (
  produtos_id INT NOT NULL,
  venda_id INT NOT NULL,
  quantidade INT NOT NULL,
  INDEX fk_carrinho_produtos1_idx (produtos_id ASC),
  INDEX fk_carrinho_compra1_idx (venda_id ASC),
  PRIMARY KEY (produtos_id, venda_id),
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
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table produtoEntrada
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produtoEntrada (
  id INT NOT NULL,
  quantidade INT NOT NULL,
  valor DOUBLE(9,2) NOT NULL,
  dataEntrada DATETIME NOT NULL,
  produtos_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_produtoEntrada_produtos1_idx (produtos_id ASC),
  CONSTRAINT fk_produtoEntrada_produtos1
    FOREIGN KEY (produtos_id)
    REFERENCES produtos (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table produtoSaida
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS produtoSaida (
  id INT NOT NULL,
  quantidade INT NOT NULL,
  valor DOUBLE(9,2) NOT NULL,
  dataSaida DATETIME NOT NULL,
  produtos_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_produtoSaida_produtos1_idx (produtos_id ASC),
  CONSTRAINT fk_produtoSaida_produtos1
    FOREIGN KEY (produtos_id)
    REFERENCES produtos (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table cargo
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cargo (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(60) NOT NULL,
  salario DOUBLE NOT NULL,
  PRIMARY KEY (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table cliente
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS cliente (
  id INT(11) NOT NULL AUTO_INCREMENT,
  usuario_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_cliente_usuario1_idx (usuario_id ASC),
  CONSTRAINT fk_cliente_usuario1
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table funcionario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS funcionario (
  id INT(11) NOT NULL AUTO_INCREMENT,
  usuario_id INT NOT NULL,
  cargo_id INT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_funcionario_usuario1_idx (usuario_id ASC),
  INDEX fk_funcionario_cargo1_idx (cargo_id ASC),
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
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;
