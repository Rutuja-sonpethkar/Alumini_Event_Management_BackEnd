package com.example.demo.Reposotry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Model.Admin;

@Repository("empRepo")
public class AdminRepostory
{
	List<Admin>list;
	@Autowired
	JdbcTemplate template;
	
	public boolean isAddNewAdmin(Admin admin)
	{
		int value=template.update("insert into admin values('0', ?,?,?,?,?)", new PreparedStatementSetter()
				{

					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						
						ps.setString(1,admin.getName());
						ps.setString(2, admin.getEmail());
						ps.setString(3, admin.getUsername());
						ps.setString(4,admin.getPassword());
						ps.setString(5, admin.getRole());
						
					}
			
				});
		return value>0?true:false;
	}
	
	
	public List<Admin>getAllAdmin()
	{
		list=template.query("select * from admin", new RowMapper<Admin>()
				{

					@Override
					public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
					Admin add=new Admin();
					add.setId(rs.getInt(1));
					add.setName(rs.getString(2));
					add.setEmail(rs.getString(3));
					add.setUsername(rs.getString(4));
					add.setPassword(rs.getString(5));
					add.setRole(rs.getString(6));
						return add;
					}
			
				});
		return list;
	}
	public Admin getAdminById(int id)
	{
		

		list=template.query("select * from admin where aid=?", new Object[] {id},new RowMapper<Admin>()
		{

			@Override
			public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
			Admin add=new Admin();
			add.setId(rs.getInt(1));
			add.setName(rs.getString(2));
			add.setEmail(rs.getString(3));
			add.setUsername(rs.getString(4));
			add.setPassword(rs.getString(5));
			add.setRole(rs.getString(6));
				return add;
			}
	
		});
     return list.size()>0?list.get(0):null;
 } 
	public boolean isdeleteAdminById(int id)
	{
		int value=template.update("delete from admin where aid=" +id);
				return value>0?true:false;
	}
	
	public boolean isUpadte(Admin admin)
	{
		int value=template.update("update admin set Admin_name=?,email=?,username=?,password=?,role=? where aid=?", new PreparedStatementSetter()
				{

					@Override
					public void setValues(PreparedStatement ps) throws SQLException
					{
						ps.setString(1, admin.getName());
						ps.setString(2, admin.getEmail());
						ps.setString(3, admin.getUsername());
						ps.setString(4, admin.getPassword());
						ps.setString(5, admin.getRole());
						ps.setInt(6, admin.getId());
						
						
					}
			
				});
		return value>0?true:false;
	}
	
}


