package com.mingcai.edu.common.utils;

import nl.justobjects.pushlet.core.Event;
import nl.justobjects.pushlet.core.EventPullSource;

import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MyPushSource implements Serializable {
	//连接数据库
	public static Connection getMySQLConnection() throws Exception {  
        Class.forName("com.mysql.jdbc.Driver");  
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mingcai-oa", "root", "root");
        return conn;  
    }
	public static class MySource1 extends EventPullSource {
		@Override
		protected long getSleepTime() {
			return 2000;
		}
		@Override
		protected Event pullEvent() {
			Event event = Event.createDataEvent("myevent1");
			try {
				Map<String,Integer> map=counts();
				for(String key:map.keySet()){
					//System.out.println(key+":"+ map.get(key));
					event.setField(key, map.get(key));
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return event;
		}
	}
	public static Map<String,Integer> counts() throws Exception{
		 Map<String,Integer> map = new HashMap<String,Integer>();
		 	Connection conn = getMySQLConnection();  
	        Statement stmt = conn.createStatement();  
	        ResultSet rs = stmt.executeQuery("SELECT count(*) as c,a.task_to_user_id as b FROM oa_product_task a  where a.task_status =0 group by a.task_to_user_id" );
	        try {
	        	while (rs.next()) {
					map.put("key"+rs.getString("b"),rs.getInt("c"));
		        }
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				try {
						rs.close();
					  stmt.close();  
				        conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
	         
		return map;
	}
}