package models;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Date;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;
import java.sql.Statement;

public class Student{
	private Integer studentId;
	private String studentName;
	private String fatherName;
	private String motherName;
	private Integer age;
	private String dob;
	private String address;
	private Subject subject;
	private String contact;
	private String email;
	private Caste caste;
	private Gender gender;
	private String fatherOccupation;
	private String motherOccupation;
	private String admissionClass;
	private String fee;
	private Integer admissionTest;
	private City city;
	private State state;
	private String password;
	private String tcPath;
	private String marksheetPath; 
	private Klass klass;
	private String birth;
	private String photo;
	private String imagePath;

	
	public Student(){
		
	}

	public Student(String studentName){
		this.studentName = studentName;
	}

	//this is a constructor
	public Student(Integer studentId,String studentName,Klass klass){
		this.studentId = studentId;
		this.studentName = studentName;
		this.klass = klass;
	}

	public Student(String studentName,String fatherName,String motherName,String address,Caste caste,Gender gender,City city,String fatherOccupation,String motherOccupation,String email,String contact,Klass klass,String password,String tcPath,String marksheetPath,Integer age){
		this.studentName = studentName;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.address = address;
		this.gender = gender;
		this.caste = caste;
		this.city = city;
		this.fatherOccupation = fatherOccupation;
		this.motherOccupation = motherOccupation;
		this.email = email;
		this.contact = contact;
		this.klass = klass;
		this.password =password;
		this.tcPath = tcPath;
		this.marksheetPath = marksheetPath;
		this.age = age;
		}
	
	
	public Student(String studentName,String fatherName,String motherName,String address,Caste caste,Gender gender,City city,String fatherOccupation,String motherOccupation,String email,String contact,Klass klass,String password,String tcPath,String marksheetPath){
		this.studentName = studentName;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.address = address;
		this.gender = gender;
		this.caste = caste;
		this.city = city;
		this.fatherOccupation = fatherOccupation;
		this.motherOccupation = motherOccupation;
		this.email = email;
		this.contact = contact;
		this.klass = klass;
		this.password =password;
		this.tcPath = tcPath;
		this.marksheetPath = marksheetPath;
		}
		
		public Student(String studentName,String fatherName,String motherName,String address,Caste caste,Gender gender,City city,String fatherOccupation,String motherOccupation,String email,String contact,Klass klass,Integer studentId,String password,String imagePath,String birth,int i){
			this.studentName = studentName;
			this.fatherName = fatherName;
			this.motherName = motherName;
			this.address = address;
			this.gender = gender;
			this.caste = caste;
			this.city = city;
			this.fatherOccupation = fatherOccupation;
			this.motherOccupation = motherOccupation;
			this.email = email;
			this.contact = contact;
			this.klass = klass;
			this.studentId = studentId;
			this.password = password;
			this.imagePath = imagePath;
			this.birth = birth;
			this.age = i;
			
		}
	
		public Student(Integer studentId){
			this.studentId = studentId;	
		}

		public static Boolean updatePassword(Integer studentId,String password){
			Boolean flag = false;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

				String query = "update students set password=? where student_id=?";
				PreparedStatement ps = con.prepareStatement(query);

				ps.setString(1,password);
				ps.setInt(2,studentId);

				ps.executeUpdate();
				flag = true;
			}catch(SQLException|ClassNotFoundException e){
				e.printStackTrace();
			}	
			return flag;
		}


	/*
		*using this method we are updating the image and documents path so when user login he will get
		*his images		
	*/
	
	public boolean updateDocuments(String photo,String birth,String tenth,String tc,String studentName){
			Boolean flag = false;
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

				String query = "update students set image_path=?,birth=?,marksheet=?,tc=? where student_name=?";
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setString(1,photo);
				ps.setString(2,birth);
				ps.setString(3,tenth);
				ps.setString(4,tc);
				ps.setString(5,studentName);

				ps.executeUpdate();
				flag = true;

				con.close();

			}catch(SQLException|ClassNotFoundException e){
				e.printStackTrace();	
			}

			return flag;
		}
		
	/*
		this method is use for user login to confirm that such 
		user exists
	*/
	public Student confirmation(String email,String password){
		Student student = null;
		try{
		   
		   Class.forName("com.mysql.jdbc.Driver");
		   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
		   
		   String query = "select student_name,father_name,mother_name,address,cast_id,gender,city_id,father_work,mother_work,email,contact,class_id,student_id,password,image_path,birth,age,student_id from students where email=? and password=?";
		   PreparedStatement ps = con.prepareStatement(query);
		 
		   ps.setString(1,email);
		   ps.setString(2,password);

		   ResultSet rs = ps.executeQuery();
		   
		   if(rs.next()){
		   student = new Student(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),new Caste(rs.getInt(5)),new Gender(rs.getInt(6)),new City(rs.getInt(7)),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),new Klass(rs.getInt(12)),rs.getInt(13),password,rs.getString(15),rs.getString(16),rs.getInt(17));
		   }//else condition remaining

		 //  System.out.println(studentName);
		   con.close();

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();	
		}	

		return student;
	}

	/*
		here we are getting the image path to show in student dashboard
	*/
	public String getImagePath(String name){
		String value = null;
		String[] sp;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "select image_path from students where student_name=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,name);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				value = rs.getString(1);
			}
			
			con.close();
			sp = value.split("data");
			value=sp[1];
			
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return value;
	}
	
	/*
		here we are saving the student information in the database along with this we are
		makin the folder with student name along with writing file which contains all 
		the information related to the student along with photoes present
	*/
	public boolean saveInfo(){
		boolean flag=false;
		Integer remainingFee = null;
		Calendar c = Calendar.getInstance();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "insert into students(student_name,father_name,mother_name,address,gender,cast_id,city_id,father_work,mother_work,email,contact,class_id,password,tc,marksheet,age)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			ps.setString(1,studentName);
			ps.setString(2,fatherName);
			ps.setString(3,motherName);
			ps.setString(4,address);
			ps.setInt(5,gender.getGenderId());
			ps.setInt(6,caste.getCasteId());
			//ps.setInt(7,state.getStateId());
			ps.setInt(7,city.getCityId());
			ps.setString(8,fatherOccupation);
			ps.setString(9,motherOccupation);
			ps.setString(10,email);
			ps.setString(11,contact);
			ps.setInt(12,klass.getClassId());
			ps.setString(13,password);
			ps.setString(14,"not available work here later");
			ps.setString(15,"not available work here later");
			ps.setInt(16,age);

			File f = new File("C:/Tomcat 8.0/webapps/school/data/student",studentName);
			File birth = new File("C:/Tomcat 8.0/webapps/school/data/student/"+studentName+"/document","birth");
			File photo = new File("C:/Tomcat 8.0/webapps/school/data/student/"+studentName+"/document","photo");
			File tc = new File("C:/Tomcat 8.0/webapps/school/data/student/"+studentName+"/document","tc");
			File tenth = new File("C:/Tomcat 8.0/webapps/school/data/student/"+studentName+"/document","tenth");

			f.mkdir();

			birth.mkdirs();
			photo.mkdirs();
			tc.mkdirs();
			tenth.mkdirs();

			File details = new File("C:/Tomcat 8.0/webapps/school/data/student/"+studentName,"informations.txt"); 
			details.createNewFile();

			BufferedWriter bw = new BufferedWriter(new FileWriter(details));
			bw.write("name="+studentName);
			bw.newLine();
			bw.write("fatherName="+fatherName);
			bw.newLine();
			bw.write("email="+email);
			bw.newLine();
			bw.write("contact="+contact);
			bw.newLine();
			bw.write("motherName="+motherName);
			bw.newLine();
			bw.write("address="+address);
			bw.newLine();
			bw.write("password="+password);
			bw.newLine();
			bw.write("cityId="+city.getCityId());
			bw.newLine();
			bw.write("casteId="+caste.getCasteId());
			bw.newLine();
			bw.write("klass="+klass.getClassId());

			bw.flush();
			bw.close();

			if(ps.executeUpdate()==1){
				
				ResultSet rs = ps.getGeneratedKeys();		
				if(rs.next()){
					//System.out.println("the key generated is%%%%%="+rs.getInt(1));
					Integer _generatedKey = rs.getInt(1);
					
					String query1 = "insert into attendence(student_id,attendence_month)values(?,?)";
					PreparedStatement statement = con.prepareStatement(query1);
					
					
					statement.setInt(1,_generatedKey);
					statement.setInt(2,c.get(Calendar.MONTH)+1);  
					
					statement.executeUpdate();

					String query2 = "insert into fees(student_id)values(?)";
					PreparedStatement fee = con.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
					fee.setInt(1,_generatedKey);
					
					if(fee.executeUpdate()==1){
						ResultSet feeKey = fee.getGeneratedKeys();
						if(feeKey.next()){
							Integer feeGeneratedKey = feeKey.getInt(1);
							Integer classId = klass.getClassId();

							if(classId==1)
								remainingFee = 2000;
							else if(classId==2)
								remainingFee = 4000;
							else if(classId==3)
								remainingFee = 5500;
							else if(classId==4)
								remainingFee = 6000;
							else if(classId==5)
								remainingFee = 6500;
							else if(classId==6)
								remainingFee = 7000;
							else if(classId==7)
								remainingFee = 8000;
							else if(classId==8)
								remainingFee = 9000;
							else if(classId==9)
								remainingFee = 10000;
							else if(classId==10)
								remainingFee = 11000;
							else if(classId==11)
								remainingFee = 12000;
							else
								remainingFee=14000;

							String feeQuery = "insert into fee_status (fee_id,remaining_fee) values(?,?)";
							PreparedStatement feeStatement = con.prepareStatement(feeQuery);
							
							feeStatement.setInt(1,feeGeneratedKey);
							feeStatement.setInt(2,remainingFee);							
							
							
							feeStatement.executeUpdate();
						}
					}
					
				}
				flag=true;
			}
			con.close();
			}
			catch(SQLException|ClassNotFoundException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
				
				return flag;
			}
	
	/*
		here we are collecting those students who belong's to the same class 
		or class Id
	*/
	public static ArrayList collectStudents(Integer classId){
			ArrayList<Student> students = new ArrayList();
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
				String query = "select student_id,student_name,class_id from students where class_id=?";

				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1,classId);
				ResultSet rs = ps.executeQuery();

				while(rs.next()){
					students.add(new Student(rs.getInt(1),rs.getString(2),new Klass(rs.getInt(3))));
				}
				con.close();
			}catch(SQLException|ClassNotFoundException e){
				e.printStackTrace();
			}
			return students;
	}


	public Boolean updateProfileStudent(Integer studentId, String updated){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "update students set image_path=? where student_id=?";

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,updated);
			ps.setInt(2,studentId);


			ps.executeUpdate();
			flag=true;

			con.close();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return flag;
	}
	

	public State getState(){
		return state;
	}
	
	public City getCity(){
		return city;
	}
	
	public Integer getAdmissionTest(){
		return admissionTest;
	}
	
	public String getFee(){
		return fee;
	}
	
	public String getAdmissionClass(){
		return admissionClass;
	}
	
	public String getMotherOccupation(){
		return motherOccupation;
	}
	
	public String getFatherOccupation(){
		return fatherOccupation;
	}
	
	public Gender getGender(){
		return gender;
	}
	
	public Caste getCaste(){
		return caste;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getMobileNo(){
		return contact;
	}
	
	public Subject getSubject(){
		return subject;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getDob(){
		return dob;
	}
	
	public Integer getAge(){
		return age;
	}
	
	public String getFatherName(){
		return fatherName;
	}
	
	public String getStudentName(){
		return studentName;
	}
	
	public Integer getStudentId(){
		return studentId;
	}
	
	public void setState(State state){
		this.state = state;
	}
	
	public void setCity(City city){
		this.city = city;
	}
	
	public void setAdmissionTest(){
		this.admissionTest = admissionTest;
	}
	
	public void setFee(String fee){
		this.fee = fee;
	}
	
	public void setAdmissionClass(String admissionClass){
		this.admissionClass = admissionClass;
	}
	
	public void setMotherOccupation(String motherOccupation){
		this.motherOccupation = motherOccupation;
	}
	
	public void setFatherOccupation(String fatherOccupation){
		this.fatherOccupation = fatherOccupation;
	}
	
	public void setGender(Gender gender){
		this.gender = gender;
	} 
	
	public void setCaste(Caste caste){
		this.caste = caste;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setMobileNo(String contact){
		this.contact = contact;
	}
	
	public void setSubject(Subject subject){
		this.subject = subject;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setDOB(String dob){
		this.dob = dob;
	}
	
	public void setAge(Integer age){
		this.age = age;
	}
	
	public void setFatherName(String fatherName){
		this.fatherName = fatherName;
	}
	
	public void setStudentName(String studentName){
		this.studentName = studentName;
	}
	
	public void setStudentId(Integer studentId){
		this.studentId = studentId;
	}

	public void setKlass(Klass klass){
		this.klass = klass;
	}

	public Klass getKlass(){
		return klass;
	}

	public String getMarksheet(){
		return marksheetPath;
	}
	
	public void setMarksheet(String marksheetPath){
		this.marksheetPath = marksheetPath;
	}
	
	public String getBirth(){
		return birth;
	}
	
	public void setBirth(String birth){
		this.birth = birth;
	}
	
	public String getPhoto(){
		return photo;
	}
	
	public void setPhoto(String photo){
		this.photo = photo;	
	}

	public String getImagePath(){
		return imagePath;
	}

	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}
	

}

 // insert into fee_status(fee_id)values
 // (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12),(13),(14),(15),(16),(17),(18),(19),(20),(21),(22),(23),(24),(25),(26),(27),(28),(29),(30),(31),(32),(33),(34);