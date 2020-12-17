package com.highradius.internship;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.highradius.internship.Response;

public class DbConnection {
private int totalNoOfRecords;
private int totalNoOfRecordsForSearch;
		
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
		
	  public List<Response> viewData(int offset,int noOfRecords,String level)throws SQLException, ClassNotFoundException 
	       
	    {  
		  List<Response> list= new ArrayList<Response>();
		  String query = null;
		  String countRowsQuery=null;
		  if(level.equals("Level 1")) {
			  query="SELECT * from order_details limit "+offset+","+noOfRecords;
		  }else if(level.equals("Level 2")) {
			  query="SELECT * from order_details where Order_Amount > 10000 and Order_Amount <= 50000 limit "+offset+","+noOfRecords;
		  }else if(level.equals("Level 3")) {
			  query="SELECT * from order_details where Order_Amount > 50000 limit "+offset+","+noOfRecords;
		  }
		  
		  if(level.equals("Level 1"))
			  countRowsQuery="SELECT COUNT(*) FROM order_details";
		  else if(level.equals("Level 2"))
				  countRowsQuery="SELECT COUNT(*) FROM order_details where Order_Amount > 10000 and Order_Amount <= 50000"; 
		  else if(level.equals("Level 3"))
				  countRowsQuery="SELECT COUNT(*) FROM order_details where Order_Amount > 50000"; 
		  try {
		  			Connection con = DbConnection.getConnection();
	        		PreparedStatement preparedStatement = con.prepareStatement(query);{
	        	          
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
	        				
	        				PreparedStatement stmt = con.prepareStatement(countRowsQuery);
	        				rs=stmt.executeQuery();
	        				if(rs.next()) {
	        					this.totalNoOfRecords=rs.getInt(1);
	        					
	        					
	        				}
	        				rs.close();
	        				con.close(); 
	        	            }
	        				
	        	           }catch(SQLException e) {
	        			 printSQLException(e);
	        		};
	        		return list;
  	  };
	  public List<Response> searchData(String orderID, String level,int offset,int noOfRecords)throws SQLException, ClassNotFoundException 
      
	    {  
		  List<Response> searchlist= new ArrayList<Response>();
		  String query = null;
		  String countRowsQuery=null;
		  if(level.equals("Level 1")) {
			  query="select * from order_details where Order_ID Like '"+orderID+"%' limit "+offset+","+noOfRecords;
		  }else if(level.equals("Level 2")) {
			  query="select * from order_details where Order_ID Like '"+orderID+"%' and Order_Amount > 10000 and Order_Amount <= 50000 limit "+offset+","+noOfRecords;
		  }else if(level.equals("Level 3")) {
			  query="select * from order_details where Order_ID Like '"+orderID+"%' and Order_Amount > 50000 limit "+offset+","+noOfRecords;
		  }
		  
		  if(level.equals("Level 1"))
			  countRowsQuery="SELECT COUNT(*) FROM order_details where Order_ID Like '"+orderID+"%'";
		  else if(level.equals("Level 2"))
				  countRowsQuery="SELECT COUNT(*) FROM order_details where Order_Amount > 10000 and Order_Amount <= 50000 and Order_ID Like '"+orderID+"%'"; 
		  else if(level.equals("Level 3"))
				  countRowsQuery="SELECT COUNT(*) FROM order_details where Order_Amount > 50000 and Order_ID Like '"+orderID+"%'"; 
		  
	        try(
	        Connection connection =  DbConnection.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        	            
	        	          
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
	        				PreparedStatement stmt = connection.prepareStatement(countRowsQuery);
	        				rs=stmt.executeQuery();
	        				if(rs.next()) {
	        					this.totalNoOfRecordsForSearch=rs.getInt(1);
	        				connection.close(); 
	        				}
	        				}catch(SQLException e) {
   			 printSQLException(e);
   		};
   		return searchlist;
	        }
	  
	   public  int addData(Response resp){  
	        int status=0;  
	        String Approval_status=null;
	        String Approved_by=null;
	        try{  
	        	int amount= resp.getOrder_amt();
	        	if(amount<=10000) {
	        		Approval_status="Approved";
	        		Approved_by="David_Lee";
	        	}
	        	else if((10000<amount)&&(amount<=50000)) {
	        		Approval_status="Awaiting Approval";
	        	}
	        	else if(amount>50000) {
	        		Approval_status="Awaiting Approval";
	        	}
	            Connection con=DbConnection.getConnection();  
	            PreparedStatement ps=con.prepareStatement(  
	                         "insert into order_details(Order_ID,Order_Date,Customer_Name,Customer_ID,Order_Amount,Notes,Approval_Status,Approved_By) values (?,?,?,?,?,?,?,?)");  
	            ps.setInt(1,resp.getOrder_id());  
	            ps.setString(2,resp.getOrder_date());  
	            ps.setString(3,resp.getCust_name());  
	            ps.setInt(4,resp.getCust_id());  
	            ps.setInt(5,amount);
	            ps.setString(6,resp.getNotes());
	            ps.setString(7,Approval_status);
	            ps.setString(8,Approved_by);
	            System.out.println(ps);
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(SQLException e) {
	   			 printSQLException(e);
	   		}; 
	        return status;  
	    }
	  
	   public int editData(Response resp) {
		   int status=0;
		   String Approval_status=null;
	        String Approved_by=null;
	        try{  
	        	int amount= resp.getOrder_amt();
	        	if(amount<=10000) {
	        		Approval_status="Approved";
	        		Approved_by="David_Lee";
	        	}
	        	else if((10000<amount)&&(amount<=50000)) {
	        		Approval_status="Awaiting Approval";
	        	}
	        	else if(amount>50000) {
	        		Approval_status="Awaiting Approval";
	        	}
	            Connection con=DbConnection.getConnection();  
	            PreparedStatement ps=con.prepareStatement(  
	                         "update order_details set Order_Amount=?,Notes=?,Approval_Status=?,Approved_By=? where Order_ID=?");  
	            ps.setInt(5,resp.getOrder_id()); 
	            ps.setInt(1,amount);
	            ps.setString(2,resp.getNotes());
	            ps.setString(3,Approval_status);
	            ps.setString(4,Approved_by);
	            System.out.println(ps);
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(SQLException e) {
	   			 printSQLException(e);
	   		};
		   
		return status;   
	   }
	   
	   public int approveData(Response resp) {
		   int status=0;
		   
	        String Approved_by=null;
	        
	        try{  
	        	
	        	if(resp.getUsername().equals("Laura_Smith")) {
	        		Approved_by="Laura Smith";
	        	}else {
	        		Approved_by="Matthew Vance";
	        	}
	        	
	            Connection con=DbConnection.getConnection();  
	            PreparedStatement ps=con.prepareStatement(  
	                         "update order_details set Approval_Status=?,Approved_By=? where Order_ID=?");  
	            ps.setInt(3,resp.getOrder_id()); 
	            ps.setString(1,"Approved");
	            ps.setString(2,Approved_by);
	            System.out.println(ps);
	            status=ps.executeUpdate();  
	              
	            con.close();  
	        }catch(SQLException e) {
	   			 printSQLException(e);
	   		};
		   return status;
	   }
	   
	   
	   public int rejectData(Response resp) {
		   int status=0;
		   
	        String Approved_by=null;
	        
	        try{  
	        	
	        	if(resp.getUsername().equals("Laura_Smith")) {
	        		Approved_by="Laura Smith";
	        	}else {
	        		Approved_by="Matthew Vance";
	        	}
	        	
	            Connection con=DbConnection.getConnection();  
	            PreparedStatement ps=con.prepareStatement(  
	                         "update order_details set Approval_Status=?,Approved_By=? where Order_ID=?");  
	            ps.setInt(3,resp.getOrder_id()); 
	            ps.setString(1,"Rejected");
	            ps.setString(2,Approved_by);
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
		  
		  return totalNoOfRecords;
	  }
	  public int getNoOfRecordsForSearch(){
		  return totalNoOfRecordsForSearch;
	  }
}
