var webSocket = new WebSocket("ws://localhost:8084/ProductWeb/subjectendpoint");

webSocket.onmessage = function(event) {
	var serverResponse = JSON.parse(event.data);
	for (var i = 1; i < 4; i++) {
		document.getElementById("comments" + i).innerHTML = "";
	}
	for (x in serverResponse) {
		var commentDiv = document.getElementById("comments"
				+ serverResponse[x]["commentid"]);
		var commentwriter = commentDiv.childNodes[1];
                var commentcontent = commentDiv.childNodes[0];

		var node1 = document.createTextNode("Comment by "
				+ serverResponse[x]["writer"]);
		var node2 = document.createTextNode(serverResponse[x]["content"]);		
		var writerparagraph = document.createElement("p");
                var contentparagraph = document.createElement("p");

		writerparagraph.appendChild(node1);
                contentparagraph.appendChild(node2);
		commentDiv.appendChild(writerparagraph);
                commentDiv.appendChild(contentparagraph);
	}
};

function sendMessage(subjectid) {
	var comment = document.getElementById("inputFieldComment" + subjectid).value
			+ "::" + document.getElementById("inputFieldName" + subjectid).value
			+ "::" + subjectid;

	document.getElementById("inputFieldComment" + subjectid).value = "";
	document.getElementById("inputFieldComment" + subjectid).value = "Leave your name here..."
        document.getElementById("inputFieldName" + subjectid).value = "";
        document.getElementById("inputFieldName" + subjectid).placeholder = "Leave a comment here...";

	webSocket.send(comment);
};
