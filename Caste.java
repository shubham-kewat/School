package models;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class Caste{
	private Integer casteId;
	private String caste;

	public Caste(Integer casteId,String caste){
		this.casteId = casteId;
		this.caste = caste;
	}

	public Caste(String caste){
		this.caste = caste;	
	}

	public Caste(Integer casteId){
		this.casteId = casteId;
	}
	
	public Caste(){
		
	}

	public static ArrayList collectCastes(){
		ArrayList<Caste> castes = new ArrayList();
		try{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

		String query = "select cast_id,cast from castes";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		while(rs.next()){
			castes.add(new Caste(rs.getInt(1),rs.getString(2)));
		}

		con.close();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return castes;
	}
	
	public void setcasteId(Integer casteId){
		this.casteId = casteId;
	}

	public void setCasteName(String caste){
		this.caste = caste;
	}

	public String getCasteName(){
		return caste;
	}

	public Integer getCasteId(){
		return casteId;
	}
}
