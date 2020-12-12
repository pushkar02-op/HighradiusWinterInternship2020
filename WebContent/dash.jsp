<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>

 hi${level} 


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
      <img src="images/abc-logo.png" alt="logo" width="200">
      </div>
   </div>
  
     
  <div class="board">
  
  <!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
  ADD
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog ">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="exampleModalLabel">ADD Order</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="add" method="post">
        <div>
        <label for="orderID">Order Id</label>
        <input type="number" name="orderID" id="orderid"><br>
        <label for="orderDate">Order Date</label>
        <input type="date" name="orderDate" id="orderdate"><br>
        <label for="CustomerName">Customer Name</label>
        <input type="text" name="CustomerName" id="custname"><br>
        <label for="CustomerNumber">Customer Number</label>
        <input type="number" name="CustomerNumber" id="custnum"><br>
        <label for="orderAmount">Order Amount</label>
        <input type="number" name="orderAmount" id="orderAmnt"><br>
        <label for="Notes">Notes</label>
        <input type="text" name="Notes" id="notes"><br>
      	<div class="button">
         <input type="submit" value="Add">
        </div>
      </div>
        </form>
      </div>
    </div>
  </div>
</div>



<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal1">
  Edit
</button>
<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
  <div class="modal-dialog ">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="exampleModalLabel1">Edit Order</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="add" method="post">
        <div>
        <label for="orderID1">Order Id</label>
        <input type="number" name="orderID1" id="orderid1"><br>
        <label for="orderDate1">Order Date</label>
        <input type="date" name="orderDate1" id="orderdate1"><br>
        <label for="CustomerName1">Customer Name</label>
        <input type="text" name="CustomerName1" id="custname1"><br>
        <label for="CustomerNumber1">Customer Number</label>
        <input type="text" name="CustomerNumber1" id="custnum1"><br>
        <label for="orderAmount1">Order Amount</label>
        <input type="number" name="orderAmount1" id="orderAmnt1"><br>
        <label for="Notes1">Notes</label>
        <input type="text" name="Notes1" id="notes1"><br>
        
        </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
  
  <form action="search" method="get">
		<input type="text" name="search" placeholder="Search">
	</form>
      <table  width = "100%" class="table table-responsive-lg table-striped table-borderless table-hover w-auto">
      <thead>
      
         <tr>
            <th>Select</th>
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
            
               <td><input type="checkbox"></td>
               <td><c:out value = "${row.order_id}"/></td>
               <td><c:out value = "${row.cust_name}"/></td>
               <td><c:out value = "${row.cust_id}"/></td>
               <td><c:out value = "${row.order_amt}"/></td>
               <td><c:out value = "${row.appr_status}"/></td>
               <td><c:out value = "${row.approved_by}"/></td>
               <td><c:out value = "${row.notes}"/></td>
               <td><c:out value = "${row.order_date}"/></td>
               
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