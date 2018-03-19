CREATE DATABASE keppidb;
CREATE USER 'brewery'@'%' IDENTIFIED BY 'passme';
GRANT ALL ON keppidb.* TO 'brewery';