INSERT INTO developers  (name,age,sex,salary) VALUES
('Vasja',25,'male',1500),
 ('John',26,'male',1600),
 ('Peter',30,'male',2500),
 ('Maria',24,'female',2000),
 ('Jenny',27,'female',2300),
 ('Annie',22,'female',1000);
 INSERT INTO skills  (language,level) VALUES
('C#','Junior'),
 ('C#','Middle'),
 ('C#','Senior'),
 ('Java','Junior'),
 ('Java','Middle'),
 ('Java','Senior'),
 ('C++','Junior'),
 ('C++','Middle'),
 ('C++','Senior'),
 ('JS','Junior'),
 ('JS','Middle'),
 ('JS','Senior');
 INSERT INTO projects  (project_name,description) VALUES
('DB service',NULL),
 ('Microservice',NULL),
 ('Telegram Bot',NULL),
 ('Server for company',NULL),
 ('Site for company',NULL);
 INSERT INTO customers  (first_name,last_name) VALUES
('Douglas','Adamson'),
 ('Michael','Johnson'),
 ('Martin','Parson'),
 ('Adam','Flatcher');
 INSERT INTO companies  (company_name,quantity_employee) VALUES
 ('LG',5000),
 ('Samsung',8000),
 ('Apple',10000),
 ('Toyota',7000);
 INSERT INTO company_x_prj (company_id,project_id) VALUES
(1,1),
 (1,3),
 (2,2),
 (2,3),
 (2,4),
 (3,1),
 (4,4),
 (4,5);
 INSERT INTO custom_x_prj (custom_id,project_id) VALUES
(1,1),
 (1,3),
 (2,2),
 (3,4),
 (4,5);
 INSERT INTO dev_x_prj (developer_id,project_id) VALUES
(1,1),
 (1,2),
 (2,2),
 (3,1),
 (4,4),
 (5,5),
 (6,3);
 INSERT INTO dev_x_skills (developer_id,skills_id) VALUES
 (1,2),
 (1,4),
 (2,5),
 (2,7),
 (3,8),
 (4,5),
 (4,9),
 (5,3),
 (6,10);