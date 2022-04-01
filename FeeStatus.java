package models;

import java.sql.*;
import java.io.*;
import java.util.*;
import models.*;

public class FeeStatus{
	private Fee feeId;
	private Integer statusId;
	private Integer submittedFee;
	private Integer remainingFee;
	private String date;

//----------------------------------------- constructor start -----------------------------	
	public FeeStatus(String date,Integer submittedFee,Integer remainingFee){
		this.submittedFee = submittedFee;
		this.remainingFee = remainingFee;
		this.date = date;
	}

	public FeeStatus(){

	}
//----------------------------------------- constructor ends -----------------------------
	
	public ArrayList<FeeStatus> collectFeeStatus(Integer id){
		ArrayList<FeeStatus> feeStatus = new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "select date,submitted_fee,remaining_fee from fee_status where fee_id=(select fee_id from fees where student_id=?)";		

			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,id);		
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				feeStatus.add(new FeeStatus(rs.getString(1),rs.getInt(2),rs.getInt(3)));
			}

			con.close();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return feeStatus;
	}

	

	public static FeeStatus fillAmount(Integer studentId,Integer amount,String date){
		Integer remainingFee = 0,submittedFee=0;
		FeeStatus feeStatus=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
						
			String query = "select submitted_fee,remaining_fee from fee_status where fee_id=(select fee_id from fees where student_id=?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,studentId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				if(rs.getInt(2)==0){
					PreparedStatement ps1 = con.prepareStatement("update fee_status set remaining_fee=? where fee_id=(select fee_id from fees where student_id=?)"); 
					System.out.println("here suremaining fee is 0");					
					ps1.setInt(1,10000);
					remainingFee = 10000;
					ps1.setInt(2,studentId);
					ps1.executeUpdate();				
			}else{
				submittedFee = rs.getInt(1);
				remainingFee = rs.getInt(2);
			}	
				
			

			remainingFee = remainingFee-amount;	
			if(remainingFee<0)
				remainingFee = remainingFee*(-1);
			amount=amount+submittedFee;
			}
			
			
			String query2 = "update fee_status set submitted_fee=?,remaining_fee=?,date=? where fee_id=(select fee_id from fees where student_id=?)";
			
			PreparedStatement ps1 = con.prepareStatement(query2);
			ps1.setInt(1,amount);
			ps1.setInt(2,remainingFee);
			ps1.setString(3,date);
			ps1	.setInt(4,studentId);

			ps1.executeUpdate();
			
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();	
		}

		return feeStatus;
	}
	


	//------------------------------------- getter setter start ---------------------------------
	public String getDate(){
		return date;
	}

	public Integer getRemainingFee(){
		return remainingFee;
	}

	public Integer getSubmittedFee(){
		return submittedFee;
	}

	public Integer getStatusId(){
		return statusId;
	}

	public Fee getFeeId(){
		return feeId;
	}

	public void setFeeId(Fee feeId){
		this.feeId = feeId;
	} 

	public void setStatusId(Integer statusId){
		this.statusId = statusId;
	}

	public void setSubmittedFee(Integer submittedFee){
		this.submittedFee = submittedFee;
	}

	public void setRemainingFee(Integer remainingFee){
		this.remainingFee = remainingFee;
	}

	public void setDate(String date){
		this.date = date;
	}
}