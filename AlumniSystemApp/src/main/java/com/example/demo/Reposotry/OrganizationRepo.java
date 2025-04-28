package com.example.demo.Reposotry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.example.demo.Model.Organization;

@Repository
public class OrganizationRepo
{
	
	List<Organization>list;
	 private final JdbcTemplate template;

	    @Autowired
	    public OrganizationRepo(JdbcTemplate template) {
			this.template= template;
		}
	
	    public boolean isaddnewOrgnzation(Organization organization)
	    {
	    	String query="insert into organization values('0',?,?,?,?,?)";
	    	
	    	int value=template.update(query, (ps)->
	    	{
	    		ps.setString(1, organization.getName());
	    		ps.setString(2, organization.getEmail());
	    		ps.setString(3, organization.getPhone());
	    		ps.setString(4, organization.getIsEnable());
	    		ps.setInt(5, organization.getAid());
	    		
	    	});
			return value>0?true:false;
	    	
	    }
	    public List<Organization> getAllOrganizations() {
	        String query = "SELECT * FROM organization";
	        
	        List<Organization> list = template.query(query, (rs, rowNum) ->
	        {
	            Organization or = new Organization();
	            or.setUid(rs.getInt(1));
	            or.setName(rs.getString(2)); 
	            or.setEmail(rs.getString(3));
	            or.setPhone(rs.getString(4)); 
	         
	            or.setIsEnable(rs.getString(5));
	            or.setAid(rs.getInt(6));
	            return or;
	        });
	        
	        return list;
	    }

	    public boolean isdeletedById(int uid)
	    {
	    	int value=template.update("delete from organization where uid="+uid);
			return value>0?true:false;
	    	
	    }
	    public boolean isUpdateOrgnazartion(Organization organization)
	    {
	        String query = "UPDATE organization SET name=?, email=?, Phone_Number=?, is_enableEnlm=?, aid=? WHERE uid=?";
	        
	        int value = template.update(query, (ps) -> {
	            ps.setString(1, organization.getName());
	            ps.setString(2, organization.getEmail());
	            ps.setString(3, organization.getPhone());
	            ps.setString(4, organization.getIsEnable());
	            ps.setInt(5, organization.getAid());
	            ps.setInt(6, organization.getUid());  // Add this line to set 'uid'
	        });

	        return value > 0;
	    }

}
