
package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class Feedback 
{
   private int fid;
   private  int rating;
   private String description;
   private String feedbackDate;
   private int sid;
   private String sname;
   @JsonProperty("eventId")
   private Integer eid;
   private String eventname;
   
}
