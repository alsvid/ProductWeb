<%-- 
    Document   : header
    Created on : 11-aug-2017, 17:28:44
    Author     : Alsvid
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
         <nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse fixed-top">
         <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="Controller?action=defaulthandler">Home</a></li>
                <c:if test="${sessionScope.user.isAdministrator()}">
                <li><a href="Controller?action=showProducts">Products</a></li>
                </c:if>
                <c:if test="${login || sessionScope.loginid != null}">
                <li><a href="availableproducts.jsp">Available products</a></li>
                </c:if>
            </ul>
             <c:if test="${login || sessionScope.loginid != null}">
             <a href="Controller?action=logOut" class="btn btn-default navbutton">LOGOUT</a>
             </c:if>
             <c:if test="${sessionScope.user.isHelpdesk()}">
             <a href="Controller?action=setOnline" class="btn btn-success navbutton">ONLINE</a>
             <a href="Controller?action=setAway" class="btn btn-warning navbutton">AWAY</a>
             <a href="Controller?action=setOffline" class="btn btn-danger navbutton">OFFLINE</a>
             <form class="navform" method="post" action="Controller?action=setCustom">
             <input type="text" class="form-control navinput" id="userid" name="customstatus" placeholder="Custom status">
             <input class="btn btn-default navbutton" type="submit" id="customstatus" name="submit" value="SUBMIT">
             </form>
             </c:if>
            
        </div>
         </nav>
        </header>