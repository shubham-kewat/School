package models;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class Gender{
	private Integer genderId;
	private String gender;

	public Gender(Integer genderId,String gender){
		this.genderId = genderId;
		this.gender = gender;
	}

	public Gender(String gender){
		this.gender = gender;
	}
	
	public Gender(Integer genderId){
		this.genderId = genderId;
	}
	
	public Gender(){
		
	}
	
	public void setGenderId(Integer genderId){
		this.genderId = genderId;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public Integer getGenderId(){
		return genderId;
	}
}
