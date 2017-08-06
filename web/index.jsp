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
    
    <c:if test="${user != null}">
	<div class="chatPanel">
		<h3 class="chatPanelTitle">Click on 'Click here' in your
			friendslist to start a conversation.</h3>
		<input type="text" class="chatPanelInputBox"> <input
			type="submit" class="chatPanelSubmit" value="Stuur bericht">
		<p class="chatPanelChat"></p>
	</div>
	
	<p class="toggleChat">
		Toon/verberg <br> Chatpaneel
	</p>
    </c:if>
    
    <body>
        <header>
         <nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse fixed-top">
         <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">Home</a></li>
                <li><a href="Controller?action=showProducts">Products</a></li>
                <li><a href="Controller?action=showHelpdesk">Helpdesk accounts</a></li>
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
        <div class="blog-div">
            <h3>Let us know your opinion on these subjects...</h3>
            <c:forEach var="subject" items="${subjects}">
                <h4 id="subject${subject.subjectid}">${subject.subject}</h4>
                <div id="comments${subject.subjectid}">
                    <c:forEach var="comment" items="${subject.commentlist}">
                    <p class="comment-writer">
                        ${comment.writer}
                    </p>
                    <p class="comment-content">
                        ${comment.content}
                    </p>
                    </c:forEach>
                </div>
                <input type="text" id="inputFieldComment${subject.subjectid}" required="required" value="<c:out value='${sessionScope.loginid}' />">
                <input type="text" id="inputFieldName${subject.subjectid}" required="required" placeholder="Leave a comment here...">
                <input type="submit" id="Submit${subject.subjectid}" value="Submit" onclick="sendMessage(${subject.subjectid})">
            </c:forEach>
        </div>
        
        <div id="helpdeskmemberlist">
            <table id="helpdesktable" class="helpdesktable" varStatus="loop">
                <c:forEach var="member" items="${helpdeskmembers}">
                <tr>
                    <td class="helpdesktablerow">
                        <div class="userid${loop.index}">
                            ${member.userid}
                        </div>
                    </td>
                    <td>
                        ${member.status}
                    </td>
                    <td>
                        <button type="button" id="${member.status}" class="startButton${loop.index}">Click here</button>
                    </td>
                </tr>
                </c:forEach>
        </div>
        <script src="assets/js/websocketblog.js"></script>
        <script src="assets/js/helpdeskmemberlist.js"></script>
        <script src="assets/js/chatscript.js"></script>
        <script src="assets/js/jquery-3.2.1.min.js"></script>
    </body>
</html>
