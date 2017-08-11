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
            table += "<tr class='user_row'><td><div id='" + serverResponse[x].userid + "' class='user'>"
            + serverResponse[x].userid + "</div></td>"
            + "<td><div id='" + serverResponse[x].userid + "' class='user'>" + serverResponse[x].status + "</div></td></tr>";
	}
        
	table += "</table>";
	document.getElementById("helpdeskmemberlist").innerHTML = table;
	setTimeout("getHelpdeskmembers()", 6000);
}
