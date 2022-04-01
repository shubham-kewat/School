package models;

import java.sql.*;
import java.io.*;
import java.util.*;

public class Application{
	private Integer attendenceId;
	private String data;
	private Integer teacherId;
	private Student student;
	private String startDate;
	private String endDate;
	private String status;
	
	

	public Application(Student student,String data,String startDate,String endDate,String status,Integer applicationId){
		this.data = data;
		this.student = student;
		this.startDate = startDate;
		this.endDate = endDate;
		this.attendenceId = applicationId;
		
		System.out.print("\napplication id "+applicationId);
	}
	
	public Application(Student student,String data,String startDate,String endDate,String status){
		this.data = data;
		this.student = student;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	

	public Application(String startDate,String endDate,String data){
		this.startDate = startDate;
		this.endDate = endDate;
		this.data = data;
	}
	
	public Application(String startDate,String endDate,String data,String status){
		this.startDate = startDate;
		this.endDate = endDate;
		this.data = data;
		this.status = status;
	}

	public static void updateStatus(Integer applicationId,Boolean flag) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "update application set status=? where application_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			if(flag)
				ps.setString(1,"Approve");
			else
				ps.setString(1,"Reject");
			ps.setInt(2,applicationId);
			
			ps.executeUpdate();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}		
	}
	
	
	public static ArrayList collectMyLeave(Integer studentId){
		ArrayList<Application> applications = new ArrayList<Application>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "select start_date,end_date,reason,status from application where student_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,studentId);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				applications.add(new Application(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}

		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return applications;
	}
	
	
	public static ArrayList<Application> collectAllApplication(Integer teacherId){
		ArrayList<Application> app = new ArrayList<Application>();
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			
			String query = "select student_id,reason,start_date,end_date,status,application_id from application where teacher_id=?";//if want to add name instead of student_id then update later
			
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,teacherId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				app.add(new Application(new Student(rs.getInt(1)),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6)));		
			}
			
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return app;
	}
	
	public static Boolean applyForLeave(Integer teacherId,String reason,String startDate,String endDate,Integer studentId){
		Boolean flag = false;	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "insert into application (teacher_id,reason,start_date,end_date,student_id) values(?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setInt(1,teacherId);
			ps.setString(2,reason);
			ps.setString(3,startDate);
			ps.setString(4,endDate);
			ps.setInt(5,studentId);

			ps.executeUpdate();
			flag = true;
			
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public String getEndDate(){
		return endDate;
	}
	
	public String getStartDate(){
		return startDate;
	}
	
	public void setEndDate(String endDate){
		this.endDate = endDate;
	}
	
	public void setStartDate(String startDate){
		this.startDate = startDate;
	}
	
	public void setAttendenceId(Integer attendenceId){
		this.attendenceId = attendenceId;
	}
	
	public void setData(String data){
		this.data = data;
	}
	
	public void setTeacherId(Integer teacherId){
		this.teacherId = teacherId;
	}
	
	public void setStudent(Student studentId){
		this.student = student;
	}
	
	public Integer getTeacherId(){
		return teacherId;
	}
	
	public String getData(){
		return data;
	}
	
	public Student getStudent(){
		return student;
	}
	
	public Integer getAttendenceId(){
		return attendenceId;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}