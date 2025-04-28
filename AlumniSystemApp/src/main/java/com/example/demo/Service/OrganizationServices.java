package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Organization;
import com.example.demo.Reposotry.OrganizationRepo;

@Service
public class OrganizationServices
{
     @Autowired
	 OrganizationRepo orgrepo;
     
     public boolean isaddnewOrgnzation(Organization organization)
     {
		return orgrepo.isaddnewOrgnzation(organization);
    	 
     }
     public List<Organization> getAllOrganizations()
     {
		return orgrepo.getAllOrganizations();
    	 
     }

     public boolean isdeletedById(int uid)
     {
		return orgrepo.isdeletedById(uid);
    	 
     }
     
     public boolean isUpdateOrgnazartion(Organization organization)
     {
		return orgrepo.isUpdateOrgnazartion(organization);
    	 
     }
}
