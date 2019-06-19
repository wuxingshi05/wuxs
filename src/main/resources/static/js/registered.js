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
});