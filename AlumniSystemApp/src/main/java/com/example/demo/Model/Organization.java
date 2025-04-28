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
public class Organization
{

	private int uid;
	private String name;
	private String email;
	private String phone;
	private String isEnable;
	private int aid;
	/*
	 * public String getIsEnable() { return isEnable; } public void
	 * setIsEnable(String isEnable) { this.isEnable = isEnable; }
	 */
}
