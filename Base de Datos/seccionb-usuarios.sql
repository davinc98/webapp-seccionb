CREATE SCHEMA `seccionb` ;

/*============USUARIO DE BASE DE DATOS======================================*/
DROP USER 'seccionbuser'@'localhost';

CREATE USER 'seccionbuser'@'localhost' IDENTIFIED BY 'il92o7f9d';
GRANT ALL PRIVILEGES ON *.* TO 'seccionbuser'@'localhost';

GRANT ALL PRIVILEGES ON seccionb.* TO 'seccionbuser'@'localhost';

SHOW GRANTS FOR 'seccionbuser'@'localhost';

