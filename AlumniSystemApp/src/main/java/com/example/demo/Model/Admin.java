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
   private String username;
   private String password;
   private String role;
}
