var person;
var activated = false;

$(function() {
    $(".chatPanelSubmit").on("click", function() {
        if (person != null) {
            var data = {
                "person" : person,
                "message" : $(".chatPanelInputBox").val()
            }
            $.ajax({
                type: "POST",
                data: {
                    data: JSON.stringify(data)
                },
                dataType: "JSON",
                url: "Controller?action=newConversation",
                success: function(response) {
                    $(".chatPanelChat").text("");
                    for (i in response) {
                        $(".chatPanelChat").append("<p>" + response[i].writer + ": " + response[i].content + "</p>");
                    }
                }
            });
        }
        else {
            $(".chatPanelTitle").text("Start a conversation with one of our employees");
        }
        
        $(".chatPanelTitle").val("");
    });
    
    (".toggleChat").on("click", function() {
        $(".chatPanel").animate({
            width: 'toggle'
        }, 300);
    });
    
    $(document).on(
        "click",
	":button",
            function() {
		var myClass = $(this).attr("class");
		var index = myClass.replace(/^\D+/g, '');
		person = serverResponse[index].userid;

		$(".chatPanel").animate({
		width : 'show'
		}, 300);
	chatActivate();
    });
});

function chatActivate() {
	if (!activated) {
		getChat();
		activated = true;
	}
}

function getChat() {
	var data = {
		"person" : person
	};

	$.ajax({
		type : "GET",
		data : {
			data : JSON.stringify(data)
		},
		dataType : "JSON",
		url : "Controller?action=getConversation",
		success : function(response) {
			$(".chatPanelChat").text("");
			$(".chatPanelTitle").text("Connected with " + person);
			for (i in response) {
				$(".chatPanelChat").append("<p>" + response[i].writer + ": " + response[i].content + "</p>");
			}
		},
		fail : function() {
			$(".chatPanelTitle").text("Failed connection");
		}
	});
	setTimeout(getChat, 3000);

}