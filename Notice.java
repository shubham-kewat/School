package models;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;

public class Notice{
	private Integer noticeId;
	private String noticeIssue;
	private String noticeData;

	
	public Notice(){

	}

	public Notice(Integer noticeId,String noticeIssue,String noticeData){
		this.noticeId = noticeId;
		this.noticeData = noticeData;
		this.noticeIssue = noticeIssue;
	}

	public Boolean setNotice(String notice,String date){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "insert into notices (notice_data,notice_issue)values(?,?)";

			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,notice);
			ps.setString(2,date);

			ps.executeUpdate();

			con.close();
			flag = true;

		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return flag;
	}

	public static ArrayList<Notice> collectAllNotice(){
		ArrayList<Notice> notice = new ArrayList<Notice>();	
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "select notice_id,notice_issue,notice_data from notices";

			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				notice.add(new Notice(rs.getInt(1),rs.getString(2),rs.getString(3)));
			}

			con.close();


		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return notice;
	}

	public Integer getNoticeId(){
		return noticeId;
	}

	public String getNoticeIssue(){
		return noticeIssue;
	}

	public String getNoticeData(){
		return noticeData;
	}

	public void setNoticeId(Integer noticeId){
		this.noticeId = noticeId;
	}

	public void setNoticeIssue(String noticeIssue){
		this.noticeIssue = noticeIssue;
	}

	public void setNoticeData(String noticeData){
		this.noticeData = noticeData;
	}
}