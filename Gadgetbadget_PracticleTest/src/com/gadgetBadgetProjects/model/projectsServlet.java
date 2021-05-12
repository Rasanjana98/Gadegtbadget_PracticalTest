package com.gadgetBadgetProjects.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class projectsServlet {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projectservice","root","1234");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertProject(String projectname,String projectdescription,String requiredfund,String projectduration) {
		
		String output = "";
		try {
			Connection con = connect();
			
			if(con==null) {
				return "Error while connecting";
			}
			
			String query = "insert into project_practicle (id,projectname,projectdescription,requiredfund,projectduration)" 
			+ "values(?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, projectname);
			preparedStatement.setString(3, projectdescription);
			preparedStatement.setString(4, requiredfund);
			preparedStatement.setString(5, projectduration);
			
			preparedStatement.execute();
			con.close();
			
			 
			 String newProject = readProjects(); 
			 output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}"; 

		}catch(Exception e){
			output ="Error while inserting to DB";
			System.err.println(e.getMessage());
			System.out.println("+++++++++++++++++++"+e.getMessage());
		}
		
		return output;
	}
	
	public String readProjects() {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting";
			}
			
			output = "<table border='1'><tr><th>ID</th><th>Description</th>" +
					 "<th>Name</th>" + 
					 "<th>Required Fund</th>" + 
					 "<th> Duration</th>" +
					 "<th>Update</th><th>Remove</th></tr>";  
			
			String query = "Select * from project_practicle";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			
			while(rs.next()) {
				String id=Integer.toString(rs.getInt("id"));
				String projectname= rs.getString("projectname");
				String projectdescription=rs.getString("projectdescription");
				String requiredfund= rs.getString("requiredfund");
				//String requiredfund = Double.toString(rs.getDouble("requiredfund"));
				String projectduration = rs.getString("projectduration");
				
				output += "<tr><td>" + id + "</td>"; 
				output += "<td>" + projectname + "</td>"; 
				output += "<td>" + projectdescription + "</td>"; 
				output += "<td>" + requiredfund + "</td>"; 
				output += "<td>" + projectduration + "</td>"; 
				
				/**output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btn btn-secondary'></td>"
						+ "<td>"
						+ "<input name='btnRemove' type='submit' value='Remove'"
						+ "  class='btn btn-danger'>" 
						+ "</form></td></tr>"; **/
				
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						 + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='" 
						 + id + "'>" + "</td></tr>";
			}
			
			con.close();
			
			output += "</table>";
			
		}catch(Exception e) {
			output = "Error while reading";
			System.err.println(e.getMessage());
			System.out.println("++++++++++++++++++++"+e.getMessage());
		}
		
		return output;
	}
	
	public String updateProjects(String id,String projectname,String projectdescription,String requiredfund,String projectduration) {
		
		String output ="";
		
		try {
			Connection con = connect();
			if(con ==null) {
				return"Error while connecting to DB";
			}
			
			String query = "UPDATE project_practicle SET projectname=?, projectdescription=?,requiredfund=?,projectduration=? WHERE id=?";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setString(1, projectname);
			preparedStatement.setString(2, projectdescription);
			preparedStatement.setString(3,requiredfund);
			preparedStatement.setString(4, projectduration);
			preparedStatement.setInt(5, Integer.parseInt(id));
			
			preparedStatement.execute();
			con.close();
			
			 String newProject = readProjects(); output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}"; 
			
		}catch(Exception e){
			output ="Error while updating projects";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deleteProjects (String id) {
		
		String output ="";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to DB";
			}
			
			String query = "DELETE FROM project_practicle where id=?";
			
			PreparedStatement preparedStatement =con.prepareStatement(query);
			
			preparedStatement.setInt(1, Integer.parseInt(id));
			
			preparedStatement.execute();
			con.close();
			
			 String newProject = readProjects(); output = "{\"status\":\"success\", \"data\": \"" + newProject + "\"}";
			
		}catch(Exception e){
			 output = "{\"status\":\"error\", \"data\": \"Error while deleting ....\"}"; 
			System.err.println(e.getMessage());
			System.out.println("+++++++++++++++++++++++++++++++++++"+e);
		}
		
		return output;
	}


}
