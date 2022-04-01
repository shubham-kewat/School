package models;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

public class School{
	private Integer schoolId;
	private String schoolName;
	private String address;
	private String email;
	private String contact;
	private String registrationNo;
	private Board board;
	private City city;
	private String pincode;
	private String imagePath;

	public School(Board board,City city,String registrationNo,String email,String schoolName,String address,String pincode,String contact){
		this.schoolName = schoolName;
		this.address = address;
		this.email = email;
		this.contact = contact;
		this.registrationNo = registrationNo;
		this.board = board;
		this.city = city;
		this.pincode = pincode;
	}
	
	public School(Integer schoolId,String SchoolName){
		this.schoolId = schoolId;
		this.schoolName = schoolName;
	}
	
	public School(String schoolName,String address,String email,String contact,String registrationNo,Board board,City city){
		this.schoolName = schoolName;
		this.address = address;
		this.email = email;
		this.contact = contact;
		this.registrationNo = registrationNo;
		this.board = board;
		this.city = city;
	
	}
	
	public School(){
	
	}

	public int saveInfo(){
		int value = 0;

		try{Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "insert into schools(school_name,board_id,city_id,address,pincode,email,contact)values(?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1,schoolName);
			ps.setInt(2,board.getBoardId());
			ps.setInt(3,city.getCityId());
			ps.setString(4,address);
			ps.setString(5,pincode);
			ps.setString(6,email);
			ps.setString(7,contact);
								 
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			while(rs.next()){
				value = rs.getInt(1);
				System.out.println("generated value="+value);
			}

			con.close();
			}catch(SQLException|ClassNotFoundException e){
				e.printStackTrace();	
			}
			return value;
	}

	public String getEmail(){
		return email;
	}

	public String getSchoolName(){
		return schoolName;
	}

	public String getContact(){
		return contact;
	}
	
	public String getRegistrationNo(){
		return registrationNo;
	}
	
	public String getAddress(){
		return address;
	}
	
	public Board getBoard(){
		return board;
	}
	
	public Integer getSchoolId(){
		return schoolId;
	}
	
	public City getCity(){
		return city;
	}
	
	public void setCity(City city){
		this.city = city;
	}
	
	public void setBoard(Board board){
		this.board = board;
	}

	public void setRegistrationNo(String registrationNo){
		this.registrationNo = registrationNo;
	}
	
	public void setContact(String contact){
		this.contact = contact;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public void setSchoolId(Integer schoolId){
		this.schoolId = schoolId;
	}

	public void setImagePath(String imagePath){
		this.imagePath = imagePath;
	}

	
}