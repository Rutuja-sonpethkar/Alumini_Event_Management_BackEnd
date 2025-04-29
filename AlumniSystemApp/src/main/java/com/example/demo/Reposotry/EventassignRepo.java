package com.example.demo.Reposotry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;

import com.example.demo.Model.Alumni;
import com.example.demo.Model.Eventassign;
import com.example.demo.Model.Joinevents;

@Repository
public class EventassignRepo
{

	List<Eventassign>list;
	List<Joinevents>listt;
	private final JdbcTemplate template;

	@Autowired
	public EventassignRepo(JdbcTemplate template) {
		this.template = template;
	}

	public boolean isAssignnewEvent(Eventassign eventassign) {
		String query = "INSERT INTO eventassign (eid, sid, attendevent, bid) VALUES (?, ?, ?, ?)";

		int value = template.update(query, (ps) -> {
			ps.setInt(1, eventassign.getEid());
			ps.setInt(2, eventassign.getSid());
			ps.setString(3, eventassign.getAttendevent());
			ps.setInt(4, eventassign.getBid());

		});
		return value > 0 ? true : false;

	}

	public List<Eventassign> getAllAssignedEventData()

	{
		String query = "SELECT * FROM eventassign";

		List<Eventassign> list = template.query(query, (rs, rowNum) -> {
			Eventassign ea = new Eventassign();

			ea.setAssign_id(rs.getInt(1));
			ea.setEid(rs.getInt(2));
			ea.setSid(rs.getInt(3));
			ea.setAttendevent(rs.getString(4));
			ea.setBid(rs.getInt(5));

			return ea;
		});

		return list;
	}

	public boolean isdeleteassignevntById(int assign_id) {

		int value = template.update("delete from eventassign where assign_id=" + assign_id);
		return value > 0 ? true : false;

	}

	public boolean isupdateassignevnt(Eventassign eventassign) {
		String query = "update eventassign set eid=?, sid=?, attendevent=? , bid=? where assign_id=?";

		int value = template.update(query, (ps) -> {
			ps.setInt(1, eventassign.getEid());
			ps.setInt(2, eventassign.getSid());
			ps.setString(3, eventassign.getAttendevent());
			ps.setInt(4, eventassign.getBid());
			ps.setInt(5, eventassign.getAssign_id());

		});

		return value > 0;
	}

	public boolean assignEventToBatch(int eid, int bid) {
		try {

			String query = "select sid from Alumni where bid =?";
			List<Integer> alumniIdList = template.query(query, (rs, rowNum) -> rs.getInt("sid"), bid);

			/*
			 * mysql> select *from eventassign;
			 * +-----------+------+------+-------------+------+ | assign_id | eid | sid |
			 * attendevent | bid | +-----------+------+------+-------------+------+ | 3 | 1
			 * | 19 | yse | 4 | +-----------+------+------+-------------+------+
			 */
			for (int sid : alumniIdList) {
				template.update("insert into eventassign values('0',?,?,?,? )", (ps) -> {

					ps.setInt(1, eid);
					ps.setInt(2, sid);
					ps.setString(3, "no");
					ps.setInt(4, bid);

				});
			}

			System.out.println(alumniIdList);
			return true;
		} catch (Exception e) {
			System.out.println("Exception is " + e);
			return false;
		}

	}
	
	public boolean assignEventToStudent(int eid, int sid) {
	    try {
	        
	        String query = "select bid from alumni where sid = ?";
	        int bid = template.queryForObject(query, Integer.class, sid);

	       
	        String insertQuery = "INSERT INTO eventassign VALUES ('0', ?, ?, ?, ?)";
	        int rows = template.update(insertQuery, (ps) -> {
	            ps.setInt(1, eid);
	            ps.setInt(2, sid);
	            ps.setString(3, "no"); // attendee status
	            ps.setInt(4, bid);
	        });

	        return rows > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public List<Eventassign> getEventDetails() {
	    String query = "SELECT e.eid, " +
	                   "e.eventname, " +
	                   "e.location, " +
	                   "e.date, " +
	                   "COUNT(DISTINCT ea.sid) AS assignedStudentCount " +
	                   "FROM events e " +
	                   "LEFT JOIN eventassign ea ON e.eid = ea.eid " +
	                   "GROUP BY e.eid, e.eventname, e.location, e.date " +
	                   "ORDER BY e.date";

	    List<Eventassign> listt = template.query(query, (rs, rowNum) -> {
	    	Eventassign event = new Eventassign();
	    	 Joinevents jeve=new  Joinevents ();
	       event.setEid(rs.getInt(1));
	        jeve.setEventnamme(rs.getString(2));
	        jeve.setLocation(rs.getString(3));
	        jeve.setDate(rs.getString(4));
	        event.setAssignedStudentCount(rs.getInt(5));
	        event.setJevents(jeve);
	       
	        return event;
	    });

	    return listt;
	}


}
