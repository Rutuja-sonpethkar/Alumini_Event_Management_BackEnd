package com.example.demo.Model;

import org.springframework.boot.ansi.AnsiOutput.Enabled;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Alumni 
{
    private int sid;
    private String name;
    private String email;
    private String Mobile_no;
    private String isEnablestudent;
    public String uid;
    public String customOrg;
    public String did;
    public String customDept;
    public String bid;
    public String customBatch;
    
}
