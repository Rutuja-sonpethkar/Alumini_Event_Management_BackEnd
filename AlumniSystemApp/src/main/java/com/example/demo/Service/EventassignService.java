package com.example.demo.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Eventassign;
import com.example.demo.Model.Joinevents;
import com.example.demo.Reposotry.EventassignRepo;

@Service
public class EventassignService {

	@Autowired
	public EventassignRepo assignrepo;

	public boolean isAssignnewEvent(Eventassign eventassign) {
		return assignrepo.isAssignnewEvent(eventassign);

	}

	public List<Eventassign> getAllAssignedEventData() {
		return assignrepo.getAllAssignedEventData();

	}

	public boolean isdeleteassignevntById(int assign_id) {
		return assignrepo.isdeleteassignevntById(assign_id);

	}

	public boolean isupdateassignevnt(Eventassign eventassign) {
		return assignrepo.isupdateassignevnt(eventassign);

	}

	public boolean assignEventToBatch(int eid, int bid) {
		return assignrepo.assignEventToBatch(eid, bid);
	}
	
	public boolean assignEventToStudent(int eid, int sid)
	{
		return assignrepo.assignEventToStudent(eid, sid);
		
		
	}
//	public List<Eventassign> getEventDetails() 
//	{
//		
//		System.out.println("==================>"+assignrepo.getEventDetails().toString());
//		return assignrepo.getEventDetails();
//		
//	}
	
	public List<Joinevents> geteventdetails() 
	{
		return assignrepo.geteventdetails();
		
	}
	public List getAssignedEventsByStudentId( int sid)
	{
		return assignrepo.getAssignedEventsByStudentId(  sid);
		
	}
	public List<Map<String, Object>> getAvailableEventsForStudent(int sid) {
        return assignrepo.getAvailableEventsForStudent(sid);
    }
	
	
	
	public List<Joinevents> getAssignedEventsByBatchId(int bid)
	{
		return assignrepo.getAssignedEventsByBatchId(bid);
		
	}
	
	
	public boolean applyForEvent(int sid, int eid) {
        return assignrepo.updateAttendance(sid, eid);
    }

	  public boolean cancelEventRegistration(int eid, int sid) {
	        return assignrepo.cancelEventRegistration(eid, sid);
	    }
	  
	  public List<Joinevents> getEventAttendance() {
	        return assignrepo.getEventAttendance();
	  }
	
}
