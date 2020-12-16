package com.highradius.internship;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public search() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String orderID;
		try {
		if(request.getParameter("search")!=null) {
			orderID= request.getParameter("search");
			DbConnection dao = new DbConnection();
			HttpSession session = request.getSession(false);
			String level=(String) session.getAttribute("level");
				List <Response> list = dao.searchData(orderID,level);
				request.setAttribute("resultList",list );
				RequestDispatcher view= request.getRequestDispatcher("dash.jsp");
				view.forward(request,response);
		}
				
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}

	


