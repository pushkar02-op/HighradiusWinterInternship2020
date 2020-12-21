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

@WebServlet("/employee")
public class viewData extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public viewData() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page=1;
		int recordsPerPage=10;
		try {
		if(request.getParameter("page")!= null) 
			page=Integer.parseInt(request.getParameter("page"));
		HttpSession session = request.getSession();
		String level= session.getAttribute("level").toString();
		DbConnection dao = new DbConnection();
		List <Response> list = dao.viewData((page-1)*recordsPerPage, recordsPerPage,level);
//		while(list.size()<10) {
//			list.add(null);
//		}
		int noOfRecords= dao.getNoOfRecords();
		int noOfPages= (int)Math.ceil(noOfRecords*1.0/ recordsPerPage);
		request.setAttribute("resultList",list );
		request.setAttribute("noOfPages",noOfPages );
		request.setAttribute("currentPage",page );
		request.setAttribute("recordsPerPage", recordsPerPage);
		request.setAttribute("totalRows", noOfRecords);
		RequestDispatcher view= request.getRequestDispatcher("dash.jsp");
		view.forward(request,response);
		
	
	}catch (ClassNotFoundException | SQLException | NumberFormatException  e) {
        e.printStackTrace();
    }
	
	
	}

	

}
