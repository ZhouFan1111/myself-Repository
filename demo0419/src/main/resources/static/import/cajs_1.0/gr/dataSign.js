
function Sign(data,SealToDataUrl,DataUrl,jhUrl){
    //对数据进行签章地址
    WebSeal_SealToDataUrl(SealToDataUrl);
    //获取签章结果（内部函数）地址
    GetDataUrl(DataUrl);
    //uucode
    SetShowQRCodeUucode("SD_QD_DS_WT");
   //uucode
    WebSeal_jhurl(jhUrl);
    var des="数据签名操作 ";

    WebSeal_SealToData(hash(data), des, "", function(sealData){
        if(document.getElementById("qianfa_sign")==null)
        {
            var signatureDom = document.createElement("hidden");
            signatureDom.id = "qianfa_sign";
            signatureDom.style.visibility='hidden';
            document.body.appendChild(signatureDom);
        }
        //var date = getDate();
        //return sealData;
        agree(sealData);//sealData协议签名值  date时间
    });
}
var I64BIT_TABLE = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-'.split('');

function hash(input){
    var hash = 5381;
    var i = input.length - 1;

    if(typeof input == 'string'){
        for (; i > -1; i--)
            hash += (hash << 5) + input.charCodeAt(i);
    }
    else{
        for (; i > -1; i--)
            hash += (hash << 5) + input[i];
    }
    var value = hash & 0x7FFFFFFF;

    var retValue = '';
    do{
        retValue += I64BIT_TABLE[value & 0x3F];
    }
    while(value >>= 6);

    return retValue;
}
function agree(sealData,date)
{
    sub(sealData);
    /*var obj = new Object();
    obj.qmid = sealData;
    obj.zsid = "";*/
    //alert(sealData);
    //alert(date);

}
function getcurrdate() {
    var date = new Date();
    var month = date.getMonth()+1;
    var strDate = date.getDate();
    if(month>=1&&strDate<=9){
        month = "0"+month;
    }
    if(strDate >=0 && strDate<=9){
        strDate = "0"+strDate;
    }
    var currentdate = date.getFullYear()+"年"+month+"月"+strDate+"日";
    return currentdate;
}
