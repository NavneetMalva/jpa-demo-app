insert into course( id,name)
values(10001,'JPA Course'),
      (10002,'AKS Course'),
      (10003,'AWS Course'),
      (10004,'Linux Administrator');

insert into passport(id,number)
values(40001,'E123456'),
      (40002,'N123457'),
      (40003,'L123890');

insert into student(id,name,passport_id)
values(20001,'Ranga',40001),
      (20002,'Adam',40002),
      (20003,'Jane',40003);

insert into review(id,rating,description,course_id)
values(50001,'5', 'Great Course',10001),
      (50002,'4', 'Wonderful Course',10001),
      (50003,'5', 'Awesome Course',10003);

-- Queries
-- SELECT * FROM COURSE JOIN REVIEW ON COURSE.ID=REVIEW.COURSE_ID

insert into student_course(student_id,course_id)
values(20001,10001),
      (20002,10001),
      (20003,10001),
      (20001,10003);

-- for ManyToMany
-- SELECT * FROM student_course,student,course
-- WHERE student_course.student_id=student.id
-- AND student_course.course_id=course.id
