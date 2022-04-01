package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Librarian {
	private Integer librarianId;
	private String name;
	private String email;
	private String password;
	
	public Librarian(Integer librarianId, String name, String email, String password) {
		super();
		this.librarianId = librarianId;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
	
	public Librarian(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}



	public Librarian() {
		super();
	}
	
	public static Librarian checkExist(String email,String password){
		Librarian librarian = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?autoReconnect=true&useSSL=false&user=root&password=1234");
			String query = "select librarian_id,email,name from library where email=? and password=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,email);
			ps.setString(2,password);
			
			System.out.println(email+" "+password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				librarian = new Librarian();
				librarian.setLibrarianId(rs.getInt(1));
				librarian.setEmail(rs.getString(2));
				librarian.setName(rs.getString(3));
			}
			
		} catch (Exception e) {
			// TODO: handle exception			
		}
		return librarian;
	}
	
	public Boolean addLibrarian(String name,String email,String password){
		Boolean flag = false;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			String query = "insert into library(name,email,password)values(?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1,name);
			ps.setString(2,email);			
			ps.setString(3,password);

			ps.executeUpdate();
			flag = true;
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			flag=false;
		}
		return flag;
	}




	public Integer getLibrarianId() {
		return librarianId;
	}

	public void setLibrarianId(Integer librarianId) {
		this.librarianId = librarianId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}
