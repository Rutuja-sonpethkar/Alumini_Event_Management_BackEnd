package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Department;
import com.example.demo.Reposotry.DepartmentRepo;

@Service
public class DepartmentService
{
   
	@Autowired
	public DepartmentRepo departrepo;
	
	
	 public boolean isAddNewDepartment(Department department)
	 {
		return departrepo.isAddNewDepartment(department);
		 
	 }
	 public List<Department> getAllDepartment()
	 {
		return departrepo.getAllDepartment();
		 
	 }
	 
	 public boolean isdeletedepartmentById(int did)
	 {
		return departrepo.isdeletedepartmentById(did);
		 
	 }
	 public boolean isUpdateDepartment(Department department)
	 {
		return departrepo.isUpdateDepartment(department);
		 
	 }
}
