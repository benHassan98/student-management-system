create database college;

use college;

create table if not exists students(
    id integer primary key auto_increment,
    firstname varchar(50) not null,
    lastname varchar(50) not null,
    email varchar(50) unique not null,
    gpa float not null,
    created_date datetime
    );

create table if not exists teachers(
    id integer primary key auto_increment,
    firstname varchar(50) not null,
    lastname varchar(50) not null,
    email varchar(50) unique not null,
    created_date datetime
    );

create table if not exists courses(
    id varchar(15) primary key,
    teacher_id integer not null,
    description text not null,
    created_date datetime,
    foreign key(teacher_id) references teachers(id) on delete cascade
    );

create table if not exists students_courses(
    course_id varchar(15) not null,
    student_id integer not null,
    foreign key(student_id) references students(id) on delete cascade,
    foreign key(course_id) references courses(id) on delete cascade
    );

create table if not exists quizzes(
    id integer primary key auto_increment,
    teacher_id integer not null,
    course_id varchar(15) not null,
    student_id integer not null,
    score float not null,
    created_date datetime,
    foreign key(student_id) references students(id) on delete cascade,
    foreign key(course_id) references courses(id) on delete cascade,
    foreign key(teacher_id) references teachers(id) on delete cascade
    );

commit;


