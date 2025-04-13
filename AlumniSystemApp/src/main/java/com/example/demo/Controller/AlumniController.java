package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.AdminNotFoundException;
import com.example.demo.Model.Alumni;
import com.example.demo.Service.AlumniService;
@RestController
//@RequestMapping("/api/alumni")
@CrossOrigin("*")
public class AlumniController
{
	
	@Autowired
   AlumniService alumniservice;
   
//   @PostMapping("/createAlumni")
//   public String createAlumni(@RequestBody Alumni alumni)
//   {
//	   boolean b=alumniservice.isAddNewAlumni(alumni);
//	   if(b)
//	   {
//		   return "Alumni Added";
//	   }
//	   else
//	   {
//		   return "alumni not added";
//	   }
//	
//	   
//   }
	@PostMapping("/api/alumni/add")
	public ResponseEntity<String> addAlumni(@RequestBody Alumni alumniRequest) {
	    try {
	        alumniservice.addAlumni(alumniRequest);
	        return ResponseEntity.ok("Alumni added successfully.");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add alumni.");
	    }
	}

//   @GetMapping("/viewAllAlumni")
//   public List<Alumni> getAllAdmin()
//   {
//	   
//	 List<Alumni>list=alumniservice.getAllAlumni();
//	 if(list.size()!=0)
//	 {
//		 return list;
//	 }
//	 else
//	 {
//		   throw new AdminNotFoundException("There is no data in the database");
//	 }
//   
//   }
//   
//   @GetMapping("/serchAlumniById/{sid}")
//   public Alumni serachAlumniById(@PathVariable("sid")Integer id)
//   {
//	   Alumni al=alumniservice.getAlumniById(id);
//	   if(al!=null)
//	   {
//		   return al;
//	   }
//	   else
//	   {
//		   throw new AdminNotFoundException("Alumni not found using " +id);
//	   }
//	
//	
//	   
//   }
//   
//   @DeleteMapping("deleteAlumnibyId/{sid}")
//   public String deleteAlumniById(@PathVariable("sid") Integer id)
//   {
//	   boolean b=alumniservice.isDeleteAlumiById(id);
//	   if(b)
//	   {
//		   return "Alumni Deleted";
//	   }
//	   else
//	   {
//		   throw new AdminNotFoundException("Alumni not found by using" +id);
//	   }
//
//	   
//   }
//   
//   public String updateAlumni(@RequestBody Alumni alumni)
//   {
//	   boolean b=alumniservice.isUpdateAlumni(alumni);
//	   if( b)
//	   {
//		   return "Alumni Record Upadate with id" +alumni;
//	   }
//	   else
//	   {
//		   throw new  AdminNotFoundException("Alumni not found using id" +alumni.getSid());
//	   }
//	   
//	   
//   }
}
