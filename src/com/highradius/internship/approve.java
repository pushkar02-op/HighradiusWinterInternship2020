package com.highradius.internship;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
@WebServlet("/approve")
public class approve extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public approve() {
        super();
        
    }
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderID = Integer.parseInt(request.getParameter("orderid"));
		System.out.println(orderID);
		HttpSession session = request.getSession();
		String name= session.getAttribute("name").toString();
		Response resp = new Response();
		resp.setOrder_id(orderID);
		resp.setUsername(name);
		try {
			DbConnection dao = new DbConnection();
			int status=dao.approveData(resp);
			if(status>0) {
				response.sendRedirect("employee");
			}
		} catch ( NumberFormatException e) {
			
			e.printStackTrace();
		}
	}

}
