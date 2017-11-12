/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect;

import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Encoded;
import javax.ws.rs.FormParam;

/**
 *
 * @author root
 */
@Path("/areamanager")
//@Encoded
public class welcome {
//  @GET  
//  @Produces(MediaType.TEXT_PLAIN)  
//  public String sayPlainTextHello() {  
//    return "<td>Sumith</td><td>12344</td><td>545454</td><td>wwwww</td>rdrd<td>bcbcbc</td>"
//            + "<td>helloe</td>"
//            + "<td>WOW</td><td>okay</td><td>essesy</td><td></td>"
//            + "<td>323232</td><td>32323</td><td>3232</td>"
//            + "<td>333333</td><td>eeeeee</td><td>11111111</td><td>2222</td><td>3232</td>";  
//  }
//    @GET
//    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getdata(@QueryParam("name") String name) {
//        return "Hello " + name;
//    }
//  
//  @GET
//  @Produces(MediaType.APPLICATION_JSON)
//  public String outputData() {
//      return "{ 'field1': 'value1', 'feild2': 'value2' }";
//  }
    /**
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_HTML)
    public String createtable() {
        connDB DB = new connDB("localhost", 3306,
        "dell_agents?zeroDateTimeBehavior=convertToNull", "root", "MTIhUEBzczM0NQ==");
        String html_output = "<div> <select id=\"area-managers\" onselect=\"alert(this.value)\">";
        html_output += "<option>Area Manager</option>";
        for(String amanagers:DB.getareamanagers()) {
            html_output += "<option>" + amanagers + "</option>";
        }
        html_output += "</select></div>";
        return html_output;
    }
    **/
    
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String createjson() {
        connDB DB = new connDB("localhost", 3306,
        "dell_agents?zeroDateTimeBehavior=convertToNull", "root", "MTIhUEBzczM0NQ==");
        String json_output = new Gson().toJson(DB.getareamanagers());
        return json_output;
    }
    
}

