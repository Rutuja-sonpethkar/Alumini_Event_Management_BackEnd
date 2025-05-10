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
public class Eventassign 
{

	private int assign_id;
	private int eid;
	private int sid;
	private String attendevent;
	private int bid;
//	private int AssignedStudentCount;
//	private Joinevents jevents;
}
