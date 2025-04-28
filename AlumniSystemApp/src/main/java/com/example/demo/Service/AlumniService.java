package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Alumni;
import com.example.demo.Reposotry.AlumniRepostory;

@Service("alumniservice")
public class AlumniService
{
   @Autowired
   AlumniRepostory alumniRepo;
   
   
  
   public boolean isAddNewAlumni(Alumni alumni)
   {
	return alumniRepo.isAddNewAlumni(alumni);
	   
   }
   
  
   public List<Alumni>getAllAlumni()
   {
	return alumniRepo.getAllAlumni();
	   
   }
   
   public Alumni getAlumniById(int id)
   {
	   return alumniRepo.getAlumniById(id);
   }
   public boolean isDeleteAlumiById(int id)
   {
	return alumniRepo.isDeleteAlumiById(id);
	   
   }
   public boolean isUpdateAlumni(Alumni alumni)
   {
	return alumniRepo.isUpdateAlumni(alumni);   
   }
}
