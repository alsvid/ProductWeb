 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>index page</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div class="table-responsive div-center">
        <center>
        <h1>Products</h1>
        <div class="form-group">
        <input type="text" id="myInput" class="form-control form-control-70percent" onkeyup="myFunction()" placeholder="Search the list..">
        </div>
        </center>
        <table class="table-striped table-hover text-center table-center" id="myTable">
            <tr>
                <th class="col-md-1 text-center">Naam</th>
                <th class="col-md-1 text-center">Id</th>
                <th class="col-md-1 text-center">Status</th>
                <c:if test="${sessionScope.user.getRoleString() == 'administrator'}">
                <th class="col-md-1 text-center">&nbsp;</th>
                </c:if>
            </tr>
  
                <c:forEach var="product" items="${products}">
                    <tr>
                    
  
                        <td class="col-md-1">
                            <c:out value='${product.productnaam}'/>
                        </td>
                        
                        <td class="col-md-1">
                            <c:out value='${product.id}'/>
                        </td>
                        <c:choose>
                            <c:when test="${product.aantal > 10}">
                                <td class="col-md-1 success">
                                    <c:out value='${product.status}'/>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${product.aantal == 10}">
                                     <td class="col-md-1 warning">
                                            <c:out value='${product.status}'/>
                                    </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="col-md-1 danger">
                                            <c:out value='${product.status}'/>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${sessionScope.user.getRoleString() == 'administrator'}">
                         <td class="col-md-1">
                            <a href="Controller?action=updateProduct&productIdToUpdate=<c:out value='${product.id}'/>" class="btn">Update</a>
                        </td>
                        </c:if>

                    </tr>
                </c:forEach>
            </tr>
        </table>
            <div id="testdiv">
                
            </div>
        <center>
        <a href="addproduct.jsp" class="btn btn-success" role="button">Create new product</a>
        </center>
        </div>
         <script src="assets/js/jquery-3.2.1.min.js"></script>
         <script src="assets/js/searchbarscript.js"></script>
    </body>
</html>

