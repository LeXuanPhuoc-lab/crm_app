$(document).ready(function(){
    $(".btn-delete-role").click(function(){
        var id = $(this).attr("roleId")
        var tag = $(this)

        $.ajax({
            url: "http://localhost:8080/crm_app/role/remove?id=" + id,
            method: "GET",
        }).done(function(){
            tag.closest("tr").remove()
        })
    })
})