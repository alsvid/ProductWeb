<%-- 
    Document   : register
    Created on : 26-jul-2017, 17:58:31
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
                                

                
				<form method="post" action="Controller?action=register">
					<p>
                                            <label for="userid">User id</label><br> <input type="text" id="userid"
							name="userid" value="<c:out value=''/>">
					</p>
                                        
                                        <p>
                                            <label for="firstname">First name</label><br> <input type="text" id="firstname"
							name="firstName" value="<c:out value=''/>">
					</p>
                                        
                                        <p>
                                            <label for="lastname1">Last name</label><br> <input type="text" id="lastname"
							name="lastName" value="<c:out value=''/>">
					</p>
                                        
					<p>
                                            <label for="password1">Password</label><br> <input type="password"
							id="password" name="password" value="<c:out value=''/>">
					</p>

					<input type="submit" id="submit" name="submit" value="Submit registration">
                                  
				</form>
                        
        </div>
    </body>
</html>
