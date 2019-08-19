$(document.body).append('<script src="/cajs_1.0/qy/date.js"></script>');
$(document.body).append('<script src="/cajs_1.0/qy/SecX_Common.js"></script>');


    //var formName="LoginForm";
    var strServerSignedData = $("#strServerSignedData_init").val();
    var strServerRan = $("#strServerRan_init").val();
    var strServerCert = $("#strServerCert_init").val();

function setDiv(errMsg){
    var info=document.createElement('myErrdiv');
    info.innerHTML = errMsg;
    document.body.appendChild(info);
}



function LoginForm_onsubmit() {
    var strCertID =  $("#UserList").val();
    var strPin = $("#UserPwd").val();
    //$("#strRandom").val("<%=strRandom%>");
    var ret = Login("LoginForm", strCertID, strPin);
    if (ret) {
        var ucert=LoginForm.UserCert.value;
        var cajg=XTXAPP.SOF_GetCertInfo(ucert ,5);
        //LoginForm.strCajg.value=cajg;
        $("#strCajg").val(cajg);
        //LoginForm.UserPwd.value = "";
        $("#UserPwd").val("")
        return true;
    } else {
        return false;
    }
}
function formSumbit(func,value) {

    if(LoginForm_onsubmit())
    {
        window.location = "/casign/qySign";
    }
}

SetAlertFunction(setDiv);
SetUserCertList("LoginForm.UserList", CERT_TYPE_ALL);