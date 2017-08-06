var xHRObject = new XMLHttpRequest();
var serverResponse;
function getHelpdeskmembers() {
	xHRObject.open("GET", "Controller?action=getHelpdeskmembers", true);
	xHRObject.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			createHelpdeskLayout();
		}
	};
	xHRObject.send(null);
}

function createHelpdeskLayout() {
	serverResponse = JSON.parse(xHRObject.responseText);
	var table = "<table class='helpdesktable' id='helpdesktable'>";

	for (x in serverResponse) {
            table += "<tr><td><div class='userid" + x + "'>"
            + serverResponse[x].userid + "</div></td>"
            + "<td>" + serverResponse[x].status + "</td>"
            + "<td><button type='button' id='" + serverResponse[x].status + "' class='startButton" + x + "'</button>";
	}
        
	table += "</table>";
	document.getElementById("helpdeskmemberlist").innerHTML = table;
	setTimeout("getHelpdeskmembers()", 5000);
}
