package com.highradius.internship;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import com.highradius.internship.Response;

public class DatabaseConnection {
	
	public boolean validate(Response responce)
	        throws SQLException, ClassNotFoundException 
	       
	    {  
		    boolean status=false;
	        String dbDriver = "com.mysql.jdbc.Driver"; 
	        String dbURL = "jdbc:mysql:// localhost:3306/"; 
	        String dbName = "winter_internship"; 
	        String dbUsername = "root"; 
	        String dbPassword = "root"; 
	  
	        Class.forName(dbDriver); 
	        try(
	        Connection connection = DriverManager.getConnection(dbURL + dbName , 
	                                                     dbUsername,  
	                                                     dbPassword); 
	        		PreparedStatement preparedStatement = connection
	        	            .prepareStatement("select user_level from user_details where username = ? and password = ? ")) {
	        	            preparedStatement.setString(1, responce.getUsername());
	        	            preparedStatement.setString(2, responce.getPassword());
	        	          
	        	            System.out.println(preparedStatement);
	        	            ResultSet rs = preparedStatement.executeQuery();
	        	            status = rs.next();
	        	            String level=rs.getString("user_level");
	        	            System.out.println(level);
//        	  	            responce.setUserLevel(level);
	        	            
	        	         
	        	            }
	        	            catch (SQLException e) {
	        	                
	        	                printSQLException(e);
	        	            }
	        	            return status;
	        	            
	        	        }
	        	        private void printSQLException(SQLException ex) {
	        	            for (Throwable e: ex) {
	        	                if (e instanceof SQLException) {
	        	                    e.printStackTrace(System.err);
	        	                    System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	        	                    System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	        	                    System.err.println("Message: " + e.getMessage());
	        	                    Throwable t = ex.getCause();
	        	                    while (t != null) {
	        	                        System.out.println("Cause: " + t);
	        	                        t = t.getCause();
	        	                    }
	        	                }
	        	            }
	    }
	        
	        	      
	        	        
}
