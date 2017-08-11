<%-- 
    Document   : productupdate
    Created on : 26-jul-2017, 20:05:46
    Author     : Alsvid
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>index page</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div>
     
                               
                                <c:if test="${not empty errors}">
                                    <ul class="alert alert-danger">
                                        <c:forEach var="error" items="${errors}">
                                        <li><c:out value='${error}'/></li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                

                
				<form method="post" action="Controller?action=updateProductContinued">
					<p>
						<label for="productnaam">Name</label> <input type="text" id="productnaam"
							name="productnaam" value="<c:out value='${productPlaceHolder.productnaam}'/>">
					</p>
                                        
                                        <p>
						<label for="id">Id</label> <input type="text" id="id"
							name="id" value="<c:out value='${productPlaceHolder.id}'/>">
					</p>
                                        
                                        <p>
						<label for="aantal ">Amount</label> <input type="text" id="aantal"
							name="aantal" value="<c:out value='${productPlaceHolder.aantal}'/>">
					</p>
                                        
					<p>
						<label for="status">Status</label> <input type="text"
							id="status" name="status" value="<c:out value='${productPlaceHolder.status}'/>">
					</p>

					<input type="submit" id="submit" name="submit" value="Update product">
                                        <input type="hidden" name="productToUpdateIdContinued" value="<c:out value='${productPlaceHolder.id}'/>">
				</form>
                        
        </div>
    </body>
</html>
