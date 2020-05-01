
$(function () {

    $('#switch_qlogin').click(function () {
        $('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_bottom').animate({left: '0px', width: '70px'});
        $('#qlogin').css('display', 'none');
        $('#web_qr_login').css('display', 'block');

    });
    $('#switch_login').click(function () {

        $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_bottom').animate({left: '154px', width: '70px'});

        $('#qlogin').css('display', 'block');
        $('#web_qr_login').css('display', 'none');
    });
    if (getParam("a") == '0') {
        $('#switch_login').trigger('click');
    }

});

function logintab() {
    scrollTo(0);
    $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
    $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
    $('#switch_bottom').animate({left: '154px', width: '96px'});
    $('#qlogin').css('display', 'none');
    $('#web_qr_login').css('display', 'block');

}


//根据参数名获得该参数 pname等于想要的参数名 
function getParam(pname) {
    var params = location.search.substr(1); // 获取参数 平且去掉？ 
    var ArrParam = params.split('&');
    if (ArrParam.length == 1) {
        //只有一个参数的情况 
        return params.split('=')[1];
    }
    else {
        //多个参数参数的情况
        for (var i = 0; i < ArrParam.length; i++) {
            if (ArrParam[i].split('=')[0] == pname) {
                return ArrParam[i].split('=')[1];
            }
        }
    }
}


var reMethod = "GET",
    pwdmin = 6;

$(document).ready(function () {

    //添加弹出层
    layui.use(['layer'], function () {
        $ = layui.jquery;//jquery
        layer = layui.layer;//弹出层

    });

    //注册校验
    $('#reg').click(function () {
        var username = $('#user').val();
        if (username == "") {
            $('#user').focus().css({
                border: "1px solid red",
                boxShadow: "0 0 2px red"
            });
            $('#userCue').html("<font color='red'><b>×用户名不能为空</b></font>");
            return false;
        }

        if ($('#user').val().length < 4 || $('#user').val().length > 16) {

            $('#user').focus().css({
                border: "1px solid red",
                boxShadow: "0 0 2px red"
            });
            $('#userCue').html("<font color='red'><b>×用户名位4-16字符</b></font>");
            return false;

        }
        var password = $('#passwd').val();
        if (password.length < pwdmin) {
            $('#passwd').focus();
            $('#userCue').html("<font color='red'><b>×密码不能小于" + pwdmin + "位</b></font>");
            return false;
        }
        if ($('#passwd2').val() != $('#passwd').val()) {
            $('#passwd2').focus();
            $('#userCue').html("<font color='red'><b>×两次密码不一致！</b></font>");
            return false;
        }
        var email = $('#email').val();
        var emailReg = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!emailReg.test(email)) {
            $('#email').focus().css({
                border: "1px solid red",
                boxShadow: "0 0 2px red"
            });
            $('#userCue').html("<font color='red'><b>×邮箱格式不正确</b></font>");
            return false;
        } else {
            $('#email').css({
                border: "1px solid #D7D7D7",
                boxShadow: "none"
            });

        }

        $.ajax({
            type: "POST",
            url: "/register",
            data: {
                'username': username,
                'email': email,
                'password': password,
            },
            dataType: "text", //return dataType: text or json
            success: function (json) {
                if (json == "success") {
                    window.open("/","_self")
                }else if(json == "username_repeat") {
                    layer.msg("用户名重复", {icon: 5, time: 1000});
                }else if(json == "email_repeat") {
                    layer.msg("邮箱已被注册", {icon: 5, time: 1000});
                }else {
                    layer.msg("邮箱失败，请检查网络", {icon: 5, time: 1000});
                }
            },
            error: function (json) {
                layer.msg("注册失败，请检查网络", {icon: 5, time: 1000});
            }
        });
    });

    //登录校验
    $('#log').click(function () {
        var username = $('#u').val();
        if (username == "") {
            layer.msg("账号不能为空", {icon: 5, time: 1000});
            return false;
        }

        var password = $('#p').val();
        if (password=="") {
            layer.msg("密码不能为空", {icon: 5, time: 1000});
            return false;
        }

        $.ajax({
            type: "POST",
            url: "/login",
            data: {
                'username': username,
                'password': password,
            },
            dataType: "text", //return dataType: text or json
            success: function (json) {
                if (json == "success") {
                    window.open("/","_self")
                }else if(json == "username_error") {
                    layer.msg("账户不存在", {icon: 5, time: 1000});
                }else if(json == "user_no") {
                    layer.msg("账户已封禁", {icon: 5, time: 1000});
                }else if(json == "password_error") {
                    layer.msg("密码错误", {icon: 5, time: 1000});
                }else {
                    layer.msg("登录失败，请检查网络", {icon: 5, time: 1000});
                }
            },
            error: function (json) {
                layer.msg("登录失败，请检查网络", {icon: 5, time: 1000});

            }
        });
    });

});