$(document).ready(function(){
    // way 1 to remove
    $(".btn-delete-user").click(function(){
        var id = $(this).attr("userID")
        var tag = $(this)

        $.ajax({
          method: "GET",
          url: "http://localhost:8080/crm_app/user/remove?id=" + id,

        }).done(function( result ) {
            // removse parent of particular delete-btn 'tag'
            tag.closest("tr").remove()
          });
    })

})


// way 2 to remove

//document.addEventListener("DOMContentLoaded", function(){

//    document.querySelector(".btn-delete-user").onclick(function(this){
//        var id = this.attr("userID")
//        var tag = this
//        $.ajax({
//          method: "GET",
//          url: "http://localhost:8080/crm_app/user/remove?id=" + id,
//
//        }).done(function( result ) {
//            // remove parent of particular delete-btn 'tag'
//            tag.closest("tr").remove()
//          });
//
//    })

//});
