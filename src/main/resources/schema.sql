DROP TABLE IF EXISTS TBL_EMPLOYEES;

CREATE TABLE TBL_EMPLOYEES (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               first_name VARCHAR(250) NOT NULL,
                               last_name VARCHAR(250) NOT NULL,
                               email VARCHAR(250) DEFAULT NULL
);

CREATE  TABLE users(
    id int auto_increment primary key,
    username varchar(250) not null,
    email varchar(100) not null ,
    password varchar(250) not null
)