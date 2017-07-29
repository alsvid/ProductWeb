var xHRObject = new XMLHttpRequest();
var refreshbutton = document.getElementById('refreshbutton');
refreshbutton.onclick = getNewQuote;

function getRefreshedData () {
	xHRObject.open("GET", "Controller?action=refreshProductlist", true);
	xHRObject.onreadystatechange = getData;
	xHRObject.send(null);
}

function getData () {
	if (xHRObject.status == 200) {
		if (xHRObject.readyState == 4) {
			var serverResponse = JSON.parse(xHRObject.responseText);
			var product = serverResponse.product; // of je kan ook doen: serverResponse["quote"]
	
			var testDiv = document.getElementById("testdiv");
			var productTableRow = testDiv.childNodes[0];
			
			if (productTableRow == null) {
				productTableRow = document.createElement('tr');
                                productTableRow.appendChild('td');
				productTableRow.id = "testid";
				var quoteText = document.createTextNode(quote);
				quoteParagraph.appendChild(quoteText);
				quoteDiv.appendChild(quoteParagraph);
			}
			else {
				var quoteText = document.createTextNode(quote);
				quoteParagraph.removeChild(quoteParagraph.childNodes[0]);
				quoteParagraph.appendChild(quoteText);
			}	
		}
	}
}var quotebutton = document.getElementById('quotebutton');
quotebutton.onclick = getNewQuote;
