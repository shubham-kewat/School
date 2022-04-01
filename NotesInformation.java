package models;

import java.sql.*;
import java.util.*;
import java.io.*;


public class NotesInformation{
	private Integer notesInformationId;
	private Subject subject;
	private Teacher teacher;
	private Klass klass;
	private String notesPath;
//----------------------------- sonstructor ------------------------------------

	public NotesInformation(){

	}

	public NotesInformation(Subject subject,Teacher teacher,Klass klass,String notesPath){
		this.subject = subject;
		this.teacher = teacher;
		this.klass = klass;
		this.notesPath = notesPath; 
	}

	public NotesInformation(Teacher teacher,Subject subject){
		this.subject = subject;
		this.teacher = teacher;
	}	
	
//---------------------------- methods ------------------------------------------------

	public static ArrayList<NotesInformation> collectAllNotes( Integer classId){
		ArrayList<NotesInformation> notesInfo = new ArrayList<NotesInformation>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "select teacher_name,subject_name from notes_informations as n inner join ";
			String query1 = "teachers as t inner join subjects as s where n.teacher_id=t.teacher_id and n.subject_id=s.subject_id and n.class_id=?";	

			PreparedStatement ps = con.prepareStatement(query+query1);
			ps.setInt(1,classId);

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				notesInfo.add(new NotesInformation(new Teacher(rs.getString(1)),new Subject(rs.getString(2))));
			}
			con.close();

		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return notesInfo;
	}

//------------------------------- getter setter -------------------------------
	public String getNotesPath(){
		return notesPath;
	}

	public Klass getKlass(){
		return klass;
	}

	public Teacher getTeacher(){
		return teacher;
	}

	public Subject getSubject(){
		return subject;
	}

	public Integer getNotesInformationId(){
		return notesInformationId;
	}

	public void setNotesPath(String notesPath){
		this.notesPath = notesPath;
	}

	public void setKlass(Klass klass){
		this.klass = klass;
	}

	public void setTeacherId(Teacher teacher){
		this.teacher = teacher;
	}

	public void setSubjectId(Subject subject){
		this.subject = subject;
	}

	public void setNotesInformationId(Integer notesInformationId){
		this.notesInformationId = notesInformationId;
	}
}
