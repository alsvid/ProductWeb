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
                var header = document.createElement('div');
                var headerclass = header.setAttribute('class','panel-heading');
                var panelbody = document.createElement('div');
                var panelbodyclass = panelbody.setAttribute('class','panel-body');
		var node1 = document.createTextNode("Comment by "
				+ serverResponse[x]["writer"]);
		var node2 = document.createTextNode(serverResponse[x]["content"]);		
		var writerparagraph = document.createElement("h3");
                var writerparagraphclass = writerparagraph.setAttribute('class','panel-title');
                var contentparagraph = document.createElement("p");
                var panelsuccess = document.createElement('div');
                var panelsuccessclass = panelsuccess.setAttribute('class','panel panel-success panelresizeclass');

		writerparagraph.appendChild(node1);
                contentparagraph.appendChild(node2);
                header.appendChild(writerparagraph);
                panelbody.appendChild(contentparagraph);
                panelsuccess.appendChild(header);
                panelsuccess.appendChild(panelbody);
                commentDiv.appendChild(panelsuccess);
	}
};

function sendMessage(subjectid) {
	var comment = document.getElementById("inputFieldComment" + subjectid).value
			+ "::" + document.getElementById("inputFieldName" + subjectid).value
			+ "::" + subjectid;

	document.getElementById("inputFieldComment" + subjectid).value = "";
	document.getElementById("inputFieldComment" + subjectid).value = "Anonymous"
        document.getElementById("inputFieldName" + subjectid).value = "";
        document.getElementById("inputFieldName" + subjectid).placeholder = "Leave a comment here...";

	webSocket.send(comment);
};
