<%-- 
    Document   : availableproducts
    Created on : 29-jul-2017, 18:41:03
    Author     : brech
--%>
 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>index page</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
    </head>
    <body onload="getProducts()">
        <header>
         <nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse fixed-top">
         <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="Controller?action=showProducts">Products</a></li>
            </ul>
        </div>
         </nav>
        </header>
        <div class="table-responsive div-center">
        <center>
        <h1>Products</h1>
        <div class="form-group">
        <input type="text" id="myInput" class="form-control form-control-70percent" onkeyup="myFunction()" placeholder="Search the list..">
        </div>
        </center>
        <center>
            <div id="productTable" class="table-responsive div-center"></div>
        </center>
        </div>
         <script src="assets/js/jquery-3.2.1.min.js"></script>
         <script src="assets/js/searchbarscript.js"></script>
         <script src="assets/js/asynchronous.js"></script>
    </body>
</html>


