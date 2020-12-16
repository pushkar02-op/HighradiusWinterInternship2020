package com.highradius.internship;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/edit")
public class edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public edit() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderID = Integer.parseInt(request.getParameter("orderID1"));
		int orderAmount=Integer.parseInt( request.getParameter("orderAmount1"));
		String notes = request.getParameter("Notes1");
		Response resp = new Response();
		resp.setOrder_id(orderID);
		resp.setOrder_amt(orderAmount);
		resp.setNotes(notes);
		System.out.println(orderID);
		try {
			DbConnection dao = new DbConnection();
			int status=dao.editData(resp);
			System.out.println(status);
			if(status>0) {
				response.sendRedirect("employee");
			}
		} catch ( NumberFormatException e) {
			
			e.printStackTrace();
		}
	}

}
