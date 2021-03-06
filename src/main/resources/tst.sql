use tradeoptimization;
create database if not exists tradeoptimization;

load data local infile
'c:\\EURAUD-5.csv'
 into table sgn fields terminated by ','
  enclosed by '"'
  lines terminated by '\n'
    (date, result, pair);

create table sgn(
id INT AUTO_INCREMENT PRIMARY KEY,
date TIMESTAMP,
result INT,
pair varchar(10)
);

create table if not exists pair(
id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(10) 
);

create table sgn(
date TIMESTAMP,
result INT
);


drop table sgn;

insert into signal (result) values(1);

INSERT INTO signal VALUES ('2013-08-05 18:19:03',1 );

create database tst;

use tst;

create table human(
id INT AUTO_INCREMENT PRIMARY KEY,
name varchar(20),
secondname varchar(20)
);

insert into human (name, secondname) values ("Dmitry", "Shilko");

insert into human (name, secondname, date) values ("Kate", "Shilko", '2017-03-24 09:27:29');

ALTER TABLE human
add date TIMESTAMP;

show tables;