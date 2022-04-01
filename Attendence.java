package models;
import models.*;
import java.sql.*;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;

public class Attendence{
	private Integer attendenceId;
	private String attendence;
	private String date;
	private Student student;
	private Integer attendenceMonth;

	public Attendence(Integer attendenceId,String attendence,String date,Student student){
		this.attendenceId = attendenceId;
		this.attendence = attendence;
		this.date = date;
		this.student = student;
	}
	
	public Attendence(){
		
	}
	
	public Attendence(Student student,String attendence,String date){
		this.student = student;
		this.attendence = attendence;
		this.date = date;
	}
	
	Boolean collectMyOneDayAttendence(Integer studentId,String date){
		Boolean flag = true;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "select attendence from attendence where student_id=? and date=?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,studentId);
			ps.setString(2,date);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				String at = rs.getString(1);
				if(at.equals("a")||at.equals("p"))
					flag = false;
			}
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return flag;
	}

	public ArrayList<Attendence> collectMyAttendence(Integer studentId,Integer monthId){
		ArrayList<Attendence> list = new ArrayList<Attendence>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			
			String query = "select student_id,attendence,date from attendence where student_id=? and attendence_month=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,studentId);
			ps.setInt(2,monthId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				list.add(new Attendence(new Student(rs.getInt(1))	,rs.getString(2),rs.getString(3)));
			}
			
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return list;
	}
		
	public Boolean insertAttendence(String date,Integer studentId,Boolean flags,Integer attendenceMonth){
		
		Boolean flag = false; 
		PreparedStatement ps = null;
		String query = null;
		
		if(collectMyOneDayAttendence(studentId,date)){
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
				
				if(flags){
					query = "insert into attendence (attendence,date,student_id,attendence_month)values(?,?,?,?)";
					ps = con.prepareStatement(query);
					ps.setString(1,"p");
					ps.setString(2,date);
					ps.setInt(3,studentId);
					ps.setInt(4,attendenceMonth);
					//System.out.println("data inserted in present having month="+date);
					
				}else{
					query = "insert into attendence (attendence,date,student_id,attendence_month)values(?,?,?,?)";
					ps = con.prepareStatement(query);
					ps.setString(1,"a");
					ps.setString(2,date);
					ps.setInt(3,studentId);
					ps.setInt(4,attendenceMonth);
					//System.out.println("data inserted in absent");
				}
				ps.executeUpdate();
				
				con.close();
			}catch(SQLException|ClassNotFoundException e){
				e.printStackTrace();
			}
		}
		System.out.println("i wont insert it is already inserted");
		return flag;
	}	
	
	public Float calculatePercentage(Integer studentId){
		Integer presnt=10,totalDays=10;
		Float percentage = 10.00f;
		Float fina;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			
			String present = "select COUNT(attendence) from attendence where attendence='p' and student_id=?";
			PreparedStatement ps1 = con.prepareStatement(present);
			ps1.setInt(1,studentId);

			String count = "select COUNT(attendence) from attendence where student_id=?";
			PreparedStatement ps2 = con.prepareStatement(count);
			ps2.setInt(1,studentId);
			
			
			ResultSet rs = ps1.executeQuery();
			if(rs.next()){
				presnt = rs.getInt(1);
				System.out.println(presnt+"~~ present days");
			}
			
			ResultSet rs1 = ps2.executeQuery();
			if(rs1.next()){
				totalDays = rs1.getInt(1);
				System.out.println(totalDays+"~~ total days");
			}
			percentage = (presnt*100)/(float)totalDays;
			//String str = Float.toString((presnt*100)/(float)totalDays);	
			//String[] t = str.split(".");
			//percentage = Float.valueOf(t[0]);
			//int tem = (presnt*100);
			
			// System.out.println(presnt+"numerator * 100 bali value");
			
			// System.out.println(tem+"numerator");
			// percentage = tem/totalDays;
			// System.out.println(tem+"denotor");
			
			// System.out.println(percentage+"=percentage");
			
			//fina = tem/(Float)totalDays;
			
				
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return percentage;
	}
	
	
	public static Boolean getCurrentDate(String date,Integer studentId){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			
			String query = "select * from attendence where date=? and student_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,date);
			ps.setInt(2,studentId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				flag = true;
			}
			con.close();
			
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}	
		return flag;
	}
	
	public boolean setPresentAbsent(String dates,Integer studentId,Boolean flags,Integer month){
		Boolean flag = false;
		Date d = new Date();
		PreparedStatement ps = null;
		try{
			
			String date = d.toString();
			date = (date.subSequence(0,10)).toString();
			String date1 = Integer.toString(d.getYear()+1900);
			
			//System.out.println(date1+"year~~~~~~~~");
			Class.forName("com.mysql.jdbc.Driver");
			
			date = date+" "+date1;
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			
			if(flags){
				String query = "update attendence set attendence=?,attendence_month=? where student_id=? and date=?";
				ps = con.prepareStatement(query);
				ps.setString(1,"p");
				ps.setInt(2,month);
			
			}else{
				String query = "update attendence set attendence=?,attendence_month=? where student_id=? and date=?";
				ps = con.prepareStatement(query);
				ps.setString(1,"a");
				ps.setInt(2,month);
				
			
			}
			ps.setInt(3,studentId);
			ps.setString(4,dates);
			
			flag=true;
			ps.executeUpdate();
			con.close();
			
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public Student getStudent(){
		return student;
	}
	
	public String getDate(){
		return date;
		}
	
	public String getAttendence(){
		return attendence;
	}
	
	public Integer getAttendenceId(){
		return attendenceId;
	}
	
	public void setAttendence(String attendence){
		this.attendence = attendence;
	}
	
	public void setDate(String date){
		this.date = date;
	}
	
	public void setStudent(Student student){
		this.student = student;
	}

	public void setAttendenceMonth(Integer attendenceMonth){
		this.attendenceMonth = attendenceMonth;
	}

	public Integer getAttendenceMonth(){
		return attendenceMonth;
	}
	
	
}