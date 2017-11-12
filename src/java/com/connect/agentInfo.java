/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect;

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
        connDB DB = new connDB("localhost", 3306,
        "dell_agents?zeroDateTimeBehavior=convertToNull", "root", "MTIhUEBzczM0NQ==");
        
        String json = new Gson().toJson(DB.agentData(manager, name));
        return json;
    }
}