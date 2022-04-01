package models;

import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.*;

public class State{
	private Integer stateId;
	private String stateName;

	public State(Integer stateId,String stateName){
		this.stateId = stateId;
		this.stateName = stateName;
	}

	public State(String stateName){
		this.stateName = stateName;
	}

	public State(Integer stateId){
		this.stateId = stateId;
	}

	public State(){
		
	}
	
	public static ArrayList collectStates(){
		ArrayList<State> states = new ArrayList();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");
		
			String query = "select state_id,state_name from states";
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				states.add(new State(rs.getInt(1),rs.getString(2)));
			}
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return states;
	}
	
	public void setStateId(Integer stateId){
		this.stateId = stateId;
	}

	public void setStateName(String stateName){
		this.stateName = stateName;
	}

	public String getStateName(){
		return stateName;
	}

	public Integer getStateId(){
		return stateId;
	}
}
