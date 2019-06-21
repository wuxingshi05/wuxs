$(document).ready(function(){
    //获取验证码
    $("#getCode").click(function () {

        var email = $("#email").val();
        if (email != null && email != ""){
            $("#emailMsg").text("");
            var that = $(this);
            var timeo = 60;
            var timeStop = setInterval(function(){
                timeo--;
                if (timeo>0) {
                    that.val('重新发送' + timeo +'s');
                    that.attr('disabled','disabled');//禁止点击
                }else{
                    timeo = 60;//当减到0时赋值为60
                    that.val('获取验证码');
                    clearInterval(timeStop);//清除定时器
                    that.removeAttr('disabled');//移除属性，可点击
                    $("#msg").val("");
                }
            },1000);

            $.ajax({
                cache: true,
                type: "GET",
                url: '/getCode',
                data: {"addto":email},
                async: false,
                error: function() {

                },
                success: function(data) {
                    $("#msg").val(data);
                }
            });
        } else {
            $("#emailMsg").text("请输入邮箱！");
        }
    })
    //注册
    $('form').submit(function () {
        $.ajax({
            cache: true,
            type: "GET",
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