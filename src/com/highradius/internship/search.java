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
		int page=1;
		int recordsPerPage=10;
		try {
		if(request.getParameter("search")!=null) {
			orderID= request.getParameter("search");
			DbConnection dao = new DbConnection();
			HttpSession session = request.getSession();
			String level=session.getAttribute("level").toString();
				List <Response> list = dao.searchData(orderID,level,(page-1)*recordsPerPage, recordsPerPage);
				
				int noOfRecords= dao.getNoOfRecordsForSearch();
				int noOfPages= (int)Math.ceil(noOfRecords*1.0/ recordsPerPage);
				request.setAttribute("noOfPages",noOfPages );
				request.setAttribute("currentPage",page );
				request.setAttribute("resultList",list );
				request.setAttribute("totalRows", noOfRecords);
				RequestDispatcher view= request.getRequestDispatcher("dash.jsp");
				view.forward(request,response);
		}
				
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	}

	


