package com.example.demo.Reposotry;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



import com.example.demo.Model.Alumni;
import com.example.demo.Model.Eventassign;
import com.example.demo.Model.Events;
import com.example.demo.Model.Joinevents;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;

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

	@Autowired
    private JavaMailSender mailSender;
	
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
	        // Step 1: Fetch event details
	        String eventSql = "SELECT eventname, location, date FROM events WHERE eid = ?";
	        List<Joinevents> events = template.query(eventSql, new Object[]{eid}, (rs, rowNum) -> {
	            Joinevents j = new Joinevents();
	            j.setEventname(rs.getString("eventname"));
	            j.setLocation(rs.getString("location"));
	            j.setDate(rs.getString("date"));
	            return j;
	        });

	        if (events.isEmpty()) {
	            System.out.println("No event found with ID: " + eid);
	            return false;
	        }
//
	        Joinevents event = events.get(0);

	        // Step 2: Fetch alumni emails for the given batch
	        String alumniSql = "SELECT   sid, s_email, name FROM alumni WHERE bid = ?";
	        List<Map<String, Object>> alumniList = template.queryForList(alumniSql, bid);

	        if (alumniList.isEmpty()) {
	            System.out.println("No alumni found for batch ID: " + bid);
	            return false;
	        }

	        // Step 3: Construct and send emails
	        String subject = "Invitation to Event: " + event.getEventname();
	        for (Map<String, Object> alumni : alumniList) {
	        	int sid=(int)alumni.get("sid");
	            String toEmail = (String) alumni.get("s_email");
	            String name = (String) alumni.get("name");

	            String body = "Dear " + name + ",\n\n"
	                    + "You are invited to the following event:\n\n"
	                    + "Event: " + event.getEventname() + "\n"
	                    + "Location: " + event.getLocation() + "\n"
	                    + "Date: " + event.getDate() + "\n\n"
	                    + "Please visit your alumni portal for more details or to RSVP.\n\n"
	                    + "Best regards,\n"
	                    + "Alumni Relations Team";

	            sendEmail(toEmail, subject, body);
	            
	            
	            String query = "INSERT INTO eventassign (eid, sid, attendevent, bid) VALUES (?, ?, ?, ?)";

	    		int value = template.update(query, (ps) -> {
	    			ps.setInt(1,eid );
	    			ps.setInt(2,sid );
	    			ps.setString(3,"no");
	    			ps.setInt(4, bid);

	    		});
	        }

	        System.out.println("Emails sent successfully to batch ID: " + bid + " for event ID: " + eid);
	        return true;

	    } catch (Exception e) {
	        System.out.println("Error sending event email to batch: " + e.getMessage());
	        return false;
	    }
	}

	private void sendEmail(String toEmail, String subject, String body) {
	    try {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(toEmail);
	        message.setSubject(subject);
	        message.setText(body);
	        message.setFrom("rutujasonpethkar@gmail.com"); // your sender email
	        mailSender.send(message);
	    } catch (Exception e) {
	        System.out.println("Failed to send email to " + toEmail + ": " + e.getMessage());
	    }
	}

	

//	public List<Eventassign> getEventDetails() {
//	    String query = "SELECT e.eid, " +
//	                   "e.eventname, " +
//	                   "e.location, " +
//	                   "e.date, " +
//	                   "COUNT(DISTINCT ea.sid) AS assignedStudentCount " +
//	                   "FROM events e " +
//	                   "LEFT JOIN eventassign ea ON e.eid = ea.eid " +
//	                   "GROUP BY e.eid, e.eventname, e.location, e.date " +
//	                   "ORDER BY e.date";
//
//	    List<Eventassign> listt = template.query(query, (rs, rowNum) -> {
//	    	Eventassign event = new Eventassign();
//	    	 Joinevents jeve=new  Joinevents ();
//	       event.setEid(rs.getInt(1));
//	        jeve.setEventnamme(rs.getString(2));
//	        jeve.setLocation(rs.getString(3));
//	        jeve.setDate(rs.getString(4));
//	        event.setAssignedStudentCount(rs.getInt(5));
//	        event.setJevents(jeve);
//	       
//	        return event;
//	    });
//
//	    return listt;
//	}

	public List<Joinevents> geteventdetails() {
	    String query = "SELECT e.eid, " +
	                   "e.eventname, " +
	                   "e.location, " +
	                   "e.date, " +
	                   "COUNT(DISTINCT ea.sid) AS assignedStudentCount " +
	                   "FROM events e " +
	                   "LEFT JOIN eventassign ea ON e.eid = ea.eid " +
	                   "GROUP BY e.eid, e.eventname, e.location, e.date " +
	                   "ORDER BY e.date";

	    return template.query(query, (rs, rowNum) -> {
	        Joinevents event = new Joinevents();
	        event.setEid(rs.getInt("eid"));
	        event.setEventname(rs.getString("eventname"));
	        event.setLocation(rs.getString("location"));
	        event.setDate(rs.getString("date")); // treating date as string
	        event.setAssignedStudentCount(rs.getInt("assignedStudentCount"));
	        return event;
	    });
	}

//	public List getAssignedEventsByStudentId( int sid) {
//
//		String query="select e.eventname, e.location,e.date from alumni a inner join eventassign ev on a.sid=ev.sid inner join events e on e.eid=ev.eid where a.sid=?";
//		
//		List<Joinevents> list=template.query(query, new Object[] {sid}, new RowMapper() {
//
//			@Override
//			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Joinevents j=new Joinevents();
//				j.setEventname(rs.getString(1));
//				j.setLocation(rs.getString(2));
//				j.setDate(rs.getString(3));
//				return j;
//			}
//			
//		});
//		return list;
//	}
	public List<Joinevents> getAssignedEventsByStudentId(int sid) {

	    String query = "SELECT e.eid, e.eventname, e.location, e.date FROM alumni a " +
	                   "INNER JOIN eventassign ev ON a.sid = ev.sid " +
	                   "INNER JOIN events e ON e.eid = ev.eid WHERE a.sid = ?";

	    List<Joinevents> list = template.query(query, new Object[] {sid}, new RowMapper<Joinevents>() {

	        @Override
	        public Joinevents mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Joinevents j = new Joinevents();
	            j.setEid(rs.getInt(1)); // Set eid
	            j.setEventname(rs.getString(2));
	            j.setLocation(rs.getString(3));
	            j.setDate(rs.getString(4));
	            return j;
	        }

	    });
	    
	    return list;
	}


//	public boolean assignEventToStudent(int eid, int sid) {
//	    try {
//	        
//	        String query = "select bid from alumni where sid = ?";
//	        int bid = template.queryForObject(query, Integer.class, sid);
//
//	       
//	        String insertQuery = "INSERT INTO eventassign VALUES ('0', ?, ?, ?, ?)";
//	        int rows = template.update(insertQuery, (ps) -> {
//	            ps.setInt(1, eid);
//	            ps.setInt(2, sid);
//	            ps.setString(3, "no"); // attendee status
//	            ps.setInt(4, bid);
//	        });
//
//	        return rows > 0;
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return false;
//	    }
//	    
//	 
//	}

	
	public boolean assignEventToStudent(int eid, int sid) {
	    try {
	        // Step 1: Get student's batch ID, email, and name
	        String query = "SELECT bid, s_email, name FROM alumni WHERE sid = ?";
	        Map<String, Object> student = template.queryForMap(query, sid);

	        int bid = (int) student.get("bid");
	        String toEmail = (String) student.get("s_email");
	        String name = (String) student.get("name");

	        // Step 2: Get event details
	        String eventSql = "SELECT eventname, location, date FROM events WHERE eid = ?";
	        Joinevents event = template.queryForObject(eventSql, new Object[]{eid}, (rs, rowNum) -> {
	            Joinevents e = new Joinevents();
	            e.setEventname(rs.getString("eventname"));
	            e.setLocation(rs.getString("location"));
	            e.setDate(rs.getString("date"));
	            return e;
	        });

	        // Step 3: Insert into eventassign table
	        String insertQuery = "INSERT INTO eventassign (eid, sid, attendevent, bid) VALUES (?, ?, ?, ?)";
	        int rows = template.update(insertQuery, ps -> {
	            ps.setInt(1, eid);
	            ps.setInt(2, sid);
	            ps.setString(3, "no");
	            ps.setInt(4, bid);
	        });

	        // Step 4: Send email to student
	        String subject = "You're Invited: " + event.getEventname();
	        String body = "Dear " + name + ",\n\n"
	                + "You have been invited to the following event:\n\n"
	                + "Event: " + event.getEventname() + "\n"
	                + "Location: " + event.getLocation() + "\n"
	                + "Date: " + event.getDate() + "\n\n"
	                + "Please visit the alumni portal to RSVP or view more details.\n\n"
	                + "Regards,\nAlumni Relations Team";

	        sendEmail(toEmail, subject, body);

	        return rows > 0;
	    } catch (Exception e) {
	        System.out.println("Error assigning event: " + e.getMessage());
	        return false;
	    }
	}
	private void sendEmail1(String toEmail, String subject, String body) {
	    try {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(toEmail);
	        message.setSubject(subject);
	        message.setText(body);
	        message.setFrom("rutujasonpethkar@gmail.com"); // Use your verified sender
	        mailSender.send(message);
	    } catch (Exception e) {
	        System.out.println("Email failed to " + toEmail + ": " + e.getMessage());
	    }
	}
	
	
//	SELECT bid AS batch_id, GROUP_CONCAT(s_email SEPARATOR ', ') AS student_emails FROM alumni  GROUP BY bid ORDER BY bid;


	  
//	public List<Joinevents> getAssignedEventsByBatchId(int bid) {
//	    try {
//	        // Query to fetch events for all students in the specified batch
//	        String query = "SELECT  e.eventname, e.location, e.date " +
//	                       "FROM alumni a " +
//	                       "INNER JOIN eventassign ev ON a.sid = ev.sid " +
//	                       "INNER JOIN events e ON e.eid = ev.eid " +
//	                       "WHERE a.bid = ?";
//
//	        // Fetch the events for all students in the batch
//	        List<Joinevents> eventList = template.query(query, new Object[]{bid}, new RowMapper<Joinevents>() {
//	            @Override
//	            public Joinevents mapRow(ResultSet rs, int rowNum) throws SQLException {
//	                Joinevents event = new Joinevents();
//	                event.setEventname(rs.getString("eventname"));
//	                event.setLocation(rs.getString("location"));
//	                event.setDate(rs.getString("date"));
//	                return event;
//	            }
//	        });
//	        
//	        // Return the list of events
//	        return eventList;
//
//	    } catch (Exception e) {
//	        System.out.println("Error fetching events for batch: " + e.getMessage());
//	        return Collections.emptyList();  // Return an empty list in case of error
//	    }
//	}

	public List<Joinevents> getAssignedEventsByBatchId(int bid) {
	    try {
	        String query = "SELECT e.eid, e.eventname, e.location, e.date " +
	                       "FROM alumni a " +
	                       "INNER JOIN eventassign ev ON a.sid = ev.sid " +
	                       "INNER JOIN events e ON e.eid = ev.eid " +
	                       "WHERE a.bid = ?";

	        List<Joinevents> eventList = template.query(query, new Object[]{bid}, new RowMapper<Joinevents>() {
	            @Override
	            public Joinevents mapRow(ResultSet rs, int rowNum) throws SQLException {
	                Joinevents event = new Joinevents();
	                event.setEid(rs.getInt("eid")); // ✅ Important!
	                event.setEventname(rs.getString("eventname"));
	                event.setLocation(rs.getString("location"));
	                event.setDate(rs.getString("date"));
	                return event;
	            }
	        });

	        return eventList;

	    } catch (Exception e) {
	        System.out.println("Error fetching events for batch: " + e.getMessage());
	        return Collections.emptyList();
	    }
	}

	
	
	public boolean updateAttendance(int sid, int eid) {
        try {
        	System.out.println("======="+sid+"==========="+eid);
            // SQL query to update the attend status to 'yes' for the given student (sid) and event (eid)
            String query = "UPDATE eventassign SET attendevent = 'yes' WHERE sid = ? AND eid = ?";

            // Execute the update query
            int val = template.update(query, sid, eid);

            // If at least one row is affected, the update was successful
            return val > 0;

        } catch (Exception e) {
            System.out.println("Error updating attendance: " + e.getMessage());
            return false;
        }
    }
	
	
	public List<Map<String, Object>> getAvailableEventsForStudent(int sid) {
	    String sql = "SELECT e.eid, e.eventname, e.location, e.date, ea.attendevent " +
	                 "FROM events e " +
	                 "JOIN eventassign ea ON e.eid = ea.eid " +
	                 "WHERE ea.sid = ? AND ea.attendevent IN ('yes', 'no')";
	    return template.queryForList(sql, sid);
	}



	public boolean cancelEventRegistration(int eid, int sid) {
        String sql = "UPDATE eventassign SET attendevent = 'no' WHERE eid = ? AND sid = ?";
        int rows = template.update(sql, eid, sid);
        return rows > 0;
    }
	
	
	public List<Joinevents> getEventAttendance() {
	    String sql = """
	        SELECT 
	            e.eid,
	            e.eventname,
	            e.location,
	            e.date,
	            a.sid,
	            a.name AS studentname,
	            CASE 
	                WHEN LOWER(TRIM(ea.attendevent)) IN ('yes', 'yse') THEN 'Present'
	                ELSE 'Absent'
	            END AS attendevent
	        FROM 
	            eventassign ea
	        JOIN 
	            alumni a ON ea.sid = a.sid
	        JOIN 
	            events e ON ea.eid = e.eid
	        """;

	    return template.query(sql, (rs, rowNum) -> {
	        Joinevents je = new Joinevents();
	        je.setEid(rs.getInt("eid"));
	        je.setEventname(rs.getString("eventname"));
	        je.setLocation(rs.getString("location"));
	        je.setDate(rs.getString("date"));
	        je.setSid(rs.getInt("sid"));
	        je.setStudentname(rs.getString("studentname"));
	        je.setAttendevent(rs.getString("attendevent"));
	        return je;
	    });
	}

}
 