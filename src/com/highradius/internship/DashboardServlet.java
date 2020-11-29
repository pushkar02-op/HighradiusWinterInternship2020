package com.highradius.internship;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.*; 


import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public DashboardServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		
		PrintWriter out = res.getWriter();  
		List dataList
		= new ArrayList();
        res.setContentType("text/html");  
        out.println("<html><body>");  
        try 
        {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql:// localhost:3306/winter_internship", "root", "root");  
              
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from order_details");  
//            out.println("<table border=1 width=100% height=100%>");  
//            out.println("<tr><th>EmpId</th><th>EmpName</th><th>Salary</th><th>LOL</th><tr>");  
            while (rs.next()) 
            {  
            	dataList.add(rs.getInt("Order_ID"));

            	  dataList.add(rs.getString("Customer_Name"));
//                String n = rs.getString("Order_ID");  
//                String nm = rs.getString("Customer_Name"); 
//                String nnn = rs.getString("Order_Amount");
//                String x = rs.getString("Approval_Status");
//                String y = rs.getString("Approved_By");
//                String z = rs.getString("Notes");
//                String a = rs.getString("Order_Date");
//                int s = rs.getInt("Customer_ID");   
//                out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + s + "</td><td>" + y + "</td></tr>");   
            }  
//            out.println("</table>");  
//            out.println("</html></body>");  
            con.close();  
           }  
            catch (Exception e) 
           {  
            	e.printStackTrace(); 
        } 
        request.setAttribute("data",dataList);

        //Disptching request

        RequestDispatcher dispatcher = request.getRequestDispatcher("dash1.jsp");

        if (dispatcher != null){

        dispatcher.forward(request, res);

        }
	
	}

	
	

}
