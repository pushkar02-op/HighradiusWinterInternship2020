package com.highradius.internship;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public add() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		String orderDate = request.getParameter("orderDate");
		String customerName = request.getParameter("CustomerName");
		int customerID = Integer.parseInt(request.getParameter("CustomerNumber"));
		int orderAmount =Integer.parseInt( request.getParameter("orderAmount"));
		String notes = request.getParameter("Notes");
		Response resp = new Response();
		resp.setOrder_id(orderID);
		resp.setOrder_date(orderDate);
		resp.setCust_name(customerName);
		resp.setCust_id(customerID);
		resp.setOrder_amt(orderAmount);
		resp.setNotes(notes);
		try {
			DbConnection dao = new DbConnection();
			int status=dao.addData(resp);
			System.out.println(status);
			if(status>0) {
				response.sendRedirect("employee");
			}
		} catch ( NumberFormatException e) {
			
			e.printStackTrace();
		}
	}

}
