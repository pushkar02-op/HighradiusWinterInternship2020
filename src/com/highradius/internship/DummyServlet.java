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
		level=resp.getUserLevel();
//		level=Integer.parseInt(resp.getUserLevel());
		System.out.println(level);
        try {
            if (dbconn.validate(resp)) {
//            	if(level==1) {
            		response.sendRedirect("dash.jsp");
//            	}
//            	else {
//            		response.sendRedirect("dash1.jsp");
//            	}
//            
                
               
            } else {
                HttpSession session = request.getSession();
                response.sendRedirect("index.html");
                
            }
        } catch (ClassNotFoundException | SQLException | NumberFormatException  e) {
            e.printStackTrace();
        }
		
	}


}
