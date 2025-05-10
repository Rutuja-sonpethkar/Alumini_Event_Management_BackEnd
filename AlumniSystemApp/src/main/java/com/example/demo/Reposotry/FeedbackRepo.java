package com.example.demo.Reposotry;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.example.demo.Model.Feedback;

@Repository
public class FeedbackRepo {

	List<Feedback> list;
	private final JdbcTemplate template;

	public FeedbackRepo(JdbcTemplate template) {
		this.template = template;
	}

//	public boolean isAddnewfeedback(Feedback feedback) {
//		String query = "INSERT INTO feedback (rating,  description, feedback_date, sid,eid) VALUES (?, ?, ?, ?,?)";
//
//		int value = template.update(query, ps -> {
//			ps.setInt(1, feedback.getRating());
//			ps.setString(2, feedback.getDescription());
//			ps.setString(3, feedback.getFeedbackDate());
//			ps.setInt(4, feedback.getSid());
//			ps.setInt(5, feedback.getEid());
//		});
//
//		return value > 0;
//	}
	
	
	//--------------------------------------
	
	
	
	 public boolean isAddnewfeedback(Feedback feedback) {
	        String checkEventQuery = "SELECT COUNT(*) FROM events WHERE eid = ?";
	        int count = template.queryForObject(checkEventQuery, Integer.class, feedback.getEid());

	        if (count == 0) return false;

	        String insertQuery = "INSERT INTO feedback (rating, description, feedback_date, sid, eid) VALUES (?, ?, ?, ?, ?)";
	        int rows = template.update(insertQuery, ps -> {
	            ps.setInt(1, feedback.getRating());
	            ps.setString(2, feedback.getDescription());
	            ps.setString(3, feedback.getFeedbackDate());
	            ps.setInt(4, feedback.getSid());
	          
	            ps.setInt(5, feedback.getEid());
	        });

	        return rows > 0;
	    }

	
	//--------------------------------------
//
//	public List<Feedback> getAllFeedback() {
//		String query = "SELECT * FROM feedback";
//
//		List<Feedback> list = template.query(query, (rs, rowNum) -> {
//
//			Feedback fd = new Feedback();
//			fd.setFid(rs.getInt(1));
//			fd.setRating(rs.getInt(2));
//			fd.setDescription(rs.getString(3));
//			fd.setFeedbackDate(rs.getString(4));
//			fd.setSid(rs.getInt(5));
//			fd.setEid(rs.getInt(6));
//
//			return fd;
//		});
//
//		return list;
//	}

	 
	 
	 //------------------------------------------------------------
	 public List<Feedback> getAllFeedback() {
		  String query = "SELECT f.fid, f.rating, f.description, f.feedback_date, a.name, " +
                  " e.eventname FROM feedback f " +
                  "JOIN alumni a ON f.sid = a.sid " +
                  "JOIN events e ON f.eid = e.eid";
		    

		    List<Feedback> list = template.query(query, (rs, rowNum) -> {
		        Feedback fd = new Feedback();
		        fd.setFid(rs.getInt(1));
		        fd.setRating(rs.getInt(2));
		        fd.setDescription(rs.getString(3));
		        fd.setFeedbackDate(rs.getString(4));
//		        fd.setSid(rs.getInt(5));
		        fd.setSname(rs.getString(5));  // student name
//		        fd.setEid(rs.getInt(7));
		        fd.setEventname(rs.getString(6));  // event name
		        
		        
		        System.err.println("----->  "+fd);
		        return fd;
		    });

		    return list;
		}


	 
	 
	public boolean isdeleteById(int fid) {
		int value = template.update("delete from feedback where fid=" + fid);
		return value > 0 ? true : false;

	}

	public boolean isupdatefeedback(Feedback feedback) {
		String query = "update feedback set rating=?,  description=?,feedback_date=?,sid =? where fid=?";

		int value = template.update(query, (ps) -> {
			ps.setInt(1, feedback.getRating());
			ps.setString(2, feedback.getDescription());
			ps.setString(3, feedback.getFeedbackDate());
			ps.setInt(4, feedback.getSid());
			ps.setInt(5, feedback.getFid());
		});
		return value > 0 ? true : false;

	}
	
//	public List<Feedback> getFeedbacksByStudentId(int sid)
//	{
//		String query=" select f.fid,f.rating,f.description,f.feedback_date,a.name from  feedback f inner join alumni a on f.sid=a.sid where a.sid=?";
//		List<Feedback> list=template.query(query, new Object[] {sid}, new RowMapper() {
//
//			@Override
//			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Feedback feedback=new Feedback();
//			feedback.setFid(rs.getInt(1));
//			feedback.setRating(rs.getInt(2));
//			feedback.setDescription(rs.getString(3));
//			feedback.setFeedbackDate(rs.getString(4));
//			feedback.setSname(rs.getString(5));
//				return feedback;
//			}
//		});
//		
//		
//		return list;
//	}
//

	public List<Feedback> getFeedbacksByStudentId(int sid) {
	    String query = "SELECT f.fid, f.rating, f.description, f.feedback_date, a.name, f.eid, e.eventname "
	                 + "FROM feedback f "
	                 + "INNER JOIN alumni a ON f.sid = a.sid "
	                 + "INNER JOIN events e ON f.eid = e.eid "
	                 + "WHERE a.sid = ?";
	    
	    List<Feedback> list = template.query(query, new Object[] {sid}, new RowMapper<Feedback>() {

	        @Override
	        public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Feedback feedback = new Feedback();
	            feedback.setFid(rs.getInt(1));
	            feedback.setRating(rs.getInt(2));
	            feedback.setDescription(rs.getString(3));
	            feedback.setFeedbackDate(rs.getString(4));
	            feedback.setSname(rs.getString(5));
//	            feedback.setEid(rs.getInt(6));  // Set the eid (event ID)
	            feedback.setEventname(rs.getString(7));
	            
	           
	            return feedback;
	        }
	    });
	    
	    return list;
	}

	
} ;