package com.highradius.internship;

import java.io.IOException;

import java.sql.SQLException;

import java.lang.NumberFormatException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class DummyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private DatabaseConnection dbconn; 
    
      public void init() {
          dbconn = new DatabaseConnection();
      }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String level;
		Response resp = new Response();
		
		resp.setUsername(name);
		resp.setPassword(pwd);
		
		
        try {
            if (dbconn.validate(resp)) {
            	
            	HttpSession session = request.getSession();
            	session.setAttribute("name", name);
            	level=dbconn.getLevel();
        		session.setAttribute("level", level);
        		response.sendRedirect("employee");
            	
               
            } else {
               
                response.sendRedirect("index.jsp");
                
            }
        } catch (ClassNotFoundException | SQLException | NumberFormatException  e) {
            e.printStackTrace();
        }
		
	}


}
