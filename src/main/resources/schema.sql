create database GradesDB;
create user 'grades'@'localhost' identified by 'grades';
grant all privileges on GradesDB.* to 'grades'@'localhost';


create table users(
    username varchar (50) not null primary key,
    password varchar (50) not null,
    enabled  boolean
);

create table authorities (
    username varchar (50),
    authority varchar (50),
    constraint fk_name foreign key (username) references users(username)
);

insert into users values('deka','dimi',true);
insert into users values('kostaki','kostaki',true);
insert into users values('telemachus','tele',true);
insert into users values('christos','chris',true);
insert into users values('eliza','elis',true);

insert into authorities values('deka','ROLE_USER');
insert into authorities values('kostaki','ROLE_USER');
insert into authorities values('telemachus','ROLE_USER');
insert into authorities values('christos','ROLE_USER');
insert into authorities values('eliza','ROLE_USER');

create table student (
    id int(5) not null primary key,
    fullname varchar (50),
    username varchar (50),
    cardid varchar (30),
    semester int(3),
    constraint fk_username foreign key (username) references users(username)
);

create table courses (
    id int(4) not null primary key,
    coursename varchar (30),
    semester int(3)
);

create table studentCourses (
    studid int(5) not null,
    courseid int(4) not null,
    constraint  fk_studid foreign key (studid) references student(id),
    constraint fk_course foreign key(courseid) references courses(id)
);

create table studentGrades(
    studid int(5) not null,
    courseid int(4) not null,
    grade float(4),
    constraint fk_stud foreign key(studid) references student(id),
    constraint fk_courseid foreign key (courseid) references courses(id)
);

insert into student values(1,'dimi nteka','deka','1234',1);
insert into student values(2,'dimi kostaki','kostaki','123334',2);
insert into student values(3,'telemachus kostakis','telemachus','12234',1);
insert into student values(4,'christos kostakis','christos','11234',2);
insert into student values(5,'eliza sismanidou','eliza','123455',3);

insert into courses values(1,'System Programming',2);
insert into courses values(2,'Java Programming',1);
insert into courses values(3,'C Programming',1);
insert into courses values(4,'Networks Systems',3);
insert into courses values(5,'Analysis',1);
insert into courses values(6,'Discrete Mathematics',2);
insert into courses values(7,'Information System',2);

insert into studentCourses values(1,2);
insert into studentCourses values(1,3);
insert into studentCourses values(1,5);
insert into studentCourses values(2,1);
insert into studentCourses values(2,6);
insert into studentCourses values(2,7);
insert into studentCourses values(3,2);
insert into studentCourses values(3,3);
insert into studentCourses values(3,5);
insert into studentCourses values(4,1);
insert into studentCourses values(4,6);
insert into studentCourses values(4,7);
insert into studentCourses values(5,4);

insert into studentGrades values(1,2,12.5);
insert into studentGrades values(1,3,14.51);
insert into studentGrades values(1,5,10.11);

insert into studentGrades values(2,1,9.5);
insert into studentGrades values(2,6,17.51);
insert into studentGrades values(2,7,16.11);

insert into studentGrades values(3,2,18.15);
insert into studentGrades values(3,3,13.67);
insert into studentGrades values(3,5,20.00);

insert into studentGrades values(4,1,11.5);
insert into studentGrades values(4,6,17.32);
insert into studentGrades values(4,7,8.23);

insert into studentGrades values(5,4,8.23);