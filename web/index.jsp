<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:choose>
            <c:when test="${sessionScope.role == 'client' || sessionScope.role == 'administrator'}">
                <body onload="getHelpdeskmembers()">
            </c:when>
            <c:otherwise>
                <body onload="getOpenconversations()">
            </c:otherwise>
        </c:choose>
            <jsp:include page="header.jsp"></jsp:include>
            <c:if test="${sessionScope.user == null}">
                <div class="well logininfowell">
                    <h4>Administrator account</h4>
                    <p>Name: brechttheys</p>
                    <p>Password: admin</p>
                    <br>
                    <h4>Helpdesk account</h4>
                    <p>Name: Helpdesk-Danny</p>
                    <p>Password: helpdesk</p>
                    <br>
                    <h4>Client account</h4>
                    <p>Name: Rach</p>
                    <p>Password: client</p>
                </div>
            </c:if>
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
                                <center>
				<form method="post" action="Controller?action=login">
					<p>
                                            <label for="userid">User id</label><br> <input type="text" class="form-control bloginput" id="userid"
							name="userid" value="<c:out value='${person.userid}'/>">
					</p>
					<p>
                                            <label for="password">Password</label><br> <input type="password"
							class="form-control bloginput" id="password" name="password" value="<c:out value=''/>">
					</p>

					<input class="btn btn-default" type="submit" id="log in" name="submit" value="Log in">
                                        <input class="btn btn-default" type="submit" value="Register" name="Submit" formaction="register.jsp"/>
				</form>
                                </center>
                                </c:otherwise>
                            </c:choose>
                               
        </div>
    <center>
        <c:if test="${sessionScope.user == null}">
            <a href="Controller?action=defaulthandler" class="btn btn-success hideshowblogbutton" style="width: 50%;margin-top: 2%;">Show blogs</a>
        </c:if>
        <div class="blog-div">
            <c:if test="${login || sessionScope.loginid != null}">
            <h3>Let us know your opinion on these subjects...</h3>
            </c:if>
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
                <input type="text" id="inputFieldComment${subject.subjectid}" required="required" class="form-control bloginput" value="Anonymous">
                <textarea type="text" id="inputFieldName${subject.subjectid}" required="required" class="form-control bloginputtextarea" placeholder="Leave a comment here..."></textarea>
                <input type="submit" id="Submit${subject.subjectid}" value="Submit" class="btn btn-lg btn-success blogbutton" onclick="sendMessage(${subject.subjectid})">
            </c:forEach>
        </div>
    </center>
        </main>
        <c:if test="${login || sessionScope.loginid != null}">
        <div id="openConversations">
            
        </div>
        </c:if>
        <c:if test="${login || sessionScope.loginid != null}">
         <c:if test="${!sessionScope.user.isHelpdesk()}">
         <div class="chat_box">
            <div class="chat_head">
                Helpdesk
            </div>
            <div class="chat_body">
                <div id="helpdeskmemberlist">
                <table id="helpdesktable" class="helpdesktable" varStatus="loop">
                <c:forEach var="member" items="${helpdeskmembers}">
                <tr>
                    <td class="helpdesktablerow">
                        <div id="user" class="userid${loop.index}">
                            ${member.userid}
                        </div>
                    </td>
                    <td>
                        ${member.status}
                    </td>
                    <td>
                        <button type="button" id="${member.userid}" class="startButton${loop.index}">Click here</button>
                    </td>
                </tr>
                </c:forEach>
            </table>
            </div>
            </div>
        </div>
         </c:if>
        <div class="msg_box" style="right:290px">
            <div class="msg_head">
                <div class="close">X</div>
            </div>
            <div class="msg_wrap">
            <div class="msg_body">
                <div class="msg_insert"></div>
            </div>
            <div class="msg_footer">
                <textarea class="msg_input" rows="4"></textarea>
                <input id="btn btn-default" style="width: 100%;" type="submit" class="chatPanelSubmit" value="Submit">
            </div>
            </div>
        </div>
        </c:if>
        <script src="assets/js/websocketblog.js"></script>
        <c:if test="${!sessionScope.user.isHelpdesk()}">
        <script src="assets/js/helpdeskmemberlist.js"></script>
        </c:if>
        <script src="assets/js/jquery-3.2.1.min.js"></script>
        <script src="assets/js/chatscript.js"></script>
        <script src="assets/js/chatboxscript.js"></script>
        <c:if test="${sessionScope.user.isHelpdesk()}">
        <script src="assets/js/openconversations.js"></script>
        </c:if>
        <script src="assets/js/start.js"></script>
    </body>
</html>
