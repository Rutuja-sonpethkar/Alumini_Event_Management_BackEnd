package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Events;
import com.example.demo.Reposotry.EventRepo;

@Service
public class EventService {

	@Autowired
	public EventRepo eventRepo;

	public boolean isAddEvent(Events events) 
	{
		return eventRepo.isAddEvent(events);
	}

	public List<Events> getAlldata() 
	{
		return eventRepo.getAlldata();
		
	}
	public boolean isdeleteeventbyid(int eid)
	{
		return eventRepo.isdeleteeventbyid(eid);
		
	}
	public boolean isUpdateEvents(Events events)
	{
		return eventRepo.isUpdateEvents(events);
		
	}
}
