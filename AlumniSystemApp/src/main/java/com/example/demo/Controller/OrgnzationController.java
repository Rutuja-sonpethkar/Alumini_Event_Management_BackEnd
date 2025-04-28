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
import com.example.demo.Model.Organization;
import com.example.demo.Service.OrganizationServices;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class OrgnzationController {
	@Autowired
	OrganizationServices orgservice;

	@PostMapping("/addnewOrgnazation")
	public boolean isaddnewOrgnzation(@RequestBody Organization organization) {
		System.out.println(organization);
//		return true;
		return orgservice.isaddnewOrgnzation(organization);

	}

	@GetMapping("/viewAllOrgnzation")
	public List<Organization> getAllOrganizations() {
		List<Organization> list = orgservice.getAllOrganizations();
		list.forEach((l) -> System.out.println(l));
		if (list.size() != 0) {
			return list;
		} else {
			throw new AdminNotFoundException("there is no data in database");
		}

	}

	@DeleteMapping("/DeleteorgnazationByid/{uid}")
	public String isdeletedById(@PathVariable("uid") Integer uid) {
		boolean b = orgservice.isdeletedById(uid);
		if (b) {
			return "Orgnazation delete";
		} else {
			throw new AdminNotFoundException("there is no data foun by" + uid);
		}

	}

	@PutMapping("/Updateorgnaztion")
	public String isUpdateOrgnazartion(@RequestBody Organization organization) {
		boolean b = orgservice.isUpdateOrgnazartion(organization);
		if (b) {

			return "Record updated With id" + organization;
		} else {
			throw new AdminNotFoundException("Orgnazation not found using uid" + organization.getUid());
		}

	}

}
