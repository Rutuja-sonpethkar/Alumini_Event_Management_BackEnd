package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Feedback;
import com.example.demo.Reposotry.FeedbackRepo;

@Service
public class FeeddbackService 
{
   
	@Autowired
	public FeedbackRepo feedbackRepo;
	
	public boolean isAddnewfeedback(Feedback feedback)
	{
		return feedbackRepo.isAddnewfeedback(feedback);
		
	}
	public List<Feedback> getAllFeedback()
	{
		return feedbackRepo.getAllFeedback();
		
	}
	public boolean isdeleteById(int fid)
	{
		return feedbackRepo.isdeleteById(fid);
		
	}
	public boolean isupdatefeedback(Feedback feedback)
	{
		return feedbackRepo.isupdatefeedback(feedback);
		
		
	}
}
