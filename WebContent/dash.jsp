<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
 
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
  
      <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql:// localhost:3306/winter_internship"
         user = "root"  password = "root"/>
 
      <sql:query dataSource = "${snapshot}" var = "result">
         SELECT * from order_details;
      </sql:query>
  <div class="board">
  hii
      <table  width = "100%" class="table table-responsive-lg table-striped table-borderless table-hover w-auto">
      <thead>
      
         <tr>
            <th>
               <div class="form-check">
                  <input type="checkbox" class="form-check-input" id="tableMaterialCheck1">
               </div>
            </th>
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
         <c:forEach var = "row" items = "${result.rows}">
            <tr>
            <th scope="row">
               <div class="form-check">
                  <input type="checkbox" class="form-check-input" id="tableMaterialCheck2">
               </div>
            </th>
               <td><c:out value = "${row.Order_ID}"/></td>
               <td><c:out value = "${row.Customer_Name}"/></td>
               <td><c:out value = "${row.Customer_ID}"/></td>
               <td><c:out value = "${row.Order_Amount}"/></td>
               <td><c:out value = "${row.Approval_Status}"/></td>
               <td><c:out value = "${row.Approved_By}"/></td>
               <td><c:out value = "${row.Notes}"/></td>
               <td><c:out value = "${row.Order_Date}"/></td>
            </tr>
         </c:forEach>
      </table>
 </div>
   </body>
</html>