package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Job 
{
  private int jid;
  private String title;
  private String companyname;
  private String location;
  private String jobtype;
  private String  description;
 
  private String deadline;
  private int sid;
}
