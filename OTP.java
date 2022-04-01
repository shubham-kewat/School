//not using ir~~**


package models;

import java.sql.*;
import java.io.*;

public class OTP{
	private Integer otpId;
	private String otp;

	public OTP(){

	}

	public OTP(Integer otpId,String otp){
		this.otp = otp;
		this.otpId = otpId;
	}

	public static Boolean checkIfPresent(Integer otpId,String otp){
		Boolean flag = false;
		otp = otp.trim();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "select otp from otp where otp_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,otpId);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				String fetched = rs.getString(1).trim();

				if(fetched.equals(otp))
					flag=true;
			}

			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return flag;
	}

	public static Integer addOtpInDatabase(String otp){
		Integer otpId=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "insert into otp (otp)values(?)";
			PreparedStatement ps = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

			ps.setString(1,otp);
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();

			while(rs.next()){
				otpId = rs.getInt(1);
			}

			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return otpId;
	}

	public void setOtpId(Integer otpId){
		this.otpId = otpId;
	}

	public void setOtp(String otp){
		this.otp = otp;
	}

	public Integer getOtpId(){
		return otpId;
	}

	public String getOtp(){
		return otp;
	}
}