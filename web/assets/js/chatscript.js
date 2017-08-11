
var person;
var activated = false;

$(function() {
    $(".chatPanelSubmit").on("click", function() {
        if (person !== null) {
            var data = {
                "person" : person,
                "message" : $(".msg_input").val()
            };
            $.ajax({
                type: "POST",
                data: {
                    data: JSON.stringify(data)
                },
                dataType: "JSON",
                url: "Controller?action=newConversation",
                success: function(response) {
                    $(".msg_input").val("");
                    $(".msg_insert").text("");
                    for (i in response) {
                        if (response[i].writer == person) {
                            $(".msg_insert").append("<div class='msg_a'>" + response[i].content + "</div>");
                            }
                            else {
                            $(".msg_insert").append("<div class='msg_b'>" + response[i].content + "</div>");
                            }
                    }
                }
            });
        }
        else {
            $(".msg_head").text("No conversation");
        }
        
        $(".msg_head").val("");
    });
    

    
    $(document).on(
        "click",
	".user",
            function() {
		var myClass = $(this).attr("id");
		person = myClass;
                $(".msg_head").show();
                $(".msg_wrap").show();
                $(".msg_box").show();
                $(".msg_insert").text("");
                $(".msg_head").text("");
		$(".msg_head").append(person);
	chatActivate();
    });
});

function chatActivate() {
	if (!activated) {
		getChat();
		activated = true;
	}
};

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
			$(".msg_insert").text("");
                        $(".msg_head").text("");
			$(".msg_head").text(person);
                        for (i in response) {
                            if (response[i].writer == person) {
                            $(".msg_insert").append("<div class='msg_a'>" + response[i].content + "</div>");
                            }
                            else {
                            $(".msg_insert").append("<div class='msg_b'>" + response[i].content + "</div>");
                            }
                        }
		},
		fail : function() {
			$(".msg_head").text("Failed connection");
		}
	});
	setTimeout(getChat, 3000);

};

$.fn.scrollBottom = function() { 
  return $(document).height() - this.scrollTop() - this.height(); 
};