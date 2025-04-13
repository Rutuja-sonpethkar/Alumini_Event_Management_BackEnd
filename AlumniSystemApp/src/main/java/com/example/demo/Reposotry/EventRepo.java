package com.example.demo.Reposotry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;


import com.example.demo.Model.Events;

@Repository
public class EventRepo {

	
	private final JdbcTemplate template;


	List<Events>list;
 	@Autowired
	public EventRepo(JdbcTemplate template) {
		this.template = template;
	}
	
	public boolean isAddEvent(Events events) {
		String query = "insert into events values('0',?,?,?)";

//		int val = template.update(query, (ps) -> new Events(events.getName(), events.getLocation(), events.getDate()));

		int val = template.update(query, (ps) -> {
			ps.setString(1, events.getName());
			ps.setString(2, events.getLocation());
			ps.setString(3, events.getDate());
		});
		return val > 0 ? true : false;
	}
	
	public List<Events> getAlldata() 
	{
	    String query = "SELECT * FROM events";   

	    List<Events> list = template.query(query, (rs, rowNum) -> 
	    {
	        Events e = new Events();
	       e.setEid(rs.getInt(1));
	       e.setName(rs.getString(2));
	       e.setLocation(rs.getString(3));
	       e.setDate(rs.getString(4));
	        return e;
	    });

	    return list;
	}
 
	
	public boolean isdeleteeventbyid(int eid)
	{
		int value=template.update("delete from events where eid="+eid);
		
		return value>0?true:false;
	}
	
	public boolean isUpdateEvents(Events events)
	{
           
		 String query = "UPDATE events SET eventname = ?, location = ?, date = ? WHERE eid = ?";
		int value=template.update(query, (ps)->
		{ 
			ps.setString(1,events.getName());
			ps.setString(2, events.getLocation());
			ps.setString(3, events.getDate());
			ps.setInt(4, events.getEid());
			
		});
		return value>0?true:false;
		
	}

}
