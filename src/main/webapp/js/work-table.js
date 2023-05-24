$(document).ready(function(){
    $(".btn-delete-work").click(function(){
        var id = $(this).attr("workID")
        var tag = $(this)

        $.ajax({
            url: "http://localhost:8080/crm_app/work/remove?id=" + id,
            method: "GET"
        }).done(function(){
            tag.closest("tr").remove()
        })
    })
})