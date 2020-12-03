<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>

 
<html>
   <head>
      <title>SELECT Operation</title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="css/dash.css">
  
  
   </head>


   <body>
   <div class="dashlogo">
      <img src="images/hrc-logo.svg" alt="logo" width="200">
      <div class="dashlogo1">
      <img src="images/abc-logo.png" alt="logo" width="200";>
      </div>
   </div>
  
     
  <div class="board">
  hii ${currentPage}
  <form action="search" method="post">
		<input type="text" name="search" placeholder="Search">
	</form>
      <table  width = "100%" class="table table-responsive-lg table-striped table-borderless table-hover w-auto">
      <thead>
      
         <tr>
            
            <th>ORDER ID</th>
            <th>CUSTOMER Name</th>
            <th>CUSTOMER ID</th>
            <th>AMOUNT</th>
            <th>Approval_Status</th>
            <th>Approved_By</th>
            <th>Notes</th>
            <th>Order_Date</th>
         </tr>
      </thead>
         <c:forEach var = "row" items = "${resultList}">
            <tr>
            
               
               <td><c:out value = "${row.cust_name}"/></td>
               <td><c:out value = "${row.cust_id}"/></td>
               <td><c:out value = "${row.order_amt}"/></td>
               <td><c:out value = "${row.appr_status}"/></td>
               <td><c:out value = "${row.approved_by}"/></td>
               <td><c:out value = "${row.notes}"/></td>
               <td><c:out value = "${row.order_date}"/></td>
               <td><c:out value = "${row.order_id}"/></td>
            </tr>
             
         </c:forEach>
      </table>
      <nav aria-label="Page navigation example">
  			<ul class="pagination">
          
      <c:if test="${currentPage!=1}">
       <li class="page-item">
      <a class="page-link" href="employee?page=${currentPage-1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
      </li>
      </c:if>
      <table border="1" cellpadding="5" cellspacing="5">
      <tr>
      <c:forEach begin="1" end="${noOfPages}" var="i">
      <c:choose>
      <c:when test="${currentPage eq i }">
      <td>${i}</td>
      </c:when>
      <c:otherwise>
      <li class="page-item">
      <td><a href="employee?page=${i}">${i}</a></td>
      </li>
      </c:otherwise>
      </c:choose>
      </c:forEach>
      </tr>
      </table>
       <c:if test="${currentPage lt noOfPages}">
       <li class="page-item">
      <a class="page-link" href="employee?page=${currentPage+1 }"aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
        
      </li>
      </c:if>
      </ul>
      </nav>
 </div>
   </body>
</html>