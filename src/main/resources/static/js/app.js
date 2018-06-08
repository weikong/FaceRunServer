/**
 * Created by xinzhendi-031 on 2018/6/5.
 */
function queryCircle(document) {
    var url = "/fit/query";
    var request = new XMLHttpRequest();
    request.onload = function () {
        if (request.status == 200) {
            return request.responseText;
        }
    };
    request.open("GET", url);
    request.send(null);
}