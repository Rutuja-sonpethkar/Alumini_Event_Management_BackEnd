package com.example.demo.Model;

import org.springframework.boot.ansi.AnsiOutput.Enabled;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Alumni 
{
    private int sid;
    private String name;
    private String email;
    private String mobileNo;
    private String isEnablestudent;
    public int uid; 
    public int did;
    public int bid;
   
   

    
}
