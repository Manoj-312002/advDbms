CREATE TABLE students (
  ssn int,
  fname varchar(10),
  PRIMARY KEY (ssn)
);

CREATE TABLE courses (
  cid int,
  credits int,
  cname varchar(50),
  PRIMARY KEY (cid)
);

CREATE TABLE preq (
  c1 int , 
  c2 int ,
  FOREIGN KEY(c1) REFERENCES courses(cid),
  FOREIGN KEY(c2) REFERENCES courses(cid),
  PRIMARY KEY (c1,c2)
);

select t1.cname , t2.cname from preq , courses t1 , courses t2 where t1.cid = preq.c1 and t2.cid = preq.c2;

-- select fname , cname from courses_enr , students, courses where ssn = Sssn and Ecid = cid;

CREATE TABLE courses_enr(
  Sssn int , 
  Ecid int , 
  FOREIGN KEY(Sssn) REFERENCES students(ssn),
  FOREIGN KEY(Ecid) REFERENCES courses(cid),
  PRIMARY KEY(Sssn , Ecid )
);

INSERT INTO students VALUES
(1,'Alice'),
(2,'Theodore'),
(3,'Joy'),
(4,'Abner'),
(5,'Michael'),
(6,'Alice'),
(7,'Elizabeth');

INSERT INTO courses VALUES
(1, 3, 'Maths-1'),
(2, 4, 'Maths-2'),
(3, 3, 'Maths-3'),
(4, 3, 'DBMS'),
(5, 3, 'Web Tech'),
(6, 3, 'Advanced DBMS'),
(7, 3, 'ITE'),
(8, 3, 'Java'),
(9, 3, 'Computer Networks'),
(10, 3, 'Python'),
(11, 4, 'Data Structures'),
(12, 4, 'Operating Systems'),
(13, 4, 'Cloud'),
(14, 4, 'Data Analytics');

-- 1 is required by 2
INSERT INTO preq VALUES
(1 , 2),
(2 , 3),
(8 , 5),
(4 , 6),
(11 , 6),
(10 , 12),
(5 , 13),
(9 , 13),
(5 , 14);

INSERT INTO preq VALUES (3 ,6) , (10 ,3);

INSERT INTO courses_enr VALUES
(1 , 1),
(1 , 2),
(1 , 3),
(1 , 4),
(1 , 5),
(1 , 6),
(2 , 7),
(2 , 8),
(2 , 9),
(3 , 5),
(3 , 4),
(4 , 14),
(5 , 14),
(5 , 10),
(6 , 9),
(7 , 10),
(7 , 12),
(7 , 5);