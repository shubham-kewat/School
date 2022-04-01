package models;

import java.sql.*;
import java.util.*;
import java.io.*;

public class Marks{
	private Student student;
	private Integer marksId;
	private Integer science;
	private Integer hindi;
	private Integer english;
	private Integer physics;
	private Integer chemistry;
	private Integer biology;
	private Integer economy;
	private Integer commerce;
	private Integer sanskrit;
	private Float percentage;

	public Marks(Student student,Integer science,Integer hindi,Integer english,Integer physics,Integer chemistry,Integer biology,Integer economy,Integer commerce,Integer sanskrit){
		this.student = student;
		this.science = science;
		this.hindi = hindi;
		this.english = english;
		this.physics = physics;
		this.chemistry = chemistry;
		this.biology = biology;
		this.economy = economy;
		this.commerce = commerce;
		this.sanskrit = sanskrit;
	}

	public Marks(Integer science,Integer hindi,Integer english,Integer physics,Integer chemistry,Integer biology,Integer economy,Integer commerce,Integer sanskrit,Float percentage){
		this.science = science;
		this.hindi = hindi;
		this.english = english;
		this.physics = physics;
		this.chemistry = chemistry;
		this.biology = biology;
		this.economy = economy;
		this.commerce = commerce;
		this.sanskrit = sanskrit;
		this.percentage =  percentage;
	}


	public Marks(){

	}

	//-------------------- methods ---------------------------------------

	public static ArrayList<Marks> ShowMyResult(Integer studentId){
		ArrayList<Marks> marks = new ArrayList();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
		
			String query = "select science,hindi,english,physics,chemistry,biology,economy,commerce,sanskrit,percentage from marks where student_id=?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,studentId);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				marks.add(new Marks(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getInt(8),rs.getInt(9),rs.getFloat(10)));
			}

			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return marks;
	}

	public static Boolean fillAllStudentsMarks(ArrayList<Marks> marks){
		Boolean flag = false;
		System.out.println(marks.size());

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

				for(Marks m:marks){
					String query = "insert into marks(student_id,science,hindi,english,physics,chemistry,biology,economy,commerce,sanskrit,percentage)values(?,?,?,?,?,?,?,?,?,?,?)";
					PreparedStatement ps = con.prepareStatement(query);

					ps.setInt(1,m.getStudent().getStudentId());
					ps.setInt(2,m.getScience());
					ps.setInt(3,m.getHindi());
					ps.setInt(4,m.getEnglish());
					ps.setInt(5,m.getPhysics());
					ps.setInt(6,m.getChemistry());
					ps.setInt(7,m.getBiology());
					ps.setInt(8,m.getEconomy());
					ps.setInt(9,m.getCommerce());
					ps.setInt(10,m.getSanskrit());					
					
					Integer calcPercent = m.getScience()+m.getHindi()+m.getEnglish()+m.getPhysics()+m.getChemistry()+m.getBiology()+m.getEconomy()+m.getSanskrit()+m.getEconomy();
					Float percentage = (calcPercent*100)/(float)1100;
					ps.setFloat(11,percentage);

					ps.executeUpdate();
				}

			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return flag;

	}
	

	//-------------- getter setters start---------------------------------------

	public void setStudent(Student student){
		this.student = student;
	}

	public void setMarksId(Integer marksId){
		this.marksId = marksId;
	}

	public void setScience(Integer science){
		this.science = science;
	}

	public void setEnglish(Integer english){
		this.english = english;
	}

	public void setPhysics(Integer physics){
		this.physics = physics;
	}

	public void setChemistry(Integer chemistry){
		this.chemistry = chemistry;
	}

	public void setHindi(Integer hindi){
		this.hindi = hindi;
	}

	public void setBiology(Integer biology){
		this.biology = biology;
	}

	public void setEconomy(Integer economy){
		this.economy = economy;
	}

	public void setCommerce(Integer commerce){
		this.commerce = commerce;
	}

	public void setSanskrit(Integer sanskrit){
		this.sanskrit = sanskrit;
	}	

	public void setPercentage(Float percentage){
		this.percentage = percentage;
	}

	public Student getStudent(){
		return student;
	} 

	public Integer getMarksId(){
		return marksId;
	}

	public Integer getScience(){
		return science;
	}

	public Integer getHindi(){
		return hindi;
	}

	public Integer getEnglish(){
		return english;
	}

	public Integer getPhysics(){
		return physics;
	}

	public Integer getChemistry(){
		return chemistry;
	}

	public Integer getBiology(){
		return biology;
	}

	public Integer getEconomy(){
		return economy;
	}

	public Integer getCommerce(){
		return commerce;
	}

	public Integer getSanskrit(){
		return sanskrit;
	}

	public Float getPercentage(){
		return percentage;
	}

	
}