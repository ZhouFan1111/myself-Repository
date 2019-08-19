
function SignString(text){
    setMessage(text);
    initDataSignString();
    var obj = new Object();
    obj.qmid = "";
    obj.zsid = "";
    sof_sign(function (data) {
        var qmid=data.split(",")[0];//签名id
        var zsid=data.split(",")[1];//证书id
        obj.qmid = qmid;
        obj.zsid = zsid;


        //alert(qmid);
        //alert(zsid);
    });
    return obj;
}



