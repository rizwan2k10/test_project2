/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connect;

/**
 *
 * @author Rizwan
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class connDB {

   public String table_agent = "agents";
   public String table_manager = "manager";
   public String table_area_manager = "area_manager";
   private Connection connection = null;
   
   public connDB()
   {
       System.out.println("Failed need Arguments");
   }
   
    /**
     *
     * @param ip
     * @param port
     * @param app_name ## MySQL application name
     * @param username # DB username
     * @param password_base64 # DB user password encoded in base64
     */
    public connDB(String ip, int port, String app_name, 
            String username, String password_base64) {

	System.out.println("-------- MySQL JDBC Connection Testing ------------");

	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return;
	}

	System.out.println("MySQL JDBC Driver Registered!");
	

	try {
                byte[] pass_bytes = Base64.getDecoder().decode(password_base64);
		connection = DriverManager
		.getConnection("jdbc:mysql://" + ip +":" + port + "/" + app_name, username , new String(pass_bytes));

	} catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return;
	}

	if (connection != null) {
		System.out.println("You made it, take control your database now!");
	} else {
		System.out.println("Failed to make connection!");
	}
        }
    
    public void disconnDB(){
        try {
            System.out.println("disconnecting....");
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("Exception on DB Connection close");
            System.out.println(e.toString());
        }
    }
    
    private boolean insertDB(String table_name, HashMap<String, String> info, String ... args) {
        for(String s: args)
        {
            System.out.println(s);
        }

        return false;
    }
    
    private boolean insertAgent(String manager, HashMap<String, String> agent_info)
    {
        String query = "insert into " + this.table_agent + " ( name. manager, cx, rar_1d, rar_7d, rar_28D, obsr, c2f, c2f_rc, iblr, qcr, oblr, dps, AF, crw, aht, ob, utiliz, dps_adpt)" +
        "select '" + agent_info.get("name") + "', man.name, '" + agent_info.get("cx") + "', '" + agent_info.get("rar_1d") + "'," +
        " '" + agent_info.get("rar_7d") + "', '" + agent_info.get("rar_28D") + "', " + agent_info.get("obsr") + "' ," +
        " '" + agent_info.get("c2f") + "', '" + agent_info.get("c2f_rc") + "', " + agent_info.get("iblr") + "' ," +
        " '" + agent_info.get("qcr") + "', '" + agent_info.get("oblr") + "', " + agent_info.get("dps") + "' ," +
        " '" + agent_info.get("AF") + "', '" + agent_info.get("crw") + "', " + agent_info.get("aht") + "' ," +
        " '" + agent_info.get("ob") + "', '" + agent_info.get("utiliz") + "', " + agent_info.get("dps_adpt") + "'" +
        " from " + this.table_manager + " as man where man.name='" + manager + "'";
        try {
            boolean rs = this.insertQuery(query);
            return rs;
        }
        catch (SQLException e) {
            System.out.println("Exception caught while inserting data into agents :");
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean insertManager(String manager, String area_manager) {
        String query = "insert into manager (name, area_manager) select '" + manager + "',aman.name from area_manager as aman where aman.name='" + area_manager + "'";
        try {
            boolean rs = this.insertQuery(query);
            return rs;
        }
        catch (SQLException e) {
            System.out.println("Exception caught while inserting data into manager :");
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public boolean insertAreaManager(String area_manager) {
        String query = "insert into " + this.table_area_manager + " (name) values ('" + area_manager + "')"; 
        try {
            boolean rs = this.insertQuery(query);
            return rs;
        }
        catch (SQLException e) {
            System.out.println("Exception caught while inserting data into Area manager : " + e.getErrorCode());
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public HashMap<String, Object> agentData(String manager, String agent_name)
    {
       List<HashMap<String, Object>>  agent_data = this.getAgentData(this.table_agent, manager, agent_name);
       HashMap<String, Object> agent = agent_data.get(0);
       return agent;
    }
    
    public ArrayList<String> getmanagers(String area_manager)
    {
        if ("".equals(area_manager) ) {
            return this.getItemNames(this.table_manager);
        }
        else {
            HashMap<String, String> manager = new HashMap<>();
            manager.put("area_manager", area_manager);
            return this.getItemNames(this.table_manager, manager);
        }
    }
    
    public ArrayList<String> getareamanagers()
    {
        return this.getItemNames(this.table_area_manager);
    }
    
    public ArrayList<String> getagents(String local_manager) {
        if ("".equals(local_manager) ) {
            return this.getItemNames(this.table_agent);
        }
        else {
            HashMap<String, String> manager = new HashMap<>();
            manager.put("manager", local_manager);
            return this.getItemNames(this.table_agent, manager);
        }
    }
    
    private List<HashMap<String, Object>> getAgentData(String table_name, String manager, String agent_name) {
        List<HashMap<String, Object>> agent_data = new ArrayList<HashMap<String, Object>>();
        ResultSet rs = null;
        try {
        String query = "select * from " + table_name + " where manager='" + manager + "' and name='" + agent_name + "'";
        System.out.println(query);
        rs = this.runQuery(query);
        agent_data = this.convertResultSetToList(rs);
        }
        catch (SQLException e) {
            System.out.println("Exception caught while getting Agent Data");
        }
        return agent_data;
    }
    
    private List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException {
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();
    List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

    while (rs.next()) {
        HashMap<String,Object> row = new HashMap<String, Object>(columns);
        for(int i=1; i<=columns; ++i) {
            if (rs.getObject(i) != null) { row.put(md.getColumnName(i),rs.getObject(i)); }
            else { row.put(md.getColumnName(i),"");}
        }
        list.add(row);
    }
    return list;
    }
    
    private void printdata(List<HashMap <String, Object>> obj) {
        for(int i=0; i< obj.size(); i++)
        {
            Map<String, Object> a = obj.get(i); 
            for(Map.Entry d:a.entrySet()){  
                System.out.println(d.getKey() +":"+ d.getValue().toString());
            }
        }
    }
    
    private void printdata(HashMap <String, Object> obj) {
        Map<String, Object> a = obj; 
        for(Map.Entry d:a.entrySet()){  
            String value;
            if (d.getValue() != null)
            { 
                value = d.getValue().toString(); 
            } 
            else 
            { 
                value = "";
            }
            System.out.println(d.getKey() +":"+ value);
        }
    }
    
    private void printdata(ArrayList<String> obj) {
        for(String s:obj) {
            System.out.println(s);
        }
    }
    
    private ArrayList getItemNames(String table) {
        ArrayList item = new ArrayList();
        ResultSet rs;
        try {
             rs = this.runQuery("select name from " + table);
             while(rs.next()) {
                item.add(rs.getString("name"));
                }
        }
        catch (SQLException e)
        {
            System.out.println("SQL Exception : " + e.getMessage());
        }
        return item;
    }
    
    private ArrayList getItemNames(String table, HashMap<String, String>  key) {
        ArrayList<String> item = new ArrayList<>();
        ResultSet rs=null;
        try {
            for(Map.Entry d:key.entrySet()){  
             rs = this.runQuery("select name from " + table + " where " + d.getKey() + "='" + d.getValue() + "'");
             break;
            }
            while(rs.next()) {
                item.add(rs.getString("name"));
            }
        }
        catch (SQLException e)
        {
            System.out.println("SQL Exception : " + e.getMessage());
        }
        return item;
    }
    
    private ResultSet runQuery(String Query) throws SQLException
    {
        try
        {
        Statement stmt = connection.createStatement();
        ResultSet result = stmt.executeQuery(Query);
        return result;
        }
        catch (SQLException e)
        {
            throw e;
        }

    }
    
    private boolean insertQuery(String Query) throws SQLException {
        Statement stmt = connection.createStatement();
        int status = stmt.executeUpdate(Query);
        System.out.println("Insert Status :" + status);
        if (status == 1) { return true; }
        return false;
    }
    
    public static void main(String args[]){
        connDB d = new connDB("localhost", 3306,
        "dell_agents?zeroDateTimeBehavior=convertToNull", "root", "MTIhUEBzczM0NQ==");
//        d.insertDB("agents", "error", "Hello", "Ara", "Next");
//        System.out.println(d.getmanagers("Rizzzo").toString());
//        d.agentData("haula", "Rizw");
        boolean status = d.insertAreaManager("haula8");
        System.out.println(status);
        d.printdata(d.getareamanagers());
        d.disconnDB();
    }
}
