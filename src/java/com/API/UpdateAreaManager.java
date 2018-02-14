/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.API;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rizwan
 */
@Path("/updateareamanager")
public class UpdateAreaManager {
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String createtable(@FormParam("name") String name, 
            @FormParam("userType") String usertype,
            @FormParam("area") String area) {
        return "<H1>Got User " + name + "</H1> <p>" + area + "</p> <p>" + usertype + "</p>" ;
    }
    
}

