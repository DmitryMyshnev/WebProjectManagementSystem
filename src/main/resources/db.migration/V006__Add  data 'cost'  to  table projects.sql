update projects set cost = ( select sum(salary) as total from developers as d
join developer_project as dxp on d.id = dxp.developer_id
join projects as p on p.id = dxp. project_id
where p.id = (select id from projects where project_name = 'DB service')) where id =1;

update projects set cost = ( select sum(salary) as total from developers as d
join developer_project as dxp on d.id = dxp.developer_id
join projects as p on p.id = dxp. project_id
where p.id = (select id from projects where project_name = 'Microservice')) where id =2;

update projects set cost = ( select sum(salary) as total from developers as d
join developer_project as dxp on d.id = dxp.developer_id
join projects as p on p.id = dxp. project_id
where p.id = (select id from projects where project_name = 'Telegram Bot')) where id =3;

update projects set cost = ( select sum(salary) as total from developers as d
join developer_project as dxp on d.id = dxp.developer_id
join projects as p on p.id = dxp. project_id
where p.id = (select id from projects where project_name = 'Server for company')) where id =4;

update projects set cost = ( select sum(salary) as total from developers as d
join developer_project as dxp on d.id = dxp.developer_id
join projects as p on p.id = dxp. project_id
where p.id = (select id from projects where project_name = 'Site for company')) where id =5;

