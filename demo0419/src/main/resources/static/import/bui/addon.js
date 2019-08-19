//税务登记直接填写页面功能菜单
function createMenu_swdj(i)
{
    var Menu = BUI.Menu;
    var menu = new Menu.Menu({
        render : '#m1',
        width : 139,
        elCls: 'fun-menu',
        children : swdjMenus(i),
        //默认模板
        itemTpl : '<a href="{href}" style="cursor:pointer">{text}</a>'
    });

    menu.render();
}

//税务登记直接填写页面功能菜单
function createMenu_ydj(i)
{
    var Menu = BUI.Menu;
    var menu = new Menu.Menu({
        render : '#m1',
        width : 139,
        elCls: 'fun-menu',
        children : ydjMenus(i),
        //默认模板
        itemTpl : '<a href="{href}" style="cursor:pointer">{text}</a>'
    });

    menu.render();
}
//税务登记功能列表生成方法
function swdjMenus(i)
{
    var zgswjg = document.getElementById("hid_zgswjg").value;
    if(i==1)
    {
        return 	[{
            id : 'm1-1',
            //  text : '<div style="width:140px;">涉税申请</div>',
            text : '<div style="width:140px;color:#000;font-weight:bolder">预约事项申请</div>',
            //  href:"ysq_phone_new.jsp?hid_sfdl="+ $("#hid_sfdl").val() +"&hid_phone="+$("#hid_phone").val(),
            href:"yy_net_yysx.jsp?zgswjg="+zgswjg,
            selected:true
        },
            {
                id : 'm1-2',
                //  text: '<div style="width:140px;">涉税申请查询</div>',
                text: '<div style="width:140px;color:#000">我要预约</div>',
                //	  href:"ysq_phone_search.jsp?hid_sfdl="+ $("#hid_sfdl").val() +"&hid_phone="+$("#hid_phone").val()+"&hid_nsrdzdah="+$("#hid_nsrdzdah").val()
                href:"yy_net_new.jsp?zgswjg="+zgswjg
            },
            {
                id : 'm1-3',
                text : '<div style="width:140px;color:#000">预约历史记录</div>',
                href:"yy_net_history.jsp?zgswjg="+zgswjg
            }/*,
				{
				  id : 'm1-3',
				  text : '<div style="width:140px;">预约查询</div>',
				  href:"yy_net_getyy.html"
				}*/
        ];
    }
    else if(i==2)
    {
        return 	[{
            id : 'm1-1',
            //  text : '<div style="width:140px;">涉税申请</div>',
            text : '<div style="width:140px;color:#000">预约事项申请</div>',
            //  href:"ysq_phone_new.jsp?hid_sfdl="+ $("#hid_sfdl").val() +"&hid_phone="+$("#hid_phone").val()
            href:"yy_net_yysx.jsp?zgswjg="+zgswjg
        },
            {
                id : 'm1-2',
                //  text: '<div style="width:140px;">涉税申请查询</div>',
                text: '<div style="width:140px;color:#000;font-weight:bolder">我要预约</div>',
                //  href:"ysq_phone_search.jsp?hid_sfdl="+ $("#hid_sfdl").val() +"&hid_phone="+$("#hid_phone").val()+"&hid_nsrdzdah="+$("#hid_nsrdzdah").val(),
                href:"yy_net_new.jsp?zgswjg="+zgswjg,
                selected:true
            },
            {
                id : 'm1-3',
                text : '<div style="width:140px;color:#000">预约历史记录</div>',
                href:"yy_net_history.jsp?zgswjg="+zgswjg
            }
        ];
    }
    else if(i==3)
    {
        return 	[{
            id : 'm1-1',
            //  text : '<div style="width:140px;">涉税申请</div>',
            text : '<div style="width:140px;color:#000">预约事项申请</div>',
            //  href:"ysq_phone_new.jsp?hid_sfdl="+ $("#hid_sfdl").val() +"&hid_phone="+$("#hid_phone").val()
            href:"yy_net_yysx.jsp?zgswjg="+zgswjg
        },
            {
                id : 'm1-2',
                //  text: '<div style="width:140px;">涉税申请查询</div>',
                text: '<div style="width:140px;color:#000">我要预约</div>',
                //  href:"ysq_phone_search.jsp?hid_sfdl="+ $("#hid_sfdl").val() +"&hid_phone="+$("#hid_phone").val()+"&hid_nsrdzdah="+$("#hid_nsrdzdah").val(),
                href:"yy_net_new.jsp?zgswjg="+zgswjg
            },
            {
                id : 'm1-3',
                text : '<div style="width:140px;color:#000;font-weight:bolder">预约历史记录</div>',
                href:"yy_net_history.jsp?zgswjg="+zgswjg,
                selected:true
            }/*,
				{
				  id : 'm1-3',
				  text : '<div style="width:140px;">预约查询</div>',
				  href:"yy_net_getyy.html"
				}*/
        ];
    }
}

//转向首页方法
function goToIndexPage()
{
    window.location='index.html';
}

//税务登记功能列表生成方法
function ydjMenus(i)
{
    if(i==1)
    {
        return 	[{
            id : 'm1-1',
            //  text : '<div style="width:140px;">涉税申请</div>',
            text : '<div style="width:140px;">预约申请查看</div>',
            href:"yy_tax_index.html",
            selected:true
        },
            {
                id : 'm1-2',
                //  text: '<div style="width:140px;">涉税申请查询</div>',
                text: '<div style="width:140px;">预约日期设置</div>',
                href:"yy_tax_yySet_rq.html"
            },
            {
                id : 'm1-3',
                //  text: '<div style="width:140px;">涉税申请查询</div>',
                text: '<div style="width:140px;">预约时间设置</div>',
                href:"yy_tax_yySet_sj.html"
            },
            {
                id : 'm1-4',
                text: '<div style="width:140px;">预约事项设置</div>',
                href:"yy_tax_yySet_sx.html"
            }
        ];
    }
    else if(i==2)
    {
        return 	[{
            id : 'm1-1',
            //  text : '<div style="width:140px;">涉税申请</div>',
            text : '<div style="width:140px;">预约申请查看</div>',
            href:"yy_tax_index.html"
        },
            {
                id : 'm1-2',
                //	  text: '<div style="width:140px;">涉税申请查询</div>',
                text: '<div style="width:140px;">预约日期设置</div>',
                href:"yy_tax_yySet_rq.html",
                selected:true
            },
            {
                id : 'm1-3',
                //	  text: '<div style="width:140px;">涉税申请查询</div>',
                text: '<div style="width:140px;">预约时间设置</div>',
                href:"yy_tax_yySet_sj.html"
            },
            {
                id : 'm1-4',
                text: '<div style="width:140px;">预约事项设置</div>',
                href:"yy_tax_yySet_sx.html"
            }
        ];
    }
    else if(i==3)
    {
        return 	[{
            id : 'm1-1',
            //  text : '<div style="width:140px;">涉税申请</div>',
            text : '<div style="width:140px;">预约申请查看</div>',
            href:"yy_tax_index.html"
        },
            {
                id : 'm1-2',
                //	  text: '<div style="width:140px;">涉税申请查询</div>',
                text: '<div style="width:140px;">预约日期设置</div>',
                href:"yy_tax_yySet_rq.html"
            },
            {
                id : 'm1-3',
                //	  text: '<div style="width:140px;">涉税申请查询</div>',
                text: '<div style="width:140px;">预约时间设置</div>',
                href:"yy_tax_yySet_sj.html",
                selected:true
            },
            {
                id : 'm1-4',
                text: '<div style="width:140px;">预约事项设置</div>',
                href:"yy_tax_yySet_sx.html"
            }
        ];
    }
    else if(i==4)
    {
        return 	[{
            id : 'm1-1',
            //  text : '<div style="width:140px;">涉税申请</div>',
            text : '<div style="width:140px;">预约申请查看</div>',
            href:"yy_tax_index.html"
        },
            {
                id : 'm1-2',
                //	  text: '<div style="width:140px;">涉税申请查询</div>',
                text: '<div style="width:140px;">预约日期设置</div>',
                href:"yy_tax_yySet_rq.html"
            },
            {
                id : 'm1-3',
                //	  text: '<div style="width:140px;">涉税申请查询</div>',
                text: '<div style="width:140px;">预约时间设置</div>',
                href:"yy_tax_yySet_sj.html"
            },
            {
                id : 'm1-4',
                text: '<div style="width:140px;">预约事项设置</div>',
                href:"yy_tax_yySet_sx.html",
                selected:true
            }
        ];
    }

}


//弹出手机验证码对话框
function openPhoneWin(a)
{
    $.layer({
        type:2,
        iframe:{
            src:"ysq_phone.jsp?type="+a
        },
        area:['400px','105px'],
        //	title:'手机验证码校验'
        title:'\u624B\u673A\u9A8C\u8BC1\u7801\u6821\u9A8C'
    });

}
