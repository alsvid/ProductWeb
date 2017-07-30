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
	var table = "<table class='table-striped table-hover text-center table-center' id='myTable'><tr><th class='col-md-1 text-center'><b>Name</b></th><th class='col-md-1 text-center'><b>Status</b></th></tr>";

	for (x in serverResponse) {
		table += "<tr><td class='product" + x + "' " + "class='col-md-1 text-center'>"
				+ serverResponse[x].productnaam + "</td><td class='col-md-1 text-center'>"
				+ serverResponse[x].status + "</td></tr>";
	}
        
	table += "</table>";
	document.getElementById("productTable").innerHTML = table;
	setTimeout("getProducts()", 5000);
}
