package models;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;

public class Assignment{
	// at last we will add teacher id as a foreign key so the records of a perticular teacher would be seen here
	private Integer assignmentId;
	private String assignment;
	private Klass klass;
	private Subject subject;
	private String submissionDate;

	public Assignment(){

	}

	
	public Assignment(Integer assignmentId,String assignment){
		this.assignmentId = assignmentId;
		this.assignment = assignment;
	}

	public Assignment(Klass klass,Subject subject,String assignment,String submissionDate){
		this.klass = klass;
		this.subject = subject;
		this.assignment = assignment;
		this.submissionDate = submissionDate;
	}

	public static ArrayList<Assignment> collectMyAssgnments(Integer classId){
		ArrayList<Assignment> as = new ArrayList<Assignment>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "select class_id,subject_name,assignment_data,submission_date from assignments inner join subjects where assignments.subject_id = subjects.subject_id";

			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				as.add(new Assignment(new Klass(rs.getInt(1)),new Subject(rs.getString(2)),rs.getString(3),rs.getString(4)));				
			}
			con.close();

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		
		return as;
	}



	public static boolean studentAssignment(Integer classId,String assignmentData,Integer subjectId,String submissionDate){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");	

			String query = "insert into assignments (class_id,assignment_data,subject_id,submission_date)values(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,classId);
			ps.setString(2,assignmentData);
			ps.setInt(3,subjectId);
			ps.setString(4,submissionDate);

			System.out.println(submissionDate+"####");

			ps.executeUpdate();
			flag = true;
			con.close();
			
			}catch(SQLException|ClassNotFoundException e){
				e.printStackTrace();	
			}
		return flag;
		}


	public void setAssignmentId(Integer assignmentId){
		this.assignmentId = assignmentId;
	}

	public void setAssignment(String assignment){
		this.assignment = assignment;
	}

	public void setKlass(Klass klass){
		this.klass = klass;
	}

	public Klass getKlass(){
		return klass;
	}

	public Integer getAssignmetId(){
		return assignmentId;
	}

	public String getAssignment(){
		return assignment;
	}

	public void setSubject(Subject subject){
		this.subject = subject;
	}

	public Subject getSubject(){
		return subject;
	}

	public void setSubmissionDate(String submissionDate){
		this.submissionDate = submissionDate;
	}

	public String getSubmissionDate(){
		return submissionDate;
	}
}