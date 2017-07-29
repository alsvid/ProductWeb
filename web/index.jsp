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
        <header>
         <nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse fixed-top">
         <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="Controller?action=showProducts">Products</a></li>
                <li><a href="availableproducts.jsp">Available products</a></li>
            </ul>
        </div>
         </nav>
        </header>
        <div>
            
                       <c:choose>
                                <c:when test="${login || sessionScope.loginid != null}">
                                <c:if test="${error != null}">
                                    <ul class="alert alert-danger">
                                        <li>${error}</li>
                                    </ul>
                                </c:if>
                                <center>
                                <h1>Welcome</h1>
                                <h2 class="usergreeting"><c:out value='${sessionScope.loginid}' /></h2>
                                </center>
                                </c:when>
                                <c:otherwise>
                                <c:if test="${error != null}">
					<ul class="alert-danger">
						<li>${error}</li>
					</ul>
				</c:if>
                                
				<form method="post" action="Controller?action=login">
					<p>
                                            <label for="userid">User id</label><br> <input type="text" id="userid"
							name="userid" value="<c:out value='${person.userid}'/>">
					</p>
					<p>
                                            <label for="password">Password</label><br> <input type="password"
							id="password" name="password" value="<c:out value=''/>">
					</p>

					<input type="submit" id="log in" name="submit" value="Log in">
                                        <input type="submit" value="Register" name="Submit" formaction="register.jsp"/>
				</form>
                                </c:otherwise>
                            </c:choose>
                               
        </div>
    </body>
</html>
