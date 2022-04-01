package models;

import java.sql.*;
import java.util.*;

public class Klass{
	private Integer klassId;
	private String klassName;

	public Klass(Integer klassId){
		this.klassId = klassId;
	}
	
	public Klass(Integer klassId,String klassName){
		this.klassId = klassId;
		this.klassName = klassName;
	}
	
	public Klass(){
	
	}

	public static ArrayList collectAllClasses(){
		ArrayList<Klass> classes = new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
			
			String query = "select class_id,class_name from class";
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()){
				classes.add(new Klass(rs.getInt(1),rs.getString(2)));
			}
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return classes;
	}

	public void setClassId(Integer klassId){
		this.klassId = klassId;
	}

	public void setClassName(String klassName){
		this.klassName = klassName;
	}

	public Integer getClassId(){
		return klassId;
	}

	public String getClassName(){
		return klassName;
	}

}