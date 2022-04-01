package models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Teacher{
	private Integer teacherId;
	private String teacherName;
	private String address;
	private Integer age;
	private Gender gender;
	private Subject subject;
	private String email;
	private String contact;
	private String school;
	private String leavingCertificate;
	private Integer experience;
	private City city;
	private Klass klass;
	private String photo;
	private String qualification;
	private String character;
	private String imagePath;
	private String twelth;

	
	public Teacher(String teacherName,String address,Integer age,Gender gender,Subject subject,String email,String contact,String school,String leavingCertificate,Integer experience,City city,Klass klass){
		this.teacherName = teacherName;
		this.address = address;
		this.age = age;
		this.gender = gender;
		this.subject = subject;
		this.email = email;
		this.contact = contact;
		this.school = school;
		this.leavingCertificate = leavingCertificate;
		this.experience = experience;
		this.city = city;
		this.klass = klass;
	}

	public Teacher(Integer teacherId){
		this.teacherId = teacherId;
	}

	public Teacher(Integer teacherId,String teacherName,Integer age,Gender gender,Subject subject,String address,String email,String contact,Integer experience,String school,City city,Klass klass){
		
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.address = address;
		this.age = age;
		this.gender = gender;
		this.subject = subject;
		this.email = email;
		this.contact = contact;
		this.school = school;
		//this.leavingCertificate = leavingCertificate;
		this.experience = experience;
		this.city = city;
		this.klass = klass;
	}

	public Teacher(){
	
	}

	public Teacher(String teacherName){
		this.teacherName = teacherName;
	}
	
	public Teacher(Integer teacherId,String teacherName){
		this.teacherId = teacherId;
		this.teacherName = teacherName;
	}

	public static Integer getTeacherId(int teacherId){
		int tId = 0;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			PreparedStatement ps = con.prepareStatement("select teacher_id from teachers where class_id=?");
			ps.setInt(1,teacherId);

			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				tId = rs.getInt(1);	
			}

			con.close();
		
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return tId;
	}

	public String getImagePath(String name){									 //instead of name change as id same in Student
		String value = null	;
		String[] arr;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "select image_path from teachers where teacher_name=?";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1,name);

			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				value = rs.getString(1);
				arr = value.split("data");
				value = arr[1];
			}
		
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}

		return value;
	}
	
	
	public Boolean updateDocuments(String photo,String character,String twelth,String qualification,String name){
		Boolean flag = false;
		try{Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "update teachers set image_path=?,l_certificate=?,twelth=?,qualification=? where teacher_name=?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,photo);
			ps.setString(2,character);
			ps.setString(3,twelth);
			ps.setString(4,qualification);
			ps.setString(5,name);

			ps.executeUpdate();
			flag = true;

			con.close();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();	
		}

		return flag;
		
	}

	//overrided method

	public static Teacher collectTeacherInfo(Integer teacherId,String email){
		Teacher teacher = new Teacher();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "select teacher_id,teacher_name,age,gender,subject,address,email,contact,experience,school,city_id,class_id from teachers where teacher_id=? and email=?";

			PreparedStatement ps = con.prepareStatement(query); 
			ps.setInt(1,teacherId);
			ps.setString(2,email);
			
			ResultSet rs = ps.executeQuery();
	
			
		if(rs.next()){
				teacher = new Teacher(rs.getInt(1),rs.getString(2),rs.getInt(3),new Gender(rs.getInt(4)),new Subject(rs.getInt(5)),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10),new City(rs.getInt(11)),new Klass(rs.getInt(12)));
			}
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return teacher;
	}
	


	public static Teacher collectTeacherInfo(Integer teacherId){
		Teacher teacher = new Teacher();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "select teacher_id,teacher_name,age,gender,subject,address,email,contact,experience,school,city_id,class_id from teachers where teacher_id=?";

			PreparedStatement ps = con.prepareStatement(query); 
			ps.setInt(1,teacherId);
			ResultSet rs = ps.executeQuery();
	
			
		if(rs.next()){
				teacher = new Teacher(rs.getInt(1),rs.getString(2),rs.getInt(3),new Gender(rs.getInt(4)),new Subject(rs.getInt(5)),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10),new City(rs.getInt(11)),new Klass(rs.getInt(12)));
			}
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return teacher;
	}
	
	public static ArrayList collectTeachers(){
		ArrayList<Teacher> teachers = new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
		
			String query = "select teacher_id,teacher_name from teachers";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
			//	System.out.println(rs.getInt(1)+"--"+rs.getString(2));
				teachers.add(new Teacher(rs.getInt(1),rs.getString(2)));
			}
			con.close();
		}catch(SQLException|ClassNotFoundException e){
		
		}
		return teachers;
	}

	public Teacher(Integer teacherId,String teacherName,String imagePath){
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.imagePath = imagePath;
	}
	
	public static Teacher checkExist(Integer teacherId,String email){
		Boolean flag = false;
		Teacher t = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			//String query = "select teacher_id,teacher_name,image_path from teachers where teacher_id=? and email=?";
			String query = "select teacher_id,teacher_name from teachers where teacher_id=? and email=?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,teacherId);
			ps.setString(2,email);

			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				//System.out.println(rs.getString(1));	
				//t = new Teacher(rs.getInt(1),rs.getString(2),rs.getString(3));
				t = new Teacher(rs.getInt(1),rs.getString(2));
				flag = true;
			}
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}

		return t;
	}
	//i am dropping application id column in teacher table in database
	public Boolean addInfo(){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "insert into teachers(teacher_name,age,gender,subject,address,email,contact,experience,school,l_certificate,city_id,class_id,login_id)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,teacherName);
			ps.setInt(2,age);
			ps.setInt(3,gender.getGenderId());
			ps.setInt(4,subject.getSubjectId());
			ps.setString(5,address);
			ps.setString(6,email);
			ps.setString(7,contact);
			ps.setInt(8,experience);
			ps.setString(9,school);
			ps.setString(10,leavingCertificate);
			ps.setInt(11,city.getCityId());
			ps.setInt(12,klass.getClassId());
			ps.setInt(13,age);

			File file = new File("C:/Tomcat 8.0/webapps/school/data/teacher",teacherName);
			file.mkdir();
			File info= new File("C:/Tomcat 8.0/webapps/school/data/teacher/"+teacherName,"information.txt");

			File photo = new File("C:/Tomcat 8.0/webapps/school/data/teacher/"+teacherName+"/document/photo");
			
			File character = new File("C:/Tomcat 8.0/webapps/school/data/teacher/"+teacherName+"/document/character");
			
			File qualification = new File("C:/Tomcat 8.0/webapps/school/data/teacher/"+teacherName+"/document/qualification");
			
			File twelth = new File("C:/Tomcat 8.0/webapps/school/data/teacher/"+teacherName+"/document/twelth");

			photo.mkdirs();
			character.mkdirs();
			qualification.mkdirs();
			twelth.mkdirs();

			info.createNewFile();

			BufferedWriter bw = new BufferedWriter(new FileWriter(info));

			bw.write("name="+teacherName);
			bw.newLine();
			bw.write("age="+age);
			bw.newLine();
			bw.write("subject-teach="+subject.getSubjectId());
			bw.newLine();
			bw.write("address="+address);
			bw.newLine();
			bw.write("email="+email);
			bw.newLine();
			bw.write("contact="+contact);
			bw.newLine();
			bw.write("school="+school);
			bw.newLine();
			bw.write("city="+city.getCityId());
			bw.newLine();
			bw.write("class="+klass.getClassId());

			bw.flush();
			bw.close();



			ps.executeUpdate();
			flag = true;
			
			con.close();
		}catch(SQLException|ClassNotFoundException e){
				e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}


			return flag;
	}

	public Boolean updateProfile(Integer teacherId, String updated){
		Boolean flag = false;
		try{
			System.out.println("~~~~~~~~~~~~~~~~inside method");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "update teachers set image_path=? where teacher_id=?";

			PreparedStatement ps = con.prepareStatement(query);
			System.out.println("teacher id=~~~~~~~~~~"+teacherId);
			System.out.println("profile~~~~~~~~~~~~~ "+updated);
			
//			ps.setString(1,updated);
			ps.setString(1,"C:\\Tomcat 8.0\\webapps\\school\\data\\teacher\\varsha\\document\\character");
			
			//adding image path mannually
			ps.setInt(2,teacherId);


			ps.executeUpdate();
			flag=true;

			con.close();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return flag;
	}


	public static String findSubjectNameById(Integer subjectId){
		String subjectName=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "select subject_name from subjects where subject_id =?";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,subjectId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				subjectName=rs.getString(1);
			}
			con.close();	
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}

		return subjectName;
	}

	public static Boolean uploadNotesPath(Integer classId,Integer teacherId,Integer subjectId,String filePath){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "insert into notes_informations (class_id,teacher_id,subject_id,notes_path)values(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query); 

			ps.setInt(1,classId);
			ps.setInt(2,teacherId);
			ps.setInt(3,subjectId);
			ps.setString(4,filePath);

			ps.executeUpdate();
			flag = true;

			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return flag;
	}


	//---------------------------- getter setter -------------------------------

	public Integer getExperience(){
		return experience;
	}
	
	public String getLeavingCertificate(){ 
		return leavingCertificate;
	}
	
	public String school(){
		return school;
	}
	
	public String contact(){
		return contact;
	}
	
	public String getEmail(){
		return email;
	}
	
	public Subject getSubject(){
		return subject;
	}
	
	public Gender getGender(){
		return gender;
	}
	
	public Integer getAge(){
		return age;
	}
	
	public String address(){
		return address;
	}
	
	public String getTeacherName(){
		return teacherName;		
	}
	
	public Integer getTeacherId(){
		return teacherId;
	}

	public City getCity(){
		return city;
	}

	public void setExperience(Integer experience){
		this.experience = experience;
	}
	
	public void setLeavingCertificate(String leavingCertificate){
		this.leavingCertificate = leavingCertificate;
	}

	public void serOldSchool(String school){
		this.school = school;
	}

	public void setContact(String contact){
		this.contact = contact;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setSubject(Subject subject){
		this.subject = subject;
	}

	public void setTeacherId(Integer teacherId){
		this.teacherId = teacherId;	
	}

	public void setTeacherName(String teacherName){
		this.teacherName = teacherName;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public void setAge(Integer age){
		this.age = age;
	}

	public void setGender(Gender gender){
		this.gender = gender;	
	}

	public void setCity(City city){
		this.city = city;
	}

	public void setClass(Klass klass){
		this.klass = klass;
	}

	public Klass getKlass(){
		return klass;
	}

	
	public String getImagePath(){
		return imagePath;
	}

	public String getQualification(){
		return qualification;
	}

	public String getTwelth(){
		return twelth;
	}

	public String getCharacter(){
		return character;
	}
	
	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}

	public void setQualification(String qualification){
		this.qualification = qualification;
	}

	public void setTwelth(String twelth){
		this.twelth = twelth;
	}

	public void setCharacter(String character){
		this.character = character;
	}
}																				

// select n.class_id,n.teacher_id,teacher_name,n.subject_id,subject_name from notes_informations 
// as n inner join teachers as t inner join subjects as s where n.teacher_id=t.teacher_id and 
// n.subject_id=s.subject_id;