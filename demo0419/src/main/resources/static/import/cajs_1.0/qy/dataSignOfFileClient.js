
var cur_cert_id = "";
//初始化使用
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
    $("#sof_sign_verify_file_certid").val( cur_cert_id);
}
//文件签名验签测试
function sof_file_sign(fileid,result)
{
    var certid =$("#sof_sign_verify_file_certid").val();
    //文件id获取
    var file1 = document.getElementById(fileid);
    file1.select();
    var file = document.selection.createRange().text;
    if (certid == "") {
        layer.alert("请插入CA");
        return;
    }
    if (file == "") {
        layer.alert("请选择文件");
        return;
    }
    var ret = XTXAPP.SOF_SignFile(certid, file);

	var tpqmid="";
    var tpzsid="";

    var qmxx = false;
    var qmzs = false;
    if (ret == "") {
        //alert("文件签名失败");
        //form1.sof_sign_verify_file_res.value = "文件签名失败";
    } else {
        tpqmid=ret;
        qmxx = true;
    }

    var ret2 = XTXAPP.SOF_ExportUserCert(certid);

    if (ret2 != "") {
        tpzsid=ret2;
        qmzs = true;
    } else {
        //alert("证书为空");
        //form1.sof_sign_verify_file_cert.value = "证书为空";
    }
    if(!qmxx||!qmzs){
        layer.alert("签名失败");
    }else{
        layer.alert("签名成功");
        return result(tpqmid+","+tpzsid);
    }
}

var isIE = navigator.userAgent.toLowerCase().search(/(msie\s|trident.*rv:)([\w.]+)/)!= -1;
if (isIE) {
//if (window.ActiveXObject) {
    document.writeln("<OBJECT classid=\"CLSID:3F367B74-92D9-4C5E-AB93-234F8A91D5E6\" height=1 id=XTXAPP  style=\"HEIGHT: 1px; LEFT: 10px; TOP: 28px; WIDTH: 1px\" width=1 VIEWASTEXT>");
    document.writeln("</OBJECT>");
    XTXAPP.SOF_GetVersion();
} else {
    document.writeln("<embed id=XTXAPP0 type=application/x-xtx-axhost clsid={3F367B74-92D9-4C5E-AB93-234F8A91D5E6} event_OnUsbkeyChange=OnUsbKeyChange width=1 height=1 />");
    XTXAPP = document.getElementById("XTXAPP0");
    XTXAPP.SOF_GetVersion();
}

//获取用户列表 并填充到证书列表
function GetUserList(strListID)
{
    var objListID = eval(strListID);
    var strUserList = XTXAPP.SOF_GetUserList();
    layer.alert("a1"+strUserList);
    //alert(strUserList);
    while (1) {
        var i = strUserList.indexOf("&&&");
        layer.alert("&&&在"+i+"位置");
        if (i <= 0 ) {
            break;
        }
        var strOneUser = strUserList.substring(0, i);
        //证书所有者
        var strName = strOneUser.substring(0, strOneUser.indexOf("||"));
        var strUniqueID = strOneUser.substring(strOneUser.indexOf("||") + 2, strOneUser.length);
        var objItem = new Option(strName, strUniqueID);
        objListID.options.add(objItem);
        var len = strUserList.length;
        strUserList = strUserList.substring(i + 3,len);
        layer.alert("a2"+strUserList);
    }
    var objListID = null;
    return;
}
//清空证书列表
function RemoveUserList(strListID)
{
    var objListID = eval(strListID);
    var i;
    var n = objListID.length;
    for(i = 0; i < n; i++) {
        objListID.remove(0);
    }
}
//重新填充用户列表
function ChangeUserList(strListID)
{
    RemoveUserList(strListID);
    GetUserList(strListID);
}

//非IE浏览器下用到的函数
function OnUsbKeyChange()
{
    //alert("OnUsbKeyChange called!");
    ChangeUserList("form1.login_test_certlst");
}

function getPath(obj){
    if(obj){
        if(window.navigator.userAgent.indexOf("MSIE")>=1){
            obj.select();
            return document.selection.createRange().Text;
        }
        else if(window.navigator.userAgent.indexOf("Firefox")>=1){
            if(obj.file){
                return obj.file.item(0).getAsDataURL();
            }
            return obj.value;
        }
        return obj.value;
    }

}
//通过OID获取证书信息,这个oid是北京ca直接提供的
function sof_get_certinfo_byoid()
{
    var cert = $("#sof_get_certinfo_byoid_cert").val();
    var oid = $("#sof_get_certinfo_byoid_cert_oid").val();//form1.sof_get_certinfo_byoid_cert_oid.value;
    var ss = "";
    if (cert == "") {
        layer.alert("请输入证书");
        $("#sof_get_certinfo_byoid_cert").val();
        return;
    }
    if (oid == "") {
        layer.alert("请输入OID");
        $("#sof_get_certinfo_byoid_cert_oid").val();
        return;
    }

    var ret = XTXAPP.SOF_GetCertInfoByOid(cert, oid);

    //这是原来的 "1.3.6.1.5.5.7.1.12"
    if (oid == "1.3.6.1.5.5.7.1.12") {
        var json_str = eval('(' + ret + ')');
        ss = json_str.toJSONString();
    }else{
        ss = ret;
    }
    return ss;
}

function get_cert_entity()
{
    var cert = form1.sof_get_certinfo_byoid_cert.value;

    if (cert == "") {
        layer.alert("请输入证书");
        form1.sof_get_certinfo_byoid_cert.focus();
        return;
    }

    var ret = XTXAPP.SOF_GetCertEntity(cert);
    if (ret != "") {
        form1.sof_get_certinfo_byoid_res.value = ret;
    } else {
        form1.sof_get_certinfo_byoid_res.value = "获取证书唯一实体标识失败";
    }
}

