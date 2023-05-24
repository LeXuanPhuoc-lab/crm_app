$(document).ready(function(){
    $(".btn-delete-project").click(function(){
        var id = $(this).attr("projectID")
        var tag = $(this)

        $.ajax({
            url:"http://localhost:8080/crm_app/project/remove?id=" + id,
            method: "GET"
        }).done(function(){
            tag.closest("tr").remove()
        })
    })
})