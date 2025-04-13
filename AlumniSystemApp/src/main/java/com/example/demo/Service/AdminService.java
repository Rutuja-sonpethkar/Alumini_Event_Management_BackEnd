package com.example.demo.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Model.Admin;
import com.example.demo.Reposotry.AdminRepostory;

import jakarta.websocket.server.ServerEndpoint;

@Service("empService")
public class AdminService 
{

	@Autowired
	AdminRepostory adminRepo;
	
	public boolean isAddNewAdmin(Admin admin)
	{
		return adminRepo.isAddNewAdmin(admin);
	}
	
	public List<Admin>getAllAdmin()
	{
		return adminRepo.getAllAdmin();
				
	}
	public Admin getAdminById(int id)
	{
		return adminRepo.getAdminById(id);
	}
	public boolean isdeleteAdminById(int id)
	{
		return adminRepo.isdeleteAdminById(id);
	}
	public boolean isUpadte(Admin admin)
	{
		return adminRepo.isUpadte(admin);
	}
}
