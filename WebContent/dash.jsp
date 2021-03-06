<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>

 
<html>
   <head>
      <title>Order Management App</title>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  
  <link rel="stylesheet" href="css/dash.css">
  
   </head>


   <body>
  
   <c:if test="${not empty errorMessage }"><script>alert("${errorMessage}")</script></c:if>
   
  
   
  
  
   
  
   <div class="dashlogo">
      <img src="images/hrc-logo.svg" alt="logo" width="200">
      <div class="dashlogo1">
      <img src="images/abc-logo.png" alt="logo" width="200">
      </div>
   </div>
  
     
  <div class="board">
  <c:if test="${level=='Level 1' }">
  
  
  <!-- Button trigger modal -->
<button  type="button" class="btn btn-primary"  data-toggle="modal" data-target="#exampleModal" >
  ADD
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog ">
    <div class="modal-content">
      <div class="modal-header" style='border-bottom: 2px solid #d1cac3;'>
        <h3 class="modal-title" id="exampleModalLabel"style='display:inline;'>ADD Order</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="add" method="post" class="popup" onsubmit="return validateForm()" name="myForm">
        <div>
        <label for="orderID">Order Id</label>
        <input type="number" name="orderID" id="orderid" ><br>
        <label for="orderDate">Order Date</label>
        <input type="date" name="orderDate" id="orderdate" ><br>
        <label for="CustomerName">Customer Name</label>
        <input type="text" name="CustomerName" id="custname"><br>
        <label for="CustomerNumber">Customer Number</label>
        <input type="number" name="CustomerNumber" id="custnum" ><br>
        <label for="orderAmount">Order Amount</label>
        <input type="number" name="orderAmount" id="orderAmnt" ><br>
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



<button   type="button" class="btn btn-primary " id="edit-btn" disabled data-toggle="modal" data-target="#exampleModal1" >
  EDIT
</button>
<div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel1" aria-hidden="true">
  <div class="modal-dialog ">
    <div class="modal-content">
      <div class="modal-header">
        <h3 class="modal-title" id="exampleModalLabel1" style='display:inline;'>Edit Order</h3>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="edit" method="post" class="popup">
        <div>
        <label for="orderID1">Order Id</label>
        <input type="number" name="orderID1" id="orderid1" value="" readonly class="getvalue"><br>
        <label for="orderAmount1" >Order Amount</label>
        <input type="number" name="orderAmount1" id="orderAmnt1" onInput="checkAmount()"><br>
        <label for="Notes1">Notes</label>
        <input type="text" name="Notes1" id="notes1"><br>
        <label for="ApprovalBy">Approval By</label>
        
        
        <input type="text" name="ApprovalBy" id="approvalBy" value="" readonly><br>
        	<div class="button">
         <input type="submit" value="SUBMIT">
        </div>
        
        </div>
        </form>
      </div>
    </div>
  </div>
</div>

 <script>
   function handleCheckbox(checkbox) {
		var btnEdit = document.getElementById("edit-btn");
		var tableRow = checkbox.parentNode.parentNode.parentNode;
		if(checkbox.checked) {
			btnEdit.disabled = false;
			
			tableRow.classList.add("table-active");
			var inputField1 = document.getElementById("orderid1");
			var inputField2 = document.getElementById("orderAmnt1");
			var inputField3 = document.getElementById("notes1");
			inputField1.value = tableRow.cells[3].innerHTML;
			inputField2.value = tableRow.cells[6].innerHTML;
			inputField3.value = tableRow.cells[8].innerHTML;
			 var approvalBy = document.getElementById("approvalBy");
	           if(inputField2.value<=10000){
	        	   name="David_Lee";
	           }
	           else if((10000<inputField2.value) && (inputField2.value<=50000)){
	        	   name="Laura Smith";
	           }
	           else if(inputField2.value>50000){
	        	   name="Matthew Vance";
	           }
	           approvalBy.value= name;
			
		}
		else {
			btnEdit.disabled = true;
			
			tableRow.classList.remove("table-active");
		}
		var chk = checkbox.parentNode.parentNode.parentNode.parentNode;
		var checkList = chk.getElementsByTagName("input");
		for(var i = 0; i < checkList.length; i++) {
			if(checkList[i] != checkbox && checkbox.checked) {
				checkList[i].checked = false;
				checkList[i].parentNode.parentNode.parentNode.classList.remove("table-active");
			}
		}
	}
       
       function checkAmount() {
    	   var name=null;
    	   var amount = document.getElementById("orderAmnt1");
    	   console.log(amount);
           var approvalBy = document.getElementById("approvalBy");
           console.log(approvalBy);
           if(amount.value<=10000){
        	   name="David Lee";
           }
           else if((10000<amount.value) && (amount.value<=50000)){
        	   name="Laura Smith";
           }
           else if(amount.value>50000){
        	   name="Matthew Vance";
           }
           approvalBy.value= name;
        	   
       }

       
       function validateForm() {
    	   var x = document.forms["myForm"]["orderID"].value;
    	   var y = document.forms["myForm"]["orderDate"].value;
    	   var z = document.forms["myForm"]["CustomerNumber"].value;
    	   var a = document.forms["myForm"]["orderAmount"].value;
    	   if (x == ""||x==null||x<=0) {
    	     alert("Order Id must be positive number");
    	     return false;
    	   }
    	   if(y==''|| y==null || y<=0){
    		   alert("Wrong Date Input");
    		   return false;
    	   }
    	   if(z==''|| z==null || z<=0){
    		   alert("Wrong Number Input");
    		   return false;
    	   }
    	   if(a==''|| a==null || a<=0){
    		   alert("Wrong Amount Input");
    		   return false;
    	   }
    	 }
      
       </script>
  </c:if>
  
  <c:if test="${level=='Level 2' || level=='Level 3' }">
  <div style='display:inline;'>
  <form action="approve" method="post" style='display:inline;'>
  <input type="hidden" id="OrderId" name="orderid" value="" style='display:inline;' class="getvalue">
  <button onclick="getValue();return false;" type="submit" class="btn btn-primary disable" id="aprroveBtn" style='display:inline;'disabled>APPROVE</button>
  </form>
  
  <form action="reject" method="post" style='display:inline;'>
  <input type="hidden" id="OrderId1" name="orderid1" value="" style='display:inline;' class="getvalue">
  <button onclick="getValue();return false;" type="submit" class="btn btn-primary disable" id="rejectBtn" style='display:inline;' disabled>REJECT</button>
  </form>
  </div>
  
  	<script>
                		function handleCheckbox(checkbox) {
                			var tableRow = checkbox.parentNode.parentNode.parentNode;
                			var x= document.getElementsByClassName("disable");
                			if(checkbox.checked) {
                				x[0].disabled = false;
                				x[1].disabled = false;
                				tableRow.classList.add("table-active");
                				var inputField1 = document.getElementById("OrderId");
                				var inputField2 = document.getElementById("OrderId1");
                				inputField1.value = tableRow.cells[3].innerHTML;
                				inputField2.value = tableRow.cells[3].innerHTML;
                			}
                			else {
                				x[0].disabled = true;
                				x[1].disabled = true;
                				tableRow.classList.remove("table-active");
                			}
                			var chk = checkbox.parentNode.parentNode.parentNode.parentNode;
                			var checkList = chk.getElementsByTagName("input");
                			for(var i = 0; i < checkList.length; i++) {
                				if(checkList[i] != checkbox && checkbox.checked) {
                					checkList[i].checked = false;
                					checkList[i].parentNode.parentNode.parentNode.classList.remove("table-active");
                				}
                			}
                		}
                	</script>
  </c:if>
  
  <div class="search" style='display:inline;'>
  <form action="search" method="get" class="search" style='display:inline;'>
  <span class="icon"><i class="fa fa-search"></i></span>
		<input type="text" name="search" id="search" placeholder="Search">
	</form>
	</div>
  
      <table  width = "100%" class="table table-responsive-lg table-striped table-borderless table-hover w-auto">
      <thead style='border-bottom: 3px solid #fc7500;'>
         <tr>
            <th></th>
            <th>Order Date</th>
            <th>Approved By</th>
            <th>Order ID</th>
            <th>Customer Name</th>
            <th>Customer ID</th>
            <th>Order Amount</th>
            <th>Approval Status</th>
            <th>Notes</th>
         </tr>
      </thead>
      <c:set var = "id" value = "1000" scope = "session" />
         <c:forEach var = "row" items = "${resultList}">
         
            <tr>
            
               <td><label><input type="checkbox"  id="checkbox" onClick="handleCheckbox(this)"></label></td>
               <td><c:out value = "${row.order_date}"/></td>
               <td><c:out value = "${row.approved_by}"/></td>
               <td><c:out value = "${row.order_id}"/></td>
               <td><c:out value = "${row.cust_name}"/></td>
               <td><c:out value = "${row.cust_id}"/></td>
               <td><c:out value = "${row.order_amt}"/></td>
               <td><c:out value = "${row.appr_status}"/></td>
               <td><c:out value = "${row.notes}"/></td>
               
               
            </tr>
         </c:forEach>
      </table>

      <div class="pages">
    <div>
     
      <c:if test="${currentPage!=1}">
       
      <a class="page-link" href="employee?page=1" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
      
      </c:if>
    </div>
    <div>  
      <c:if test="${currentPage!=1}">
       
      <a class="page-link" href="employee?page=${currentPage-1 }" aria-label="Previous" ><</a>
     
      </c:if>
    </div>
    
    <h4> Page</h4>
    
    <div>
      
      <c:forEach begin="1" end="${noOfPages}" var="i">
      <c:choose>
      <c:when test="${currentPage eq i }">
      ${i} 
      </c:when>
      
      </c:choose>
      </c:forEach>
     
      
    </div>
      <h4> of ${noOfPages} </h4>
    <div>
       <c:if test="${currentPage lt noOfPages}">
       
      <a class="page-link" href="employee?page=${currentPage+1 }"aria-label="Next">></a>
        
      
      </c:if>
    </div>
    <div>
      <c:if test="${currentPage lt noOfPages}">
      
      <a class="page-link" href="employee?page=${noOfPages}"aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
        
     
      </c:if></div>
      <h4 class="text">
					Customers ${(currentPage - 1) * recordsPerPage + 1} - ${currentPage * recordsPerPage} of ${totalRows}
				</h4>
      
    </div>

      
    
 </div>
   </body>
</html>