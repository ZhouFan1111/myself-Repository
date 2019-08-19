/*--------------------------------------------------------------------------  
 * the javascript file is used for web seal operation in MSSP condition.
 * in the condition, sign operation is realized by MSSM cloud and mobile app,
 * and the stamp of user is saved in the MSSM cloud.
 * Author:BJCA-xf
 * date:20160909
 *--------------------------------------------------------------------------*/

//global variant
var $_$token = null;
var $_$breakFlag = 0;
var $_$SignOptCallbackFun = null;
var $_$VerifyOptCallbackFun = null;
var $_$stampPicDiv = null;
var $_$stampPicID = null;
var jhurl = null;

//temp sign data
var tmp_signdata = null;
var tmp_cert = null;
var tmp_stampPic = null;

var $_$WebSeal_SealToDataUrl = "";



function WebSeal_SealToDataUrl(url)
{
    $_$WebSeal_SealToDataUrl = url;
}
function WebSeal_jhurl(url)
{
    jhurl = url;
}
//
//函数说明：对数据进行签章
//参数说明：
//		oriData:		需要签章的数据
//		descriptionInfo:签章操作的描述信息（暂时没用）
//		stampPicID:		签章成功后放置印章图片的Img元素的ID(注：数据签章不需要，传值为空)
//		CallbackFun:	签章成功后的回调函数
//
//		回调函数的原型：function SealDataCallbackFun(sealData);
//						参数说明:sealData,签章成功后返回的签章数据
//
function WebSeal_SealToData(oriData, descriptionInfo, stampPicID, CallbackFun)
{
	var url=$_$WebSeal_SealToDataUrl;
 	var req= new Object();
	req.data = oriData;
	var info = '';
	if(descriptionInfo == null)
		info = "";
	else
		info = descriptionInfo;
/*	if(descriptionInfo == null)
		req.des = "";
	else
		req.des = descriptionInfo;
*/	req.keyType = "AUTH";
	//req.operType = "SIGN";

	$_$stampPicID = stampPicID;
	$_$SignOptCallbackFun = CallbackFun;
	var postdata=JSON.stringify(req);
	jQuery.ajax({
         type: 'POST',
         //contentType: 'json',
         url: url,
         data:{keyType:'AUTH',data:oriData,des:info},
         dataType: 'json',
         success: function(data){
			$_showProc();
			$_ShowQRCode(data) ;
         },
         error: function(data){
        	 layer.alert("服务器错误请稍后再试！");
             }
        });
}

//
//函数说明：对数据进行验章操作
//参数说明：
//		oriData:		需要验章的原始数据
//		sealData:		签章后的结果数据
//		stampPicID:		签章成功后放置印章图片的Img元素的ID(注：数据签章不需要，传值为空)
//		CallbackFun:	验章的回调函数
//
//		回调函数的原型：function verifyCallbackFun(bVerifyOk);
//						参数说明:bVerifyOk,true:验章成功；false:验章失败
//
function WebSeal_VerifySeal(oriData, sealData, stampPicID, verifyCallbackFun)
{
	//parse signature and cert
	var signData,signCert;

	if((oriData==null) || (oriData.length == 0))
	{
		layer.alert("oriData param is null");
		return;
	}
	if((sealData==null) || (sealData.length == 0))
	{
		layer.alert("sealData param is null");
		return;
	}

	$_$stampPicID = stampPicID;
	$_$VerifyOptCallbackFun = verifyCallbackFun;

	//dataParse
	if($_ParseSealData(sealData)!=0)
	{
        layer.alert("sealData is unvalid!");
	}

	signData = tmp_signdata;
	signCert = tmp_cert;
	$_SignVerifyFunc(oriData, signData, signCert);
}
var showQRCodeUucode="";
function SetShowQRCodeUucode(uucode) {
    showQRCodeUucode=uucode;
}
//
//函数说明：进行二维码编码并显示（内部函数）
//参数说明：
//		data:		需要进行二维码QR编码的数据
//
function $_ShowQRCode(data)
{
    var json=data;

    $_$token=json.signId;
    if(json.errCode!="0")
    {
    	   document.getElementById("qrcode").innerHTML="MSSP云签名网络服务异常 <br>"+json.errMsg;
     	   return;
    }
    else
    {
        var QR_Obj=new Object();
        QR_Obj.version=json.version;
        QR_Obj.operType="SignData";

        QR_Obj.uuid="11bc2166-27a7-4257-8663-160a48f18a6a";
        QR_Obj.uucode=showQRCodeUucode;
        QR_Obj.signType="0";
        QR_Obj.appScheme="xyt-shgz";

        QR_Obj.data=$_$token;
        var qrcode=JSON.stringify(QR_Obj);

    	//alert($_$token);
    	/*var QR_Obj=new Object();
    	QR_Obj.version=json.version;
    	QR_Obj.operType="SignData";
    	QR_Obj.data=$_$token;
    	var qrcode=JSON.stringify(QR_Obj);*/
        var qrcode_c = document.getElementById("qrcode");
	    if(qrcode_c)
	    {
			document.getElementById("qrcode").innerHTML="";
			var qrcodeIMG = new QRCode(qrcode_c,
			{
			width : 154,
			height : 154
			});

			qrcodeIMG.makeCode(qrcode);
			$_GetData();
	    }
     }
}

var $_$GetDataUrl=""
function GetDataUrl(url)
{
    $_$GetDataUrl = url;
}
//
//函数说明：获取签章结果（内部函数）
//参数说明：
//
function $_GetData()
{
       	var req= new Object();
    	req.signId=$_$token;

     	var postdata=JSON.stringify(req);
     	var url=$_$GetDataUrl;
		jQuery.ajax({
                type: 'POST',
                contentType: 'text',
                url: url,
                data:encodeURI(postdata) ,
                dataType: 'json',
                success: function(data)
                {

                   $_Recall(data)  ;
	            },
	                error: function()
	                {
	                    //alert("error");
	                    $_GetData();
	                }
	            });

}

//
//函数说明：处理返回的签章数据（内部函数）
//参数说明：
//		data:		从服务器返回的签章数据json
//
function $_Recall(data)
{
	var json=data;
	var state= json.state;
	var tmpSealData;//stamppic,
	//alert(data);
	if(state=="0")
	{
		tmpSealData = json.sealData;
		$_closeProc();
		if($_ParseSealData(tmpSealData)!=0)
		{
            layer.alert("sealData is unvalid!");
			return;
		}

		if($_$SignOptCallbackFun)
			$_$SignOptCallbackFun(tmpSealData);
	}
	else
	{
		if ($_$breakFlag==0)
		{
			$_GetData();
		}
	}
}


var $_$SignVerifyFuncUrl="";
function SignVerifyFuncUrl(url)
{
    $_$SignVerifyFuncUrl = url;
}

//
//函数说明：对签章数据进行验章操作（内部函数）
//参数说明：
//		oriData:		需要验章的原始数据
//		signature:		签章数据中解析出来的签名值
//		cert:			签章数据中解析出来的证书内容
//
function $_SignVerifyFunc(oridata,signature,cert)
{

	var url=$_$SignVerifyFuncUrl+'mssp/signVerify';
	var req= new Object();

	req.data=oridata;
	req.signature=signature;
	req.cert=cert;

	var postdata=JSON.stringify(req);
	jQuery.ajax({
          type: 'POST',
          contentType: 'application/json',
          url: url,
          data: postdata,
          dataType: 'json',
          success: function(data)
          {
       	   //alert(JSON.stringify(data))
            $_SignVerifyFinish(JSON.stringify(data));

          },
          error: function(data){
              layer.alert("error"+JSON.stringify(data));
              }
    });
}

//
//函数说明：验章操作后的回调函数处理（内部函数）
//参数说明：
//		data:		从服务器返回的验章操作json数据
//
function $_SignVerifyFinish(data)
{
	var Json = eval("(" + data + ")");
	if(Json.state!="0")
	{
	 if($_$VerifyOptCallbackFun)
			$_$VerifyOptCallbackFun(false);
	  grayscale($("#"+$_$stampPicID));
	}
	else
	{
	  if($_$VerifyOptCallbackFun)
			$_$VerifyOptCallbackFun(true);
	}
}

//
//函数说明：解析签章数据的各项内容（内部函数）
//参数说明：
//		sealData:		数据签章后的结果数据
// 	备注：目前解析签名值、证书、印章的图片信息
//
function $_ParseSealData(sealData)
{
	if(sealData != null && ''!=sealData){
		var words = sealData.split(',');
		tmp_signdata = words[2];
		tmp_cert = words[1];
		return 0;
	}else{
		return -1;
	}
}
///////////////////弹出二维码相关/////////////////////////////////////////////
//
//函数说明：创建灰色背景层（内部函数）
//
function $_showProc()
{
    message_box.style.visibility='visible';
    message_box.style.left=($(window).width()-260)/2+"px";

    //创建灰色背景层
    procbg = document.createElement("div");
    procbg.setAttribute("id","mybg");
    procbg.style.background = "#000";
    procbg.style.width = "100%";
    //procbg.style.height = "100%";
    procbg.style.position = "absolute";
    procbg.style.top = "0";
    procbg.style.left = "0";
    procbg.style.zIndex = "500";
    procbg.style.opacity = "0.3";
    procbg.style.filter = "Alpha(opacity=30)";
    //背景层加入页面
    document.body.appendChild(procbg);
    document.body.style.overflow = "hidden";

    $("#mybg").css("height",$(document).height());
}

//
//函数说明：隐藏灰色背景层（内部函数）
//
function $_closeProc(flag)
{
    message_box.style.visibility='hidden';
    procbg.style.visibility = "hidden";
    $_$breakFlag=1;

    document.body.style.overflow = "auto";
}



function panduanshouji(){

    if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)){
        $("#sj_tuisong").show();
        $("#pc_tuisong").hide();
    }else if(/iPad/i.test(navigator.userAgent)){
        $("#sj_tuisong").show();
        $("#pc_tuisong").hide();
    }else{
        $("#sj_tuisong").hide();
        $("#pc_tuisong").show();
    }
    $("#message_box").css("left",($(window).width()-294)/2);

}

//
//函数说明：为弹出二维码的DIV增加Style（内部函数）
//
function AddStyle()
{

    var style_nod = document.createElement("style");
    str = "#message_box{position:fixed;top:15%;background:#fff;width:260px;border-radius:3px; height:270px;filter:dropshadow(color=#666666,offx=3,offy=3,positive=2);z-index:1000;visibility:hidden}";
    str = str+ "#message{}";
    str = str+ "#message_title{width:260px; text-align:center; clear:both; padding-top:30px; height:36px; color:#7b8392; font-size:14px;}";
    str = str+ "#message_title_txt{font-size:15px; font-family:'微软雅黑'; color:#666;}";
    str = str+ "#message_title_close{position:absolute;top:10px; right:13px;}";
    str = str+ "#qrcode{padding-left:53px; padding-top:20px; height:178px;}";
    str = str+ "#qrcode1{padding-left:53px; padding-top:20px; height:178px;}";
    str = str+ "#message_title_anniu{ height:30px; display:inline-block;text-align:center; width:100%}";
    str = str+ "#message_title_anniu input[type=button]{ height:35px;}";
    style_nod.type="text/css";
    if
    (style_nod.styleSheet){         //ie下
        style_nod.styleSheet.cssText = str;
    }
    else {
        //style_nod.innerHTML = str;       //或者写成
        style_nod.appendChild(document.createTextNode(str));
    }
    document.getElementsByTagName("head")[0].appendChild(style_nod);
}

//
//函数说明：为弹出二维码增加DIV（内部函数）
//
function AddDiv()
{
    var message_box = document.createElement("div");
    message_box.id = "message_box";

    var message = document.createElement("div");
    message.id = "message";

    var message_title = document.createElement("div");
    message_title.id = "message_title";

    var qrcodeDiv = document.createElement("div");
    qrcodeDiv.id = "qrcode";

    /*var qrcodeDiv1 = document.createElement("div");
    qrcodeDiv1.id = "qrcode1";*/

    var message_title_txt = document.createElement("span");
    message_title_txt.id = "message_title_txt";
    message_title_txt.innerHTML="请使用“盛信通”APP扫描二维码<br><br>";

    var message_bottom_txt = document.createElement("span");
    message_bottom_txt.id = "message_bottom_txt";
    message_bottom_txt.innerHTML="<div align='center'>" +
        "<a onclick='jh();' style='font-size:15px; font-family:\"微软雅黑\";cursor: pointer;color: blue'>如您未申请CA证书，请点击此处</a>" +
        "</div>";

    var message_title_close = document.createElement("span");
    message_title_close.id = "message_title_close";
    message_title_close.onclick=function (){
        $_closeProc();  };
    message_title_close.innerHTML="<font style='font-family:Arial, Helvetica, sans-serif; font-size:16px; cursor:pointer'>X</font>";

    message_title.appendChild(message_title_txt);
    message_title.appendChild(message_title_close);

    message.appendChild(message_title);
    message.appendChild(qrcodeDiv);
    message.appendChild(message_bottom_txt);
    //message.appendChild(qrcodeDiv1);

    message_box.appendChild(message);

    document.body.appendChild(message_box);
}

AddStyle();
AddDiv();

function jh() {
    layer.open({
        type:2,
        title:'个人CA激活',
        shadeClose:false,
        shade:0,
        area:['530px','80%'],
        content:jhurl,
    });
}


///////////////////////////////////////////////
