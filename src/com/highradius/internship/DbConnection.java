package com.highradius.internship;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.highradius.internship.Response;

public class DbConnection {
private int noOfRecords;
	  public List<Response> viewData(int offset,int noOfRecords)throws SQLException, ClassNotFoundException 
	       
	    {  
		  List<Response> list= new ArrayList<Response>();
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
	        	            .prepareStatement("SELECT * from order_details limit ?,? ")) {
	        	            preparedStatement.setInt(1, offset);
	        	            preparedStatement.setInt(2, noOfRecords);
	        	          
	        	            System.out.println(preparedStatement);
	        	            ResultSet rs = preparedStatement.executeQuery();
		  
	        
	        				while(rs.next()) {
	        					Response resp=new Response();
	        					resp.setOrder_id(rs.getString("Order_ID"));
	        					resp.setCust_name(rs.getString("Customer_Name"));
	        					resp.setCust_id(rs.getString("Customer_ID"));
	        					resp.setOrder_amt(rs.getString("Order_Amount"));
	        					resp.setAppr_status(rs.getString("Approval_Status"));
	        					resp.setApproved_by(rs.getString("Approved_By"));
	        					resp.setNotes(rs.getString("Notes"));
	        					resp.setOrder_date(rs.getString("Order_Date"));
	        					list.add(resp);
	        				}
	        				rs.close();
	        				PreparedStatement stmt = connection
	    	        	            .prepareStatement("SELECT COUNT(*) FROM order_details");
	        				rs=stmt.executeQuery();
	        				if(rs.next()) {
	        					this.noOfRecords=rs.getInt(1);
	        					System.out.println(noOfRecords);
	        				}
	        		}catch(SQLException e) {
	        			 printSQLException(e);
	        		};
	        		return list;
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

	  public int getNoOfRecords() {
		  return noOfRecords;
	  }
}
