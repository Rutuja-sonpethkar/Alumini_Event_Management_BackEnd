package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Batch;
import com.example.demo.Reposotry.BatchRepo;

@Service
public class BatchService 
{
   @Autowired	
    public  BatchRepo batchrepo;
   
   public boolean isAddneBatch(Batch batch)
   {
	    
	 return batchrepo.isAddneBatch(batch);
	   
   }
   
   public List<Batch> getAllBatches()
   {
	return batchrepo.getAllBatches();
	 
	   
   }
   
   public boolean isdeletebatchbyid(int bid)
   {
	return batchrepo.isdeletebatchbyid(bid);
	   
   }
   
   public boolean isUpdateBatch(Batch batch)
   {
	return batchrepo.isUpdateBatch(batch);
	   
   }
   
}
