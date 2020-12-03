<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
 <html>
 <head>
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
      <img src="images/abc-logo.png" alt="logo" width="210";>
      </div>
   </div>
 
<c:set var="categories" value="${sessionScope.categories}" />
<c:set var="rowsPerPage" value="10"/>
<c:set var="pageNumber" value="${param.pageNumber}"/>

<c:set var="a">
    <fmt:formatNumber value="${categories.rowCount/rowsPerPage}" maxFractionDigits="0"/>
</c:set>
 
<c:set var="b" value="${categories.rowCount/rowsPerPage}" />
 
 
<c:choose>
    <c:when test="${a==0}">
        <c:set var="numberOfPages" value="1" scope="session"/>   
    </c:when>
 
    <c:when test="${b>a}">
        <c:set var="xxx" value="${b%a}"/>
        <c:if test="${xxx>0}">
            <c:set var="numberOfPages" value="${b-xxx+1}" scope="session"/>   
        </c:if>
    </c:when>
 
    <c:when test="${a>=b}">
        <c:set var="numberOfPages" value="${a}" scope="session"/>    
    </c:when>
</c:choose>
 
<c:set var="start" value="${pageNumber*rowsPerPage-rowsPerPage}"/>
<c:set var="stop" value="${pageNumber*rowsPerPage-1}"/>
<div class="board">
  hii
      <table  width = "100%" class="table table-responsive-lg table-striped table-borderless table-hover w-auto">                   
        <c:set var="columns" value="0" scope="session"/>
        <c:forEach items="${categories.columnNames}" var="name">
            <c:set var="columns" value="${columns+1}"/>
            <td bgcolor="white"><c:out value="${name}"/></td>
        </c:forEach>
        <c:set var="columns" value="${columns-1}"/>
 
        <c:forEach items="${categories.rowsByIndex}" var="row" begin="${start}" end="${stop}">
            <tr>
                <c:forEach begin="0" end="${columns}" var="x">
                    <td><c:out value="${row[x]}"/></td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    <div class="pages">
    <div>
    <c:if test="${pageNumber!= 1}">
    <a href="dash1.jsp?pageNumber=1"> << </a>
    </c:if>
    </div>
    <div>
        <%--For displaying Previous link --%>
    <c:if test="${pageNumber gt 1}">
        <a href="dash1.jsp?pageNumber=${pageNumber - 1}"> <</a>
    </c:if>
    </div>
    <div>
    Page
    </div>
    <div>
    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${i!=pageNumber}">
            
                <a href="dash1.jsp?pageNumber=<c:out value="${i}"/>"><c:out value="${i}"/></a>
            </c:when>
            
            <c:otherwise>
                <c:out value="${i}"/>
            </c:otherwise>        
        </c:choose>       
    </c:forEach> 
   </div>
    <div>
    <%--For displaying Next link --%>
    <c:if test="${pageNumber lt numberOfPages}">
        <a href="dash1.jsp?pageNumber=${pageNumber + 1}">></a>
    </c:if>
    </div>
    <div>
     <c:if test="${pageNumber <= numberOfPages}">
     <a href="dash1.jsp?pageNumber=${numberOfPages}">>></a>
     </c:if>
     </div>
    </div>
</div>
</body>
</html>