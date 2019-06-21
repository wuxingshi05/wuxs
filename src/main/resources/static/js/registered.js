$(document).ready(function(){
    $('form').submit(function () {
        $.ajax({
            cache: true,
            type: "POST",
            url: '/registered',
            data:$('#formData').serialize(),
            async: false,
            error: function() {

            },
            success: function() {

            }
        });
    });

    $("#code").click(function () {
        var email = $("#email").val();
        $.ajax({
            cache: true,
            type: "GET",
            url: '/getCode',
            data: {"addto":email},
            async: false,
            error: function() {

            },
            success: function() {

            }
        });
    })
});