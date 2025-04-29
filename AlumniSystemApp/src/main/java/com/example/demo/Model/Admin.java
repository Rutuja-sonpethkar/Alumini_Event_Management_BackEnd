package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin
{
	 private int id;
	   private String name;
	   private String email;
	   private String contact;
	   private String role;
  
}
