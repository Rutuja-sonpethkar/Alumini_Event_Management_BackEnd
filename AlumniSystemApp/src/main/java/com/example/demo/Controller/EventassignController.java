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
import com.example.demo.Model.Eventassign;
import com.example.demo.Model.Joinevents;
import com.example.demo.Service.EventassignService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class EventassignController {

	@Autowired
	public EventassignService service;

	@PostMapping("/assignEventToBatch/{eid}/{bid}")
	public boolean assignEventToBatch(@PathVariable Integer eid, @PathVariable Integer bid) {
		System.out.println("Batch Id : " + bid + "\t" + "Event Id :" + eid);
		return service.assignEventToBatch(eid, bid);
	}
	
	@PostMapping("/assigenEventToStudent/{eid}/{sid}")
	public boolean assigneventToStudent(@PathVariable Integer eid, @PathVariable Integer sid)
	{
		System.out.println("stduent Id:" +sid+ "\t" +"Event Id:" +eid);
		return service.assignEventToStudent(eid, sid);
	}
	
	
	@GetMapping("/viewassigneventdata")
	public List<Eventassign> getEventDetails() 
	{
		List<Eventassign>listt=service.getEventDetails();
		if(listt.size()!=0)
		{
			return listt;
		}
		else
		{
			throw new AdminNotFoundException("there is no data in database");
		}
		
		
	}

	@PostMapping("/addassignevent")
	public boolean isAssignnewEvent(@RequestBody Eventassign eventassign) {
		System.out.println(eventassign.getBid() + "/" + eventassign.getEid());
		return service.isAssignnewEvent(eventassign);

	}
    
	 
	@GetMapping("/viewAllassignevents")
	public List<Eventassign> getAllAssignedEventData() {
		List<Eventassign> list = service.getAllAssignedEventData();
		if (list.size() != 0) {
			return list;
		}

		else {
			throw new AdminNotFoundException("there is no data in database");
		}

	}

	@DeleteMapping("/deleteassignevntbyId/{assign_id}")
	public String isdeleteassignevntById(@PathVariable("assign_id") Integer assign_id) {
		boolean b = service.isdeleteassignevntById(assign_id);
		if (b) {
			return "delete assignevnt";
		}

		else {
			throw new AdminNotFoundException("record not found by id" + assign_id);
		}

	}

	@PutMapping("/updetedAssignevent")
	public String isupdateassignevnt(@RequestBody Eventassign eventassign) {
		boolean b = service.isupdateassignevnt(eventassign);
		if (b) {
			return "assign event updated with" + eventassign;
		} else {
			throw new AdminNotFoundException("Assigne event not foun by id" + eventassign.getAssign_id());
		}

	}

}
