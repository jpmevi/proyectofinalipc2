
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `BANCO` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BANCO`;
USE `BANCO` ;


-- -----------------------------------------------------
-- Table `LIMITE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `LIMITE` (
  `limiteReporte2` DOUBLE NOT NULL,
  `limiteReporte3` DOUBLE NOT NULL,
  PRIMARY KEY (`limiteReporte2`,`limiteReporte3`));
  
  INSERT INTO LIMITE VALUES(0,1);
  
-- -----------------------------------------------------
-- Table `CLIENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CLIENTE` (
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `dpi` VARCHAR(45) NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(200) NOT NULL,
  `pdfdpi` MEDIUMBLOB NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`codigo`));


-- -----------------------------------------------------
-- Table `CAJERO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CAJERO` (
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `turno` VARCHAR(200) NOT NULL,
  `dpi` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(200) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`codigo`));


-- -----------------------------------------------------
-- Table `GERENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GERENTE` (
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `turno` VARCHAR(200) NOT NULL,
  `dpi` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(200) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`codigo`));


-- -----------------------------------------------------
-- Table `CUENTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `CUENTA` (
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `fechaCreacion` DATE NOT NULL,
  `monto` DOUBLE NOT NULL,
  `cliente_codigo` BIGINT(19) NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cliente_codigo`)
    REFERENCES `CLIENTE` (`codigo`));


-- -----------------------------------------------------
-- Table `TRANSACCION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `TRANSACCION` (
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `monto` INT NOT NULL,
  `cajero_codigo` BIGINT(19) NOT NULL,
  `cuenta_codigo` BIGINT(19) NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cajero_codigo`)
    REFERENCES `CAJERO` (`codigo`),
    FOREIGN KEY (`cuenta_codigo`)
    REFERENCES `CUENTA` (`codigo`));


-- -----------------------------------------------------
-- Table `SOLICITUD`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SOLICITUD` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `estado` VARCHAR(200) NOT NULL,
  `cuenta_codigoenvio` BIGINT(19) NOT NULL,
  `cueanta_codigorecibe` BIGINT(19) NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cuenta_codigoenvio`)
    REFERENCES `CUENTA` (`codigo`),
    FOREIGN KEY (`cueanta_codigorecibe`)
    REFERENCES `CUENTA` (`codigo`));


-- -----------------------------------------------------
-- Table `ASOCIACION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ASOCIACION` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `fecha` VARCHAR(45) NOT NULL,
  `solicitud_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`solicitud_codigo`)
    REFERENCES `SOLICITUD` (`codigo`));


-- -----------------------------------------------------
-- Table `HISTORIAL_CLIENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HISTORIAL_CLIENTE` (
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `dpi` VARCHAR(45) NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(200) NOT NULL,
  `pdfdpi` MEDIUMBLOB NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `cliente_codigo` BIGINT(19) NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cliente_codigo`)
    REFERENCES `CLIENTE` (`codigo`));


-- -----------------------------------------------------
-- Table `HISTORIAL_CAJERO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HISTORIAL_CAJERO` (
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `turno` VARCHAR(200) NOT NULL,
  `dpi` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(200) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `cajero_codigo` BIGINT(19) NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`cajero_codigo`)
    REFERENCES `CAJERO` (`codigo`));


-- -----------------------------------------------------
-- Table `HISTORIAL_GERENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HISTORIAL_GERENTE` (
  `codigo` BIGINT(19) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `turno` VARCHAR(200) NOT NULL,
  `dpi` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `sexo` VARCHAR(200) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `gerente_codigo` BIGINT(19) NOT NULL,
  PRIMARY KEY (`codigo`),
    FOREIGN KEY (`gerente_codigo`)
    REFERENCES `GERENTE` (`codigo`));
    
    INSERT INTO CAJERO VALUES(101,'Banca virtual','Toda hora',101,'','','8cX7%%tedj4!yJm4');
