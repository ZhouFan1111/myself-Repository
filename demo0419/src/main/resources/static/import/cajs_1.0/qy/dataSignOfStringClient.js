//获取 id
var certid = "";
var cur_cert_id = "";
var plain = "";

function setMessage(text)
{
    plain=text;
}

function initDataSignString() {
    var isIE = navigator.userAgent.toLowerCase().search(/(msie\s|trident.*rv:)([\w.]+)/)!= -1;
    if (isIE) {
//if (window.ActiveXObject) {
        document.writeln("<OBJECT classid=\"CLSID:3F367B74-92D9-4C5E-AB93-234F8A91D5E6\" height=1 id=XTXAPP  style=\"HEIGHT: 1px; LEFT: 10px; TOP: 28px; WIDTH: 1px\" width=1 VIEWASTEXT>");
        document.writeln("</OBJECT>");
        XTXAPP.SOF_GetVersion();
        //XTXAPP.SetUserConfig(2, "0");
    } else {
        document.writeln("<embed id=XTXAPP0 type=application/x-xtx-axhost clsid={3F367B74-92D9-4C5E-AB93-234F8A91D5E6} event_OnUsbkeyChange=OnUsbKeyChange width=1 height=1 />");
        XTXAPP = document.getElementById("XTXAPP0");
        XTXAPP.SOF_GetVersion();
        //XTXAPP.SetUserConfig(2, "0");
    }
}


function get_cert_id()
{
    var usrlst = XTXAPP.SOF_GetUserList();
    while (usrlst != "") {
        var i = usrlst.indexOf("&&&");
        var left = usrlst.substring(0, i);
        var right = usrlst.substring(i + 3, usrlst.length);
        var j = left.indexOf("||");
        var name = left.substring(0, j);
        var container = left.substring(j + 2, left.length);
        if (cur_cert_id != container) {
            cur_cert_id = container;
            break;
        }
        usrlst = right;
    }
    certid = cur_cert_id;
}
var sig = "";
var cert = "";
//数据签名验签测试
function sof_sign(result)
{
    get_cert_id();
    var qmid="";
    var zsid="";
    if (certid == "") {
        alert("请插入Key！");
        return;
    }

    var time_begin = new Date();
    var i, ret;
    for (i = 0; i < 1; i++) {
       // alert(plain);
        ret = XTXAPP.SOF_SignData(certid, plain);
        qmid=ret;
    }
    if (ret == "") {
        alert("数据签名失败");
        return;
    } else {
        cert = ret;
    }
    var time_end = new Date();
    var str_waring = "消耗时间" + (time_end - time_begin) / 1000 + "秒";
    //alert(str_waring);
    var ret2 = XTXAPP.SOF_ExportUserCert(certid);
    if (ret2 != "") {
        sig =  ret2;
        zsid=  ret2;
    } else {
        alert("证书为空");
    }
    if(qmid!=""&&zsid!="")
    {
        result(qmid+","+zsid);
    }
    else
        {
            alert("数据签名失败");
        }
}
/*function resize_window(){
    window.moveTo(0,0);
    window.resizeTo(800,600);
}
resize_window();*/
function Bytedata(str){
    var bytelen = 0;
    return str.split('');
}