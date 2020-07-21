CREATE DATABASE IF NOT EXISTS `db_test`;
USE `db_test`;

CREATE TABLE `empleado` (
	`idEmpleado` INT(10) NOT NULL AUTO_INCREMENT,
	`nombres` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`apellidos` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`tipo_documento` VARCHAR(20) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`numero_documento` VARCHAR(30) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`fecha_nacimiento` DATE NULL DEFAULT NULL,
	`fecha_vinculacion` DATE NULL DEFAULT NULL,
	`cargo` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci',
	`salario` DOUBLE(22,0) NULL DEFAULT NULL,
	PRIMARY KEY (`idEmpleado`) USING BTREE
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=6
;