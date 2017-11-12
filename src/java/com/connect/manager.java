/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect;

import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author rizwan
 */
@Path("/manager")
public class manager {
        @GET 
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public String list_manager(@QueryParam("name") String name) {
        connDB DB = new connDB("localhost", 3306,
        "dell_agents?zeroDateTimeBehavior=convertToNull", "root", "MTIhUEBzczM0NQ==");
        
        String json = new Gson().toJson(DB.getmanagers(name));
        return json;
    }
}
/** 
 * //    @GET
 * //    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.TEXT_HTML)
//    public String list_manager(@QueryParam("name") String name) {
//        connDB DB = new connDB("localhost", 3306,
//        "dell_agents?zeroDateTimeBehavior=convertToNull", "root", "MTIhUEBzczM0NQ==");
//        String html_output = "<div> <select id=\"managers\">";
//        html_output += "<option>Manager</option>";
//        for(String managers:DB.getmanagers(name)) {
//            html_output += "<option>" + managers + "</option>";
//        }
//        html_output += "</select></div>";
//        return html_output;
//    }
    
    * //    private String jsonString(String name) {
* //        
* //    }
* //    private String jsonString(List lt) {
* //        for ()
* //        return 
* //    }
**/    

/**
 * //        String json_output = "{ \"manager\" : ";
 * //        for(String managers:DB.getmanagers(name)) {
 * //            html_output += ", " + managers + "</option>";
 * //        }    
 * //        for (String manager : DB.getmanagers(name)) {
 * //            Logger.getLogger(manager.class.getName()).log(Level.INFO, manager );
 * //            try {
 * //                json_output.;
 * //            } catch (JSONException ex) {
 * //                Logger.getLogger(manager.class.getName()).log(Level.SEVERE, null, ex);
 * //            }
 * //        }
 * //        json_output += DB.getmanagers(name).toString();
 * //        json_output += " }";
**/
    


