package com.example.demo.Reposotry;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.relational.domain.RowDocument;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Model.Admin;
import com.example.demo.Model.Alumni;

@Repository("alumniRepo")


public class AlumniRepostory 
{
	
	List<Alumni>list;
	@Autowired
    JdbcTemplate template;
	
	public boolean isAddNewAlumni(Alumni alumni) {
	    int value = template.update(
	        "insert into alumni(name, s_email, mobile_no, isEnableStudent, uid, did, bid) values(?,?,?,?,?,?,?)", 
	        new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	                ps.setString(1, alumni.getName());
	                ps.setString(2, alumni.getEmail());
	                ps.setString(3, alumni.getMobileNo());
	                ps.setString(4, alumni.getIsEnablestudent());
	                ps.setInt(5, alumni.getUid());
	                ps.setInt(6, alumni.getDid());
	                ps.setInt(7, alumni.getBid());
	            }
	        });
	    return value > 0;
	}

	
	
	 
	public List<Alumni>getAllAlumni()
	{
		list=template.query("select* from alumni", new RowMapper<Alumni>()
				{

					@Override
					public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
						Alumni al=new Alumni();
						al.setSid(rs.getInt(1));
						al.setName(rs.getString(2));
						al.setEmail(rs.getString(3));
						al.setMobileNo(rs.getString(4));
						al.setIsEnablestudent(rs.getString(5));
						al.setUid(rs.getInt(6));
						al.setDid(rs.getInt(7));
						al.setBid(rs.getInt(8));
						
						return al;
					}
			
				});
		return list;
		
	}
	
	public Alumni getAlumniById(int id)
	{
		

		list=template.query("SELECT * FROM alumni WHERE sid = ?", new Object[] {id},new RowMapper<Alumni>()
		{

			@Override
			public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
			Alumni al=new Alumni();
			al.setSid(rs.getInt(1));
			al.setName(rs.getString(2));
			al.setEmail(rs.getString(3));
			al.setMobileNo(rs.getString(4));
			al.setIsEnablestudent(rs.getString(5));
			al.setUid(rs.getInt(6));
			al.setDid(rs.getInt(7));
			al.setBid(rs.getInt(8));
		return al;
			}
	
		});
		return list.size()>0?list.get(0):null;
		
	}
	public boolean isDeleteAlumiById(int id)
	{
		int value=template.update("delete from Alumni where sid=" +id);
		return value>0?true:false;
		
	}
	public boolean isUpdateAlumni(Alumni alumni) {
	    int value = template.update(
	        "UPDATE Alumni SET name=?, s_email=?, Mobile_no=?, isEnableStudent=?, uid=?, did=?, bid=? WHERE sid=?",
	        new PreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps) throws SQLException {
	                ps.setString(1, alumni.getName());
	                ps.setString(2, alumni.getEmail());
	                ps.setString(3, alumni.getMobileNo());
	                ps.setString(4, alumni.getIsEnablestudent());  
	                ps.setInt(5, alumni.getUid());
	                ps.setInt(6, alumni.getDid());
	                ps.setInt(7, alumni.getBid()); 
	                ps.setInt(8, alumni.getSid());  
	            }
	        }
	    );
	    return value > 0;  
	}

	
	public List getAlumniByIdd(int sid) {
	    String query = "SELECT * FROM alumni WHERE sid = ?";
	    
	    List<Alumni> result = template.query(
	        query,
	        new Object[]{sid},
	         new RowMapper<>() {

				@Override
				public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
					
					Alumni a=new Alumni();
					a.setSid(rs.getInt(1));
					a.setName(rs.getString(2));
					a.setEmail(rs.getString(3));
					a.setMobileNo(rs.getString(4));
					a.setIsEnablestudent(rs.getString(5));
				
					a.setUid(rs.getInt(6));
					a.setDid(rs.getInt(7));
					a.setBid(rs.getInt(8));
					
					
					return a;
				}
	        	
	        }
	    );
	    
//	    result.forEach(e->System.out.println(e.getBid()+" "+e.getIsEnablestudent()));
	    return result;
	}




	public boolean studentlogin(String email, String mobileNo) {
		String query="select s_email, Mobile_no from alumni where s_email=? and  Mobile_no=?";
		int value=(template.query(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, email);
				ps.setString(2, mobileNo);
			}
		}, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return null;
			}
		})).size();
		
		return value>0?true:false;
	}

	
	/*
	public List getAlumniByIdd(int sid) {
		
		 String query = "SELECT * FROM alumni WHERE sid = ?";
		 
		 List<Alumni> list=template.query(query, new Object[]{sid},new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Alumni a=new Alumni();
				a.setSid(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setEmail(rs.getString(3));
				a.setMobileNo(rs.getString(4));
				a.setIsEnablestudent(rs.getString(5));
				
				System.err.println(a.getIsEnablestudent());
				return a;
			}
			 
		 });
		 
		return list;
	}
	*/


		
	
}
