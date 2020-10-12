/*$("#eye").clike(function() {

	this.toggleClass('fa fa-eye-slash');
	this.toggleClass('fa fa-eye');
})*/

$("#eye").on('click', function () {

    var thisInput = $(this).parent().children('input');
    var types = $(thisInput).attr('type');
    if (types === "password") {

        $(this).attr("class", 'fa fa-eye');
        $(thisInput).attr("type", "text");
    } else {

        $(this).attr("class", 'fa fa-eye-slash');
        $(thisInput).attr("type", "password");

    }

})  //把点击事件绑定到id为id的子级元素类名为div的元素身上


function regist() {

    location.href = "register";
}


function login() {

    var password = document.forms["myForm"]["password"].value;
    var username = document.forms["myForm"]["username"].value;

    if (password.length < 1) {

        layer.msg("密码输入错误");
        return false;
    }

    if (username == null || username == undefined || username == "") {

        layer.msg("账号输入错误");
        return false;

    }

    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");


    $.ajax({
        url: "sign/loginUser",
        type: "POST",
        beforeSend: function (request) {
            request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
        },
        data: {
            username: username,
            password: password
        },
        success: function (data) {

            if (data == "OK") {

                layer.msg("登陆成功");
                location.href = "home";
            }

            return false;
        }
    });
}


/*function verfiyN() {



	$('#inputV').show("密码不得少于6位数");
}*/