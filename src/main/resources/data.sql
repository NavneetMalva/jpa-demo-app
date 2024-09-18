insert into course( id,name)
values(101,'JPA Course'),
       (102,'AKS Course'),
       (103,'AWS Course'),
       (104,'Linux Administrator');


insert into passport(id,number)
values(40001,'E123456'),
      (40002,'N123457'),
      (40003,'L123890');

insert into student(id,name,passport_id)
values(20001,'Ranga',40001),
      (20002,'Adam',40002),
      (20003,'Jane',40003);

insert into review(id,rating,description)
values(50001,'5', 'Great Course'),
      (50002,'4', 'Wonderful Course'),
      (50003,'5', 'Awesome Course');