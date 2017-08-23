$(document).ready(function(){
    
    $(".chat_body").hide();
    $(".msg_wrap").hide();
    $(".msg_head").hide();
    
    $(".chat_head").click(function() {
        $(".chat_body").slideToggle('slow');
        
    });
   
    $(".msg_head").click(function() {
        $(".msg_wrap").slideToggle('slow');
    });
    
    
    $(".close").click(function() {
        $(".msg_box").hide();
        
    });
    
    $("#user").click(function() {
        $(".msg_head").show();
        $(".msg_wrap").show();
        $(".msg_box").show();
    });
    
   $(".msg_input").keypress(function(e) {
       if (e.keyCode == 13) {
           var msg = $(this).val();
           $("<div class='msg_b'>"+msg+"</div>").insertBefore(".msg_insert");
           $(this).val("");
           $(".msg_body").scrollTop($(".msg_body")[0].scrollHeight);
       }
   });
   
    $(document).on(
        "click",
	".hideshowblogbutton",
            function() {
                $(".hideshowblogbutton").hide();
    });
        
});