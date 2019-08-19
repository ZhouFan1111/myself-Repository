
function PageIni()
{
    var path=$("#path").val();
    //初始化签名登录二维码
    var des = "rushang";//描述信息
    var getMssgMessageUrl="login/getMssgMessage";
    var getMsspId="login/getMsspId";
    SetServerUrl(getMsspId)
    //uucode
    SetShowQRCodeUucode("SD_QD_DS_WT");

    //var url = '<%=basePath%>';
    // SetServerUrl(url);
    GetLoginData(des,getMssgMessageUrl,function(msspId){
        //window.location = "/casign/LoginGRCa?msspId="+ msspId;
      //alert(msspId);
     // window.location ="${ctxPath}/login/scavenging";

        $.ajax({
            type: "post",
            url:path+"/login/scavenging",
            dataType: "json",
            async:false,
            data:{"msspId":msspId},
            success: function(jsonData){
                if(jsonData.status=="00000")
                {
                    //操作执行成功
                    if(jsonData.info!="")
                    {
                        layer.msg(jsonData.info,{time:3000},function(){
                            if(jsonData.success!="")
                            {
                                window[jsonData.success](jsonData);
                            }
                            if(jsonData.url!="")
                            {
                                window.location=jsonData.url;
                            }
                        });
                        return;
                    }
                    if(jsonData.success!="")
                    {
                        window[jsonData.success](jsonData);
                    }
                    if(jsonData.url!="")
                    {
                        window.location=jsonData.url;
                    }

                }
                else
                {
                    //操作执行错误
                    if(jsonData.info!="")
                    {
                        layer.msg(jsonData.info,{time:3000},function(){
                            if(jsonData.error!="")
                            {
                                window[jsonData.error](jsonData);
                            }
                            if(jsonData.url!="")
                            {
                                window.location = jsonData.url;
                            }
                        });
                        return;
                    }
                    if(jsonData.error!="")
                    {
                        window[jsonData.error](jsonData);
                    }
                }
            },
            error:function(xhr,textStaus,err)
            {
                layer.alert("出错！"+textStaus+err);
            }
        });
    });

}