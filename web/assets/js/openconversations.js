var xHRObject = new XMLHttpRequest();
var serverResponse;
function getOpenconversations() {
	xHRObject.open("GET", "Controller?action=refreshOpenconversations", true);
	xHRObject.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			createTable();
		}
	};
	xHRObject.send(null);
}

function createTable() {
	serverResponse = JSON.parse(xHRObject.responseText);
	var table = "<table class='table-striped table-hover text-center table-center' id='myTable'>";

	for (x in serverResponse) {
		table += "<tr class='user_row'><td class='user alert alert-info' id='" + serverResponse[x].person1.userid + "'>"
				+ serverResponse[x].person1.userid + " needs your help </td></tr>";
                       
                
	}
        
	table += "</table>";
	document.getElementById("openConversations").innerHTML = table;
	setTimeout("getOpenconversations()", 5000);
}