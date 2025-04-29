package com.example.demo.Reposotry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.demo.Model.Batch;
import com.example.demo.Model.Events;

@Repository("batchrepo")
public class BatchRepo 
{
	List<Batch>list;
	
	 @Autowired
	public JdbcTemplate template;
	 
	 public boolean isAddneBatch(Batch batch)
	 {
		 
		 String query="insert into batches (batch_year) values (?)";
		 
		 int value=template.update(query, (ps)->
				 {

			        ps.setString(1,batch.getBatchyear());
					  
				 });
		return value>0?true:false;
		 
	 }
	 
	 public List<Batch> getAllBatches()
	 {
		    String query = "SELECT * FROM batches";  

		    List<Batch> list = template.query(query, (rs, rowNum) -> {
		        Batch bs = new Batch();
		         bs.setBid(rs.getInt(1));
		        bs.setBatchyear(rs.getString(2));
		        return bs;
		    });

		    return list;
		}

	 public boolean isdeletebatchbyid(int bid)
		{
			int value=template.update("delete from batches where bid=" +bid);
			
			return value>0?true:false;
		}
	 
	 public boolean isUpdateBatch( Batch batch) {
		    String sql = "UPDATE batches SET batch_year = ? WHERE bid = ?";
		    
		    int value = template.update(sql, ps -> {
		        ps.setString(1, batch.getBatchyear()); 
		        ps.setInt(2, batch.getBid());
		      
		    });
		    
		    return value > 0;
		}

		   
		

}
