/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  rizwan
 * Created: Nov 1, 2017
 */

-- Drop Database dell_agents;
CREATE DATABASE IF NOT EXISTS dell_agents;
USE dell_agents;
Create table area_manager(name VARCHAR(26) NOT NULL PRIMARY KEY);
Create table manager(name VARCHAR(26) NOT NULL PRIMARY KEY, area_manager VARCHAR(20), FOREIGN KEY (area_manager) REFERENCES area_manager(name));
Create table agents(name VARCHAR(26), manager VARCHAR(26), FOREIGN KEY (manager) REFERENCES manager(name), cx VARCHAR(10), rar_1d VARCHAR(10), rar_7d VARCHAR(10), rar_28d VARCHAR(10), rdr_7d VARCHAR(10), obsr VARCHAR(10), c2f VARCHAR(20), c2f_rc VARCHAR(20), iblr VARCHAR(10), qcr VARCHAR(10), oblr VARCHAR(10), dps VARCHAR(10), AF VARCHAR(20), crw VARCHAR(20), aht VARCHAR(10), ob VARCHAR(10), utiliz VARCHAR(10), dps_adpt VARCHAR(10));
-- insert into area_manager(name) values ('Amit');
-- insert into manager (name, area_manager) select "Amit_manager", ar_man.name from area_manager as ar_man where ar_man.name="Amit";
-- insert into agents ( name, manager, cx, rar_1d, rar_7d, rar_28D, rdr_7d, obsr, c2f, c2f_rc, iblr, qcr, oblr, dps, AF, crw, aht, ob, utiliz, dps_adpt)
--  select 'Amit', man.name, '50', '10','3', '14', '1234', 'ffff' , 'ee', 'bb', 'ee' , 'asdsafs', 'asdsdas', '2312312321' , 'asd432423', '1232132', 'asdsfd' , '1233232', '1232143', '13453' from manager as man where man.name="Amit_manager";
-- desc agents;
-- select * from area_manager;
-- select * from manager;
-- select * from agents;

-- drop table agents;
-- drop table manager;
-- drop table area_manager;

