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
import com.example.demo.Model.Batch;
import com.example.demo.Service.BatchService;



@RestController
@RequestMapping("/api")
@CrossOrigin("*")


public class BatchController
{
     @Autowired
	 public BatchService batchservic;
     
     
     @PostMapping("/AddBatch")
     public boolean isAddneBatch(@RequestBody Batch batch)
     {
		return batchservic.isAddneBatch(batch);
    	 
     }
     
     @GetMapping("/ViewAllBatches")
     public List<Batch> getAllBatches()
     {
    	 System.out.println("in batches controller");
    	 List<Batch>list=batchservic.getAllBatches();
    	 
    	 if(list.size()!=0)
    	 {
    		 return list;
    	 }
    	 else
    	 {
    		 throw new  AdminNotFoundException("there is no ddata in database");
    	 }
    	 
     }
     
     @DeleteMapping("deletebatchById/{bid}")
     public String isdeletebatchbyid(@PathVariable ("bid") Integer bid)
     {
    	 boolean b=batchservic.isdeletebatchbyid(bid);
    	 if(b)
    	 {
    		 return "batch Deleted";
    	 }
    	 else
    	 {
    		 throw new AdminNotFoundException("Btach not found By "+bid);
    	 }
    	 
		
    	 
     }
     
     @PutMapping("/Updatebatch")
     public String isUpdateBatch(@RequestBody Batch batch)
     {
    	 boolean b=batchservic.isUpdateBatch( batch);
    	 if(b)
    	 {
    		 return "Batch Updated" +batch;
    	 }
    	 else
    	 {
    		 throw new AdminNotFoundException("batch not found"+batch.getBid());
    	 }
     }
}
