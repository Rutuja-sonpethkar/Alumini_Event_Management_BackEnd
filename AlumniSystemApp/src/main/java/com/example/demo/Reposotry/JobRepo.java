package com.example.demo.Reposotry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Job;
import java.util.*;




@Repository
public class JobRepo
{

	List<Job>list;
	private final JdbcTemplate template;
	
	
	  @Autowired
	  public JobRepo(JdbcTemplate template) 
	  {
			this.template= template;
		}
	 
	  public boolean isAddNewJob(Job job) {
		    String query = "INSERT INTO Job (title, company_name, location, job_type, description, deadline, sid) VALUES (?, ?, ?, ?, ?, ?, ?)";

		    int value = template.update(query, ps -> {
		        ps.setString(1, job.getTitle());
		        ps.setString(2, job.getCompanyname());
		        ps.setString(3, job.getLocation());
		        ps.setString(4, job.getJobtype());
		        ps.setString(5, job.getDescription());
		        ps.setString(6, job.getDeadline());
		        ps.setInt(7, job.getSid());
		    });

		    return value > 0;
		}

	  public List<Job> getAllJob() 
	  {
		    String query = "SELECT * FROM Job";

		    List<Job> list = template.query(query, (rs, rowNum) -> {
		        Job job = new Job();
		        job.setJid(rs.getInt(1)); 
		        job.setTitle(rs.getString(2));
		        job.setCompanyname(rs.getString(3));
		        job.setLocation(rs.getString(4));
		        job.setJobtype(rs.getString(5));
		        job.setDescription(rs.getString(6));
		        job.setDeadline(rs.getString(7));
		        job.setSid(rs.getInt(8));
		        
		        return job;
		    });

		    return list;
		}
	  
	  public boolean isdeleteById(int jid)
	  {
		  int value=template.update("delete from Job where jid="+jid);
		return value>0?true:false;
		  
	  }
	  public boolean isupdatejob(Job job) {
		    String query = "UPDATE Job SET title=?, company_name=?, location=?, job_type=?, description=?, deadline=?, sid=? WHERE jid=?";

		    int value = template.update(query, ps -> {
		        ps.setString(1, job.getTitle());
		        ps.setString(2, job.getCompanyname());
		        ps.setString(3, job.getLocation());
		        ps.setString(4, job.getJobtype());
		        ps.setString(5, job.getDescription());
		        ps.setString(6, job.getDeadline());
		        ps.setInt(7, job.getSid());
		        ps.setInt(8, job.getJid()); // This goes in WHERE clause
		    });

		    return value > 0;
		}



}
