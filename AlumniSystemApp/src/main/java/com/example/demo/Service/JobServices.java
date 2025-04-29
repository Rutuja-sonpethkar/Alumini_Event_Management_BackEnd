package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Job;
import com.example.demo.Reposotry.JobRepo;

import lombok.Setter;

@Service
public class JobServices 
{
   
	@Autowired
	public JobRepo jobrepo;
	
	 public boolean isAddNewJob(Job job)
	 {
		return jobrepo.isAddNewJob(job);
		 
	 }
	 public List<Job> getAllJob()
	 {
		return jobrepo.getAllJob();
		 
	 }
	 public boolean isdeleteById(int jid)
	 {
		return jobrepo.isdeleteById(jid);
		 
	 }
	 public boolean isupdatejob(Job job)
	 {
		return jobrepo.isupdatejob(job);
		 
	 }
}
