function upload() {

    layer.open({
        type: 2,
        content: "looo",
        area: ["100%", "100%"]
    })
}

function logout(){
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        url:"logout",
        type:"post",
        beforeSend: function (request) {
            request.setRequestHeader(csrfHeader, csrfToken); // 添加  CSRF Token
        },
        success:function () {
            location.href = "loginPage";
        }

    })
}


function seek() {
    var seek = $("#seek").val();
    if (seek.length < 1) {
        layer.msg("搜索框不可为空");
        return false;
    }
    layer.open({
        type: 2,
        content: "https://www.baidu.com/s?wd=" + seek,
        area: ["100%", "100%"],
    });
}

function ck(obj) {
    $('a[id^="s-"]').css("color", "black");
    $(obj).css("color", "red");
}

var sign = $("#sign").val();
if (sign){

    layer.msg("欢迎登录，积分+1");
}


function app() {
    layer.open({
        type: 2,
        content: "https://app.jd.com/",
        area: ["100%", "100%"],
    })
}