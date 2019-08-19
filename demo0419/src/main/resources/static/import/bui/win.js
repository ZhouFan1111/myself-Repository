function nWin(url,width,height,scrollbars,status)
{
	this.page = url==''?'blank.html':url;
    this.width = width==''?'200':width;
    this.height = height==''?'200':height;
	this.scrollbars = scrollbars==''?'no':scrollbars;
	this.x = parseInt(screen.width / 2.0) - (width / 2.0);  
    this.y = parseInt(screen.height / 2.0) - (height / 2.0);
	this.isMsIE = (navigator.appName == "Microsoft Internet Explorer");
	this.isIE6 = false;
	this.ua = navigator.userAgent;
	this.ua.lastIndexOf("MSIE 6.0") != -1?this.isIE6=true:this.isIE6=false;
	if(this.isIE6)
	{
		this.height = this.height+49;
	}
	if(this.isMsIE)
	{
		this.status = status==''?"dialogWidth:"+this.width+"px;dialogHeight:"+this.height+"px;status:no;resizable:yes;location:yes;":status+"; scroll:"+this.scrollbars;
	}
	else
	{
		this.status = status==''?"top=" + this.y + "px,left=" + this.x + "px,scrollbars=" + this.scrollbars + ",dialog=yes,modal=yes,width=" + this.width + ",height=" + this.height + ",resizable=no":status;	
	}
}
nWin.prototype.create = function (){
	  var win;
      if (this.isMsIE) 
	  {     
	  	win = window.showModalDialog(this.page, window, this.status);
      } 
	  else 
	  { 
	  	win = window.open(this.page, "popup",  this.status);         
   	 }  
	  return  win;
};
//cv 子窗口的值   pv 父窗口元素id
function returnWindowValue(cv,pv)
{
	var isMsIE = (navigator.appName == "Microsoft Internet Explorer");
	var parent;
	if(isMsIE)//判断是否是IE浏览器
	{
		window.dialogArguments.document.getElementById(pv).value=cv;
	}
	else
	{
		window.opener.document.getElementById(pv).value=cv;
	}
}

