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
import com.example.demo.Model.Feedback;
import com.example.demo.Service.FeeddbackService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class FeedbackController 
{

	@Autowired
	 public FeeddbackService feedbackservice;
	
	@PostMapping("/AddFeedback")
	public boolean isAddnewfeedback(@RequestBody   Feedback feedback)
	{
		System.out.println(feedback);
//		return true;
		return feedbackservice.isAddnewfeedback(feedback);
		
	}
	@GetMapping("/viewallfeedback")
	public List<Feedback> getAllFeedback()
	{
		List<Feedback>list=feedbackservice.getAllFeedback();
		if(list.size()!=0)
		{
			return list;
		}
		else
		{
			throw new AdminNotFoundException("There is no data in database");
		}
		
		
		
	}
	@DeleteMapping("/deletefeedbybyid/{fid}")
	public String isdeleteById(@PathVariable ("fid") Integer fid)
	{
		boolean b=feedbackservice.isdeleteById(fid);
		if(b)
		{
			return "feedback delete";
		}
		else
		{
			throw new AdminNotFoundException("no data foundd by id"+fid);
		}
		
		
	}
	@PutMapping("/updatefeedback")
	public String isupdatefeedback( @RequestBody    Feedback feedback)
	{
		boolean b=feedbackservice.isupdatefeedback(feedback);
		if(b)
		{
			return "updated feedback"+feedback;
		}
		else
		{
			throw new AdminNotFoundException("feedback not updated by"+feedback.getFid());
		}
		
		
	}
}
