/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  rizwan
 * Created: Nov 1, 2017
 */

Drop Database dell_agents;
CREATE DATABASE IF NOT EXISTS dell_agents;
USE dell_agents;
Create table area_manager(name VARCHAR(26) NOT NULL PRIMARY KEY);
Create table manager(name VARCHAR(26) NOT NULL PRIMARY KEY, area_manager VARCHAR(20), FOREIGN KEY (area_manager) REFERENCES area_manager(name));
Create table agents(name VARCHAR(26), manager VARCHAR(26), FOREIGN KEY (manager) REFERENCES manager(name), cx VARCHAR(10), rar_1d VARCHAR(10), rar_7d VARCHAR(10), rar_28d VARCHAR(10), rdr_7d VARCHAR(10), obsr VARCHAR(10), c2f VARCHAR(20), c2f_rc VARCHAR(20), iblr VARCHAR(10), qcr VARCHAR(10), oblr VARCHAR(10), dps VARCHAR(10), AF VARCHAR(20), crw VARCHAR(20), aht VARCHAR(10), ob VARCHAR(10), utiliz VARCHAR(10), dps_adpt VARCHAR(10));

-- desc agents;
-- select * from area_manager;
-- select * from manager;
-- select * from agents;

-- drop table agents;
-- drop table manager;
-- drop table area_manager;

