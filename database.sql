CREATE TABLE department (
  ID int(11) NOT NULL AUTO_INCREMENT,
  NAME varchar(60) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE seller (
  ID int(11) NOT NULL AUTO_INCREMENT,
  NAME varchar(60) NOT NULL,
  EMAIL varchar(100) NOT NULL,
  BIRTH_DATE datetime(6) NOT NULL,
  BASE_SALARY double NOT NULL,
  DEPARTMENT_ID int(11) NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (DEPARTMENT_ID) REFERENCES department (ID)
);

INSERT INTO department (NAME) VALUES 
  ('Computers'),
  ('Electronics'),
  ('Fashion'),
  ('Books');

INSERT INTO seller (NAME, EMAIL, BIRTH_DATE, BASE_SALARY, DEPARTMENT_ID) VALUES 
  ('Bob Brown','bob@gmail.com','1998-04-21 00:00:00',1000,1),
  ('Maria Green','maria@gmail.com','1979-12-31 00:00:00',3500,2),
  ('Alex Grey','alex@gmail.com','1988-01-15 00:00:00',2200,1),
  ('Martha Red','martha@gmail.com','1993-11-30 00:00:00',3000,4),
  ('Donald Blue','donald@gmail.com','2000-01-09 00:00:00',4000,3),
  ('Alex Pink','bob@gmail.com','1997-03-04 00:00:00',3000,2);