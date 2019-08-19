/*--------------------------------------------------------------------------  
 * the javascript file is used for web seal operation in MSSP condition.
 * in the condition, sign operation is realized by MSSM cloud and mobile app,
 * and the stamp of user is saved in the MSSM cloud.
 * Author:BJCA-xf
 * date:20160909
 *--------------------------------------------------------------------------*/

//global variant
var $_$token = null;
var $_$server_url = "";
var $_$breakFlag = 0;
var $_$SignOptCallbackFun = null;
var GetSign_interval = 3000;
var repeattimes = 0;
//
//函数说明：设置服务器的URL，缺省情况下不用调用此函数。
//
function SetServerUrl(url)
{
	$_$server_url = url;
}

//
//函数说明：对数据进行签名
//参数说明：
//		oriData:		需要签名的数据
//		descriptionInfo:签名操作的描述信息（暂时没用）
//		CallbackFun:	签名成功后的回调函数
//
function GetLoginData(des,getMssgMessageUrl, CallbackFun)
{
	//message_title_txt.innerHTML="使用签签Demo扫描二维码";
	var url=getMssgMessageUrl;
	
	var req= new Object();
	req.des=des;
	req.type="sign";
	var postdata=JSON.stringify(req);
	$_$SignOptCallbackFun = CallbackFun;
	jQuery.ajax({  
			type: 'post',  
        	contentType: 'text',  
        	url: url,  
			async: false,
         success: function(data){
			//$_showProc();
			
			/*$_ShowQRCode(JSON.stringify(data)) ;     */        
			$_ShowQRCode(data) ; 
         },  
         error: function(data){
             layer.alert("error"+JSON.stringify(data));
             }  
        }); 
}

var qRCodeUucode="";
function SetShowQRCodeUucode(uucode)
{
    qRCodeUucode=uucode;
}

//
//函数说明：进行二维码编码并显示（内部函数）
//参数说明：
//		data:		需要进行二维码QR编码的数据
//  
function $_ShowQRCode(data) 
{
    var json=JSON.parse(data);
 
    $_$token=json.signId;
    if(json.errCode!="0") 
    {
    	document.getElementById("qrcode").innerHTML="MSSP云签名网络服务异常 <br>"+json.errMsg;
     	return;
    }
    else
    {
    	//alert($_$token);
    	var QR_Obj=new Object();
		//2018-1-23
		QR_Obj.uuid="11bc2166-27a7-4257-8663-160a48f18a6a";
		QR_Obj.uucode=qRCodeUucode;
		QR_Obj.signType="0";
		//end 2018-1-23
    	QR_Obj.version=json.version;
    	QR_Obj.operType="SignData";
    	QR_Obj.data=$_$token;
		
    	var qrcode=JSON.stringify(QR_Obj);
    	//alert(qrcode);
        var qrcode_c = document.getElementById("qrcode");  
	    if(qrcode_c)
	    {  
			document.getElementById("qrcode").innerHTML="";
			var qrcodeIMG = new QRCode(qrcode_c,
			{  //154
				width : 100,
				height : 100
			});  

			qrcodeIMG.makeCode(qrcode);
			$_GetData();
	    }     
     }     
}   
	function trim(str) {
	  return str.replace(/(^\s+)|(\s+$)/g, "");
	}
//
//函数说明：获取签名结果（内部函数）
//参数说明：
//  
/*
function $_GetData()
{
       	var req= new Object();
    	req.signId=$_$token;    	
    	    	    	
     	var postdata=JSON.stringify(req);
     	var url='getSignResult.jsp';
     	
		jQuery.ajax({  
                type: 'POST',  
                contentType: 'text',  
                url: url,  
                data:encodeURI(postdata) ,  
                success: function(data)
                {  
                    $_Recall(data);           
	            },  
	                error: function()
	                {
	                    $_GetData();
	                }  
	            });  
         
}
 */
 function $_GetData()
{
       //	var req= new Object();
    	//req.signId=$_$token;    	
    	  var signid= $_$token;
     	//var postdata=JSON.stringify(req);
     	var url=$_$server_url;
     	//alert(signid);
		$.ajax({  
                type: 'POST',  
                dataType: 'text',  
                url: url,  
                data:{'id':signid},  
                success: function(data)
                {
                	//alert(data);
                    $_Recall(data);           
	            },  
	                error: function()
	                {
	                    $_GetData();
	                }  
	            });  
         
}
   
   
//
//函数说明：处理返回的签名数据（内部函数）
//参数说明：
//		data:		从服务器返回的签名数据msspId
//       
function $_Recall(data)
{
	var msspId = data;
	msspId=trim(msspId); // =abc
	repeattimes=repeattimes+1;	
	
	if(repeattimes>100)
	{		
		$_$breakFlag=1;
	}
	if(msspId != null && msspId != ""){
		if($_$SignOptCallbackFun){
			$_$SignOptCallbackFun(msspId);
		}
	}else{
		if ($_$breakFlag==0){
			
			window.setTimeout($_GetData,GetSign_interval);
			//$_GetData();
		}
	}
}

