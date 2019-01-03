/**
 * Created by xinzhendi-031 on 2018/6/5.
 */
function queryCircle() {
    var url = "/fit/query";
    var request = new XMLHttpRequest();
    request.onload = function () {
        if (request.status == 200) {
            console.log(request.responseText);
        }
    };
    request.open("GET", url);
    request.send(null);
}

function queryCircles() {
    var url = "/circle/query";
    var request = new XMLHttpRequest();
    request.onload = function () {
        if (request.status == 200) {
            console.log(request.responseText);
            return request.responseText;
        }
    };
    request.open("POST", url);
    request.send(null);
}

/**
 * jQuery GET 访问
 * */
function jQueryGetTask(){
    $.get('/fit/query',function(data,status){
        console.log(data);
    })
}

/**
 * jQuery POST 访问
 * */
function jQueryPostTask(){
    $.post('/test_rest/test2',param,function(data,status){
        console.log(data);
    })
}

/**
 * jQuery POST 访问
 * 带参数：param
 * */
function jQueryPostParamTask(param){
    $.post('/test_rest/test2',
        // param,
        {
            param:"Donald Duck"
        },
        function(data,status){
            console.log(data);
        })
}

/**
 * ajax GET 访问
 * */
function ajaxGetTask(){
    $.ajax({
        type: 'GET',
        url: '/fit/query',
        success: function(o){
            console.log(o);
        },
        error: function (e) {
            alert("error");
        }
    });
}

/**
 * ajax POST 访问
 * */
function ajaxPostTask(){
    $.ajax({
        type: 'POST',
        url: '/circle/query',
        success: function(o){
            console.log(o);
        },
        error: function (e) {
            alert("error");
        }
    });
}

/**
 * ajax POST 访问
 * 带参数：param
 * */
function ajaxPostParamTask(){
    $.ajax({
        type: 'POST',
        url: '/test_rest/test2',
        data: {
            param:"Donald Duck2"
        },
        success: function(o){
            console.log(o);
        },
        error: function (e) {
            alert("error");
        }
    });
}

/**
 * jsonp 访问，解决GET类型的跨域访问
 * */
function httpJsonpTask(){
    $.ajax({
        type: 'GET',
        url: 'https://graph.qq.com/oauth2.0/me?access_token=BB4D0445825092C4B34B4CB39D60C22F&unionid=1',
        async: false,
        dataType: "jsonp",
        jsonp: "callback",//传递给请求处理程序或页面的，用以获得jsonp回调函数名的参数名(一般默认为:callback)
        jsonpCallback:"callback",//自定义的jsonp回调函数名，默认未jquery自动生成的随机函数名，也可以写“?”jquery会自动处理
        success: function(o){
            console.log(o);
        },
        error: function (e) {
            alert("error");
        }
    });
}