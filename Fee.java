package models;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Fee{
	private Integer feeId;
	private Integer submittedFee;
	private Integer remainingFee;
	private Integer studentId;

	public Fee(Integer FeeId,Integer submittedFee,Integer remainingFee){
		this.feeId = feeId;
		this.submittedFee = submittedFee;
		this.remainingFee = remainingFee;
	}

	public Fee(Integer submittedFee,Integer remainingFee){
		this.submittedFee = submittedFee;
		this.remainingFee = remainingFee;
	}
	
	public Fee(){
		
	}

	
	
	public Integer getStudentId(){
		return studentId;
	}
	
	public Integer getRemainingFee(){
		return remainingFee;
	}
	
	public Integer getSubmittedFee(){
		return submittedFee;
	}
	
	public Integer getFeeId(){
		return feeId;
	}
	
	public void setFeeId(Integer feeId){
		this.feeId = feeId;
	}

	public void setSubmittedFee(Integer submittedFee){
		this.submittedFee = submittedFee;
	}

	public void setRemainingFee(Integer remainingFee){
		this.remainingFee = remainingFee;
	}

	public void setStudentId(Integer studentId){
		this.studentId = studentId;
	}
}