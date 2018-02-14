/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.API;

import com.connect.connDB;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author root
 */
@Path("/agentInfo")
public class agentInfo {
    @GET 
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String list_manager(@QueryParam("manager") String manager, @QueryParam("name") String name) {
        DB_config db_config = new DB_config();
        connDB DB = new connDB(db_config.db_ip, db_config.db_port,
        db_config.application, db_config.user, db_config.passwd);
        String json = new Gson().toJson(DB.agentData(manager, name));
        return json;
    }
}