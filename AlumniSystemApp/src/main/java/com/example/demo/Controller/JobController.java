package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.AdminNotFoundException;
import com.example.demo.Model.Job;
import com.example.demo.Service.JobServices;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class JobController
{
    @Autowired
	public JobServices jobservice;
    
    @PostMapping("/AddJob")
    public boolean isAddNewJob(@RequestBody Job job)
    {
		return jobservice.isAddNewJob(job);
    	
    }
    @GetMapping("/ViewAllJob")
	 public List<Job> getAllJob()
	{
		 List<Job>list=jobservice.getAllJob();
		 if(list.size()!=0)
		 {
			 return list;
		 }
		 else
		 {
			 throw new AdminNotFoundException("There is no data in the database");
		 }
		
		
	}
    @DeleteMapping("/deletebyid/{jid}")
    public String isdeleteById(@PathVariable("jid") Integer jid)
    {
    	boolean b=jobservice.isdeleteById(jid);
    	if(b)
    	{
    	  return "Job Delete";
    	}
    			
    	else
    	{
    	   throw new AdminNotFoundException("job not found by id"+jid);
    	}
    }
    
    @PutMapping("/updatejob")
    public String isupdatejob(@RequestBody  Job job)
    
    {
    	boolean b=jobservice.isupdatejob(job);
    	if(b)
    	{
    		return "record update with id"+job;
    	}
    			
    	else
    	{
    		throw new AdminNotFoundException("job not foun by id"+job.getJid());
    	}
		
    	
    }
}
