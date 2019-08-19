
$(document.body).append('<script src="/cajs_1.0/qy/dataSignOfFileClient.js"></script>');

function SignFile(_nsrsbhid,_fileid){
    try{
        get_cert_id();
    }catch(err){
        layer.alert("未获取到CA证书");
        return;
    }
    var crertid =  $("#sof_sign_verify_file_certid").val();
    if(crertid==""){
        layer.alert("未获取到CA证书");
        return;
    }
    var hqsbh =  $("#"+_nsrsbhid).val();
    var ret2 = XTXAPP.SOF_ExportUserCert(crertid);
    $("#sof_get_certinfo_byoid_cert").val(ret2);
    var casbh = sof_get_certinfo_byoid();
    $("#sof_get_certinfo_byoid_cert").val("");
    if(casbh!=""){
        casbh = casbh.substring(2);
    }
    if(hqsbh==casbh){
        sof_file_sign(_fileid,function (data) {
            //alert(data);
            var qmid=data.split(",")[0];//签名id
            var zsid=data.split(",")[1];//证书id
            console.log(qmid);
            console.log(zsid)
        });
    }else{
        alert("CA获取纳税识别号不正确");
        $("#sof_sign_verify_file_certid").val("");
    }

}


