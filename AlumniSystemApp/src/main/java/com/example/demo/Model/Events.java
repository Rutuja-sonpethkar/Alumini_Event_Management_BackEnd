package com.example.demo.Model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Events {

	private int eid;
	private String name;
	private String location;
	public Events(String name, String location, String date) {
		super();
		this.name = name;
		this.location = location;
		this.date = date;
	}
	private String date;
	
	
}
