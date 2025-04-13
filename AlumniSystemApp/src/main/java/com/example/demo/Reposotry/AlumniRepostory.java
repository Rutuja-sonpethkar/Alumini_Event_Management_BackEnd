package com.example.demo.Reposotry;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.domain.RowDocument;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Admin;
import com.example.demo.Model.Alumni;

@Repository("alumniRepo")
public class AlumniRepostory 
{
	
	List<Alumni>list;
	@Autowired
    JdbcTemplate template;
	
	
//	public boolean isAddNewAlumni(Alumni alumni)
//	{
//		int value=template.update("insert into alumni values('0',?,?,?,?,?,?)",new PreparedStatementSetter()
//				{
//
//					@Override
//					public void setValues(PreparedStatement ps) throws SQLException 
//					{
//						ps.setString(1, alumni.getName());
//						ps.setString(2, alumni.getEmail());
//						ps.setString(3, alumni.getNumber());
//						ps.setString(5, alumni.getIsEnablestudent());
//						ps.setInt(6, alumni.getUid());
//						ps.setInt(7, alumni.getDid());
//					}
//			
//				});
//		return value>0?true:false;
//		
//	}
	
	public void addAlumni(Alumni req) {
	    // Handle Organization
	    int orgId;
	 // Fix custom org
	    if ("Other".equals(req.uid)) {
	        String sql = "INSERT INTO organization (name, email, phone_number, is_enableEnlm) VALUES (?, '', '', 'Yes')";
	        template.update(sql, req.customOrg); // <== use customOrg
	        orgId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
	    } else {
	        orgId = template.queryForObject("SELECT uid FROM organization WHERE name = ?", Integer.class, req.uid);
	    }


	    // Handle Batch
	    int batchId;
	    if ("Other".equals(req.bid)) {
	        String sql = "INSERT INTO batches (batch_year) VALUES (?)";
	        template.update(sql, req.customBatch);
	        batchId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
	    } else {
	        String sql = "SELECT bid FROM batches WHERE batch_year = ?";
	        batchId = template.queryForObject(sql, Integer.class, req.bid);
	    }

	    // Handle Department
	    int deptId;
	    if ("Other".equals(req.did)) {
	        String sql = "INSERT INTO department (dname) VALUES (?)";
	        template.update(sql, req.customDept);
	        deptId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
	    } else {
	        String sql = "SELECT did FROM department WHERE dname = ?";
	        deptId = template.queryForObject(sql, Integer.class, req.did);
	    }

	    // Insert Alumni
	    String insertSql = "INSERT INTO alumni (name, email, Mobile_no, isEnableStudent, uid, did) VALUES (?, ?, ?, 'Yes', ?, ?)";
	    template.update(insertSql, req.getName(), req.getEmail(), req.getMobile_no(), orgId, deptId);
	}

	 
//	public List<Alumni>getAllAlumni()
//	{
//		list=template.query("select* from alumni", new RowMapper<Alumni>()
//				{
//
//					@Override
//					public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
//						Alumni al=new Alumni();
//						al.setSid(rs.getInt(1));
//						al.setName(rs.getString(2));
//						al.setEmail(rs.getString(3));
//						al.setNumber(rs.getString(4));
//						al.setIsEnablestudent(rs.getString(6));
//						al.setUid(rs.getInt(7));
//						al.setDid(rs.getInt(7));
//						return al;
//					}
//			
//				});
//		return list;
//		
//	}
//	
//	public Alumni getAlumniById(int id)
//	{
//		
//
//		list=template.query("SELECT * FROM alumni WHERE sid = ?", new Object[] {id},new RowMapper<Alumni>()
//		{
//
//			@Override
//			public Alumni mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Alumni al=new Alumni();
//			al.setSid(rs.getInt(1));
//			al.setName(rs.getString(2));
//			al.setEmail(rs.getString(3));
//			al.setNumber(rs.getString(4));
//			al.setIsEnablestudent(rs.getString(6));
//			al.setUid(rs.getInt(7));
//			al.setDid(rs.getInt(8));
//			return al;
//			}
//	
//		});
//		return list.size()>0?list.get(0):null;
//		
//	}
//	public boolean isDeleteAlumiById(int id)
//	{
//		int value=template.update("delete from Alumni where sid=" +id);
//		return value>0?true:false;
//		
//	}
//	public boolean isUpdateAlumni(Alumni alumni)
//	{
//		int  value=template.update("upddtae Alumni set sid=?,name=?,s_email=?,Mobile_no=?,isEnabledStudent=?,uid=?,did=?", new PreparedStatementSetter()
//		{
//			
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException
//			{
//				ps.setString(1, alumni.getName());
//				ps.setString(2, alumni.getEmail());
//				ps.setString(3, alumni.getNumber());
//				ps.setString(5, alumni.getIsEnablestudent());
//				ps.setInt(6, alumni.getUid());
//				ps.setInt(7, alumni.getDid());
//				ps.setInt(8, alumni.getSid());
//				
//				
//				
//			}
//		});
//		return value>0?true:false;
//		
//	}
}
