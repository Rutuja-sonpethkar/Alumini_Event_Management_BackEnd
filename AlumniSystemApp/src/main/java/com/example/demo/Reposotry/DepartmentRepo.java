package com.example.demo.Reposotry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.demo.Model.Department;

@Repository
public class DepartmentRepo 
{

	
	List<Department>list;
	
	 private final JdbcTemplate template;

	    @Autowired
	    public DepartmentRepo(JdbcTemplate template) {
			this.template= template;
		}
	 
	    public boolean isAddNewDepartment(Department department)
	    {
	    	String query="insert into department values('0',?)";
	    	
	    	 int value=template.update(query, (ps)->
	    	 {
	    		 ps.setString(1, department.getDname());
	    		 
	    	 });
	    	
			return value>0?true:false;
	    	
	    }
	    
	    public List<Department> getAllDepartment()
	    {
	        String query = "SELECT * FROM department";

	        List<Department> list = template.query(query, (rs, rowNum) ->
	        {
	            Department dept = new Department();
	            
	            dept.setDid(rs.getInt("did"));
	            dept.setDname(rs.getString("dname"));
	            
	            return dept;
	        });

	        return list;
	    }
	    
	    
	    public boolean isdeletedepartmentById(int did)
	    {
	    	int value=template.update("delete from department where did="+did);
			return value>0?true:false;
	    	
	    }
	    public boolean isUpdateDepartment(Department department) {
	        String query = "UPDATE department SET dname=? WHERE did=?"; // removed extra comma

	        int value = template.update(query, ps -> {
	            ps.setString(1, department.getDname());
	            ps.setInt(2, department.getDid());
	        });

	        return value > 0;
	    }



	    
	
	 
}
