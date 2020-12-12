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
		
		public static Connection getConnection() {
			Connection con=null;
			 String dbDriver = "com.mysql.jdbc.Driver"; 
		        String dbURL = "jdbc:mysql:// localhost:3306/"; 
		        String dbName = "winter_internship"; 
		        String dbUsername = "root"; 
		        String dbPassword = "root"; 
		        try {
		        	Class.forName(dbDriver); 
		        	con=DriverManager.getConnection(dbURL + dbName , 
                            dbUsername,  
                            dbPassword);
		        }catch(Exception e){System.out.println(e);}  
		        return con;  
			
		}
		
	  public List<Response> viewData(int offset,int noOfRecords)throws SQLException, ClassNotFoundException 
	       
	    {  
		  List<Response> list= new ArrayList<Response>();
		  try {
		  			Connection con = DbConnection.getConnection();
	        		PreparedStatement preparedStatement = con
	        	            .prepareStatement("SELECT * from order_details limit ?,? "); {
	        	            preparedStatement.setInt(1, offset);
	        	            preparedStatement.setInt(2, noOfRecords);
	        	          
	        	            System.out.println(preparedStatement);
	        	            ResultSet rs = preparedStatement.executeQuery();
		  
	        
	        				while(rs.next()) {
	        					Response resp=new Response();
	        					resp.setOrder_id(rs.getInt("Order_ID"));
	        					resp.setCust_name(rs.getString("Customer_Name"));
	        					resp.setCust_id(rs.getInt("Customer_ID"));
	        					resp.setOrder_amt(rs.getInt("Order_Amount"));
	        					resp.setAppr_status(rs.getString("Approval_Status"));
	        					resp.setApproved_by(rs.getString("Approved_By"));
	        					resp.setNotes(rs.getString("Notes"));
	        					resp.setOrder_date(rs.getString("Order_Date"));
	        					list.add(resp);
	        				}
	        				rs.close();
	        				PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM order_details");
	        				rs=stmt.executeQuery();
	        				if(rs.next()) {
	        					this.noOfRecords=rs.getInt(1);
	        					System.out.println(noOfRecords);
	        				}
	        				rs.close();
	        	            }
	        				
	        	           }catch(SQLException e) {
	        			 printSQLException(e);
	        		};
	        		return list;
  	  };
	  public List<Response> searchData(String orderID)throws SQLException, ClassNotFoundException 
      
	    {  
		  List<Response> searchlist= new ArrayList<Response>();
		  
		  
	        try(
	        Connection connection =  DbConnection.getConnection();
	        		PreparedStatement preparedStatement = connection
	        	            .prepareStatement("select * from order_details where Order_ID = ?")) {
	        	            preparedStatement.setString(1, orderID);
	        	          
	        	            System.out.println(preparedStatement);
	        	            ResultSet rs = preparedStatement.executeQuery();
		  
	        
	        				while(rs.next()) {
	        					Response resp=new Response();
	        					resp.setOrder_id(rs.getInt("Order_ID"));
	        					resp.setCust_name(rs.getString("Customer_Name"));
	        					resp.setCust_id(rs.getInt("Customer_ID"));
	        					resp.setOrder_amt(rs.getInt("Order_Amount"));
	        					resp.setAppr_status(rs.getString("Approval_Status"));
	        					resp.setApproved_by(rs.getString("Approved_By"));
	        					resp.setNotes(rs.getString("Notes"));
	        					resp.setOrder_date(rs.getString("Order_Date"));
	        					searchlist.add(resp);
	        				}
	        				rs.close();
	        }catch(SQLException e) {
   			 printSQLException(e);
   		};
   		return searchlist;
	        }
	  
	   public  int addData(Response resp){  
	        int status=0;  
	        try{  
	            Connection con=DbConnection.getConnection();  
	            PreparedStatement ps=con.prepareStatement(  
	                         "insert into order_details(Order_ID,Order_Date,Customer_Name,Customer_ID,Order_Amount,Notes) values (?,?,?,?,?,?)");  
	            ps.setInt(1,resp.getOrder_id());  
	            ps.setString(2,resp.getOrder_date());  
	            ps.setString(3,resp.getCust_name());  
	            ps.setInt(4,resp.getCust_id());  
	            ps.setInt(5,resp.getOrder_amt());
	            ps.setString(6,resp.getNotes());
	            System.out.println(ps);
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(SQLException e) {
	   			 printSQLException(e);
	   		}; 
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

	  public int getNoOfRecords() {
		  return noOfRecords;
	  }
}
