var xHRObject = new XMLHttpRequest();
var serverResponse;
function getProducts() {
	xHRObject.open("GET", "Controller?action=refreshProductlist", true);
	xHRObject.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			createTable();
		}
	};
	xHRObject.send(null);
}

function createTable() {
	serverResponse = JSON.parse(xHRObject.responseText);
	var table = "<table><tr><th><b>Name</b></th><th><b>Status</b></th></tr>";

	for (x in serverResponse) {
		var visible = false;
		if (serverResponse[x].aantal !== 0) {
			visible = true;
		}
		table += "<tr><td class='product" + x + "'>"
				+ serverResponse[x].productnaam + "</td><td>"
				+ serverResponse[x].status + "</td></tr>";
	}

	table += "</table>";
	document.getElementById("productTable").innerHTML = table;
	setTimeout("getProducts()", 5000);
}
