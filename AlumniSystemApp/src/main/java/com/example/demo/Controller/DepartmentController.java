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
import com.example.demo.Model.Department;
import com.example.demo.Service.DepartmentService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class DepartmentController 
{

	@Autowired
	public DepartmentService departservic;
	
	
	@PostMapping("/AddNewDepartment")
	 public boolean isAddNewDepartment( @RequestBody Department department)
	 {
		
		return departservic.isAddNewDepartment(department);
		 
	 }
	@GetMapping("/ViewAllDepartemnt")
	 public List<Department> getAllDepartment()
	 {
		System.err.println("i am in departmetn show");
		 List<Department>list=departservic.getAllDepartment();
		 if(list.size()!=0)
		 {
			 return list;
		 }
		 else
		 {
			 throw new AdminNotFoundException("there will be no data in databse");
		 }
		
		 
	 }
	
	@DeleteMapping("deleteDepartmentById/{did}")
	public String isdeletedepartmentById(@PathVariable("did") Integer did)
	{
		boolean b=departservic.isdeletedepartmentById(did);
		if(b)
		{
			return "Delete Department";
		}
		else
		{
			throw new AdminNotFoundException("Department not Found by" +did);
		}
		
	}
	
	@PutMapping("/Updatedepartment")
	 public String isUpdateDepartment(@RequestBody    Department department)
	 {
		 boolean b=departservic.isUpdateDepartment(department);
		 if(b)
		 {
			 return "Record updated With id" +department;
		 }
		 else
		 {
			  throw new AdminNotFoundException("Department not found with ID" +department.getDid());
		 }
		
		 
	 }
}
