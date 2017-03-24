create database if not exists tradeoptimization;
use tradeoptimization;

create table if not exists sgn(
id INT AUTO_INCREMENT PRIMARY KEY,
date TIMESTAMP,
result INT
);
