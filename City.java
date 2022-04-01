package models;

import java.sql.PreparedStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.*;

public class City{
	private Integer cityId;
	private String cityName;

	public City(Integer cityId,String cityName){
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public City(String city){
		this.cityName = cityName; 
	}

	public City(Integer cityId){
		this.cityId = cityId;	
	}

	public City(){
		
	}
	
	public static ArrayList collectCities(){
		ArrayList<City> cities = new ArrayList();
		try{Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?user=root&password=1234");

			String query = "select city_id,city_name from cities";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				cities.add(new City(rs.getInt(1),rs.getString(2)));
			}
			System.out.print(cities+" i am cities");
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();	
		}
		return cities;
	}
	
	public void setCityId(Integer cityId){
		this.cityId = cityId;
	}

	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	public String getCityName(){
		return cityName;
	}

	public Integer getCityId(){
		return cityId;
	}
}
