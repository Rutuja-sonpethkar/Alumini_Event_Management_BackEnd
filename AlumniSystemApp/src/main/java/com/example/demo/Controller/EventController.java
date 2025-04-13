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
import com.example.demo.Model.Alumni;
import com.example.demo.Model.Events;
import com.example.demo.Service.EventService;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

	@Autowired
	public EventService service;

	@PostMapping
	public boolean isCreateNewEvent(@RequestBody Events events) {
		
		System.out.println("Hitting  ");
		return service.isAddEvent(events);
	}
	 


	@GetMapping("/viewAllevents")
	public List<Events> getAlldata() 
	{
		System.out.println("inside view all students called");
		List<Events>list=service.getAlldata();
		if(list.size()!=0)
		{
//			System.out.println(list);
			return list;
		}
		else
		{
			throw new AdminNotFoundException("There is no data in the database");
		}
		
	}
	@DeleteMapping("deleteeventById/{eid}")
	public String isdeleteeventbyid(@PathVariable("eid") Integer eid)
	{
		boolean b=service.isdeleteeventbyid(eid);
		if(b)
		{
			return "Event Deleted";
		}
   		
		else
		{
			throw new AdminNotFoundException("Alumni not found by "+eid);
		}
		
	}
	@PutMapping("/updateEvent")
	public String upddateEvnt(@RequestBody Events event)
	{
		boolean b=service.isUpdateEvents(event);
		if(b)
		{
			return "Event Record updated With id" +event;
		}
		else
		{
			 throw new AdminNotFoundException ("Event not find using id" +event.getEid());
		}
		
		
	}

}
