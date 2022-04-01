package models;

import java.sql.*;
import java.util.*;
import java.io.*;

public class Complaint{
	private Integer complaintId;
	private String complaint;
	private Student student;
	
	public Complaint(Integer complaintId,String complaint,Student student){
		this.complaintId = complaintId;
		this.complaint = complaint;
		this.student = student;
	}

	public Complaint(Integer complaintId,String complaint){
		this.complaintId = complaintId;
		this.complaint = complaint;
	}
	
	public Complaint(){
		
	}
	
	//overloaded version of method comtains one argument
	public static ArrayList collectAllComplaints(Integer studentId){
		ArrayList<Complaint> comp = new ArrayList<Complaint>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			
			String query = "select complaint_id,complaint from complaints where student_id=?";	
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,studentId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				comp.add(new Complaint(rs.getInt(1),rs.getString(2)));
			}
			
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return comp;
	}

	public static ArrayList collectAllComplaints(){
		ArrayList<Complaint> comp = new ArrayList<Complaint>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			
			String query = "select complaint_id,complaint,student_id from complaints";	
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				comp.add(new Complaint(rs.getInt(1),rs.getString(2),new Student(rs.getInt(3))));
			}
			
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return comp;
	}
	
	public Boolean setComplaint(Integer studentId,String complaint){
			Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			
			String query = "insert into complaints (student_id,complaint)values(?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,studentId);
			ps.setString(2,complaint);
			
			ps.executeUpdate();
			flag = true;
			
			con.close();
			
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public Student getStudent(){
		return student;
	}
	
	public String getComplaint(){
		return complaint;
	}
	
	public Integer getComplaintId(){
		return complaintId;
	}
	
	public void setComplaintId(Integer complaintId){
		this.complaintId = complaintId;
	}
	
	public void setComplaint(String complaint){
		this.complaint = complaint;
	} 
	
	public void setStudent(Student student){
		this.student = student;
	}
	
}