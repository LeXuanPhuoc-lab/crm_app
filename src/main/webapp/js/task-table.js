$(document).ready(function(){
    $(".btn-delete-task").click(function(){
        var id = $(this).attr("taskID")
        var tag = $(this)

        $.ajax({
            url: "http://localhost:8080/crm_app/task/remove?id=" + id,
            method: "GET"
        }).done(function(){
            tag.closest("tr").remove()
        })
    })
})