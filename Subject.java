package models;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;

import java.sql.*;
import java.util.ArrayList;

public class Subject{
	private Integer subjectId;
	private String subjectName;

	public Subject(Integer subjectId,String subjectName){
		this.subjectId = subjectId;
		this.subjectName = subjectName;
	}

	public Subject(){
		
	}

	public Subject(Integer subjectId){
		this.subjectId = subjectId;
	}
	
	public Subject(String subjectName){
		this.subjectName = subjectName;
	}

	public static ArrayList collectSubjects(){
		ArrayList<Subject> subjects = new ArrayList();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "select subject_id,subject_name from subjects";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				//System.out.println(rs.getInt(1)+"--"+rs.getString(2));
				subjects.add(new Subject(rs.getInt(1),rs.getString(2)));
			}

			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return subjects;
	}
	
	
	public void setSubjectId(Integer subjectId){
		this.subjectId = subjectId;
	}

	public void setSubjectName(String subjectName){
		this.subjectName = subjectName;
	}

	public String getSubjectName(){
		return subjectName;
	}

	public Integer getSubjectId(){
		return subjectId;
	}
}
