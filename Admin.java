package models;

import java.sql.*;
import java.io.*;

public class Admin{
	private Integer adminId;
	private String adminName;
	private String contact;
	private String password;
	private String email;

	public Admin(Integer adminId,String adminName,String email,String contact,String password){
		this.adminId = adminId;
		this.adminName = adminName;
		this.email = email;
		this.contact = contact;
		this.password=password;
	}

	public Admin(){

	}

	public Admin(String email,String password){
		this.email = email;
		this.password=password;
	}

	public Admin loginData(){
		Admin a = null;
		String admin;
		String mobile;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "select email,mobile from admin where email=? and password=?";			 

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,email);
			ps.setString(2,password);

			System.out.println(email+"--"+password+" @shubham keawt ");

			ResultSet rs = ps.executeQuery();
			System.out.println(rs+" Result Set~~~~~~~~~~~~~~~~~~~");			
			System.out.println("rs.next()");
			System.out.println(rs+" Result Set~~~~~~~~~~~~~~~~~~~");
			if(rs.next()){
				System.out.println("inside if");
				a = new Admin();
				admin = rs.getString(1);
				mobile = rs.getString(2);
				
				System.out.println(admin+"$$");
//				System.out.println(id+"^^");
				a.setEmail(admin);
				a.setContact(mobile);
			}

			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		System.out.println(a+" thsis is the value while l o g  in having contanc enumber "+a.getContact());
		return a;
	}

	public Boolean saveInfo(String name,String email,String contact,String password){
		Boolean flag=false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "insert into admin(admin_name,email,mobile,password)values(?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			
			ps.setString(1,name);
			ps.setString(2,email);
			ps.setString(3,contact);
			ps.setString(4,password);

			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			if(rs.next()){
				Integer key = rs.getInt(1);
				System.out.println("admin key="+key);
			}
			String query2 = "delete from otp where otp=?";
			PreparedStatement ps1 = con.prepareStatement(query2);
			ps1.setString(1,password);
			ps1.executeUpdate();
			con.close();

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return flag;
	}

	public static Boolean changePassword(Integer adminId,String newPassword){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "update admin set password=? where admin_id=?";
			PreparedStatement ps = con.prepareStatement(query);		

			ps.setString(1,newPassword);
			ps.setInt(2,adminId);

			ps.executeUpdate();
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return flag;
	}

	public String getEmail(){
		return email;
	}

	public String getContact(){
		return contact;
	}

	public String getAdminName(){
		return adminName;
	}

	public Integer getAdminId(){
		return adminId;
	}

	public String getPassword(){
		return password;
	}

	public void setAdminId(Integer adminId){
		this.adminId = adminId;
	}

	public void setAdminName(String adminName){
		this.adminName = adminName;
	}

	public void setContact(String contact){
		this.contact = contact;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setPassword(String password){
		this.password = password;
	}
}