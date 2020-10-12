function submitForget() {

    var mail = document.forms["myForm"]["mail"].value;
    var code = document.forms["myForm"]["code"].value;

    if (mail == null || mail == undefined || mail == "") {

        layer.msg("不是一个有效的 e-mail 地址");
        return false;
    }
    if (code == null || code == undefined || code == "") {

        layer.msg("验证码无效");
        return false;
    }
    if (mail.indexOf("@") < 1 || mail.lastIndexOf(".") < mail.indexOf("@") + 2
        || mail.lastIndexOf(".") + 2 >= mail.length) {
        layer.msg("不是一个有效的 e-mail 地址");
        return false;
    }
    $.ajax({
        url: "submitForget",
        data: {
            mail: mail,
            code: code,
        },
        success: function (data) {

            if (data == "OK") {

                layer.msg("您的信息已发送到您的邮箱");
            } else {

                layer.msg(data);
            }

        }

    })
}

function getCode() {

    var mail = getEmail();
    $.ajax({
        url: "getCode",
        data: {
            mail: mail,
        }, success: function (data) {

            if (data == "OK") {
                layer.msg("发送成功");
            } else {
                layer.msg("邮箱已存在");
            }

        }
    });
}

function getEmail(){

    var mail = document.forms["myForm"]["mail"].value;
    if (mail.indexOf("@") < 1 || mail.lastIndexOf(".") < mail.indexOf("@") + 2
        || mail.lastIndexOf(".") + 2 >= mail.length) {

        layer.msg(mail + " 不是一个有效的 e-mail 地址");
        return false;
    }

    return mail;
}

function getCodeByForget() {

    var mail = getEmail();
    $.ajax({
        url: "getCodeByForget",
        data: {
            mail: mail,
        }, success: function (data) {

            layer.msg(data);
            if (data == "OK") {
                layer.msg("发送成功");
            } else {
                layer.msg("邮箱不存在");
            }

        }
    });
}

function verifyCode() {

    var username = document.forms["myForm"]["username"].value;
    var password = document.forms["myForm"]["password"].value;
    var repass = document.forms["myForm"]["repass"].value;
    var mail = document.forms["myForm"]["mail"].value;
    var code = document.forms["myForm"]["code"].value;

    if (mail.indexOf("@") < 1 || mail.lastIndexOf(".") < mail.indexOf("@") + 2
        || mail.lastIndexOf(".") + 2 >= mail.length) {
        layer.msg(mail + " 不是一个有效的 e-mail 地址");
        return false;
    }

    if (username.length < 3 || password.length < 3) {

        layer.msg("用户名或密码错误");
        return false;
    }
    if (password != repass) {

        layer.msg("两次密码不一致");
        return false;
    }

    if (code.length != 4) {

        layer.msg("验证码有误");
        return false;
    }

    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        url: "verifyCode",
        type: "post",
        //contentType:'application/json;charset=utf-8',
        beforeSend: function (request) {
            request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
        },
        data: {
            username: username,
            password: password,
            mail: mail,
            code: code
        }, success: function (data) {
            layer.msg(data);
        },error:function (data) {
			layer.msg(data);
		}
    })
}