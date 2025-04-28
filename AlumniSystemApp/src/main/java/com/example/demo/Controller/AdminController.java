package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.example.demo.Exception.AdminNotFoundException;
import com.example.demo.Exception.Errormessage;
import com.example.demo.Model.Admin;
import com.example.demo.Service.AdminService;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class AdminController 
{
	@Autowired
	AdminService adminService;
	
	@PostMapping("/createAdmin")
    public String createAdmin(@RequestBody Admin admin)
    {
		boolean b=adminService.isAddNewAdmin(admin);
		if(b)
		{
			return "Admin Added";
		}
		else
		{
			return "Admin not added";
		}
		
		
    }
	@GetMapping("/viewAllAdmin")
	public List<Admin>getAllAdmin()
	{
		List<Admin>list=adminService.getAllAdmin();
		if(list.size()!=0)
		{
			  return list;
		}
		else
		{
			 throw new AdminNotFoundException("There is no data in the database");
		}
	}
	//Search Admin Api 
	@GetMapping("/serchAdminById/{aid}")
	public Admin serchAdminById(@PathVariable("aid") Integer id)
	{
		Admin add=adminService.getAdminById(id);
		if(add!=null)
		{
			return add;
		}
		else
		{
		   throw new AdminNotFoundException("Admin not found using "+id);
		}
	}
	@DeleteMapping("/deletebyId/{aid}")
	public String deleteAdminById(@PathVariable("aid") Integer id)
	{
		boolean b=adminService.isdeleteAdminById(id);
		if(b)
		{
			return "Admin Deleted";
		}
		throw new AdminNotFoundException("Admin not found by using "+id);
	}
	
	@PutMapping("/updateAdmin")
	public String updateEmployee(@RequestBody Admin admin)
	{
		boolean b=adminService.isUpadte(admin);
		if(b)
		{
			return "Admin Record Update with id" +admin;
		}
		else
		{
			throw new AdminNotFoundException("Admin not found using id" +admin.getId());
			
		}
		
	}
}
