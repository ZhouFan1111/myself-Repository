/**
 * et-v2.0.1 2018-09-18
 */

$(document).ready(function () {

    //控制panel-collapse折叠按钮图标状态
    if ($('.panel .collapse').length > 0) {
        //展开时执行
        $('.panel .collapse').on('show.bs.collapse', function () {
            $(this).parent().find('.et-toggle-btn span').addClass('glyphicon-menu-up').removeClass('glyphicon-menu-down');
        })
        //折叠时执行
        $('.panel .collapse').on('hide.bs.collapse', function () {
            $(this).parent().find('.et-toggle-btn span').addClass('glyphicon-menu-down').removeClass('glyphicon-menu-up');
        })
        //检查
        $('.collapse.in').parent().find('.et-toggle-btn span').addClass('glyphicon-menu-up').removeClass('glyphicon-menu-down');
    }

    //按钮控制快捷键伸缩
    if ($('body .et-admin-shortcuts-collapse').length > 0) {
        $('.et-admin-shortcuts-collapse').on('click', function (e) {
            e.preventDefault();
            if ($('.et-admin-shortcuts').hasClass('et-active')) {
                $('.et-admin-shortcuts').removeClass('et-active');
                $('.et-admin-content').removeClass('et-move-down');
            } else {
                $('.et-admin-shortcuts').addClass('et-active');
                $('.et-admin-content').addClass('et-move-down');
            }
        });
    }

    //定时关闭快捷键模块
//	setTimeout(function(){
//		$('.et-admin-shortcuts').removeClass('et-active');
//		$('.et-admin-content').removeClass('et-move-down');
//	},
//	3000);

    //快捷栏滑动
    if ($('body .et-admin-shortcuts').length > 0) {
        var oDiv = $('#et-scroll');
        var oUl = oDiv.find('.et-shortcut');
        var aLi = oUl.find('.et-item');

        var PageWidth = 5;//每次移动(px)
        var timer = null;//循环
        var speed = 10; //速度
        var scroll_Left = 0;

        //设置宽度
        oUl.width(aLi.outerWidth(true) * aLi.length + 'px');

        //左侧按键绑定事件
        $('.et-admin-shortcuts .et-scroll-left').on('mousedown', function (e) {
            e.preventDefault();
            timer = setInterval(function () {
                scroll_Left -= PageWidth;
                oDiv.scrollLeft(scroll_Left)
            }, speed);
        })

        //右侧侧按键绑定事件
        $('.et-admin-shortcuts .et-scroll-right').on('mousedown', function (e) {
            e.preventDefault();
            timer = setInterval(function () {
                scroll_Left += PageWidth;
                oDiv.scrollLeft(scroll_Left)
            }, speed);
        })

        $('.et-admin-shortcuts .et-scroll-left,.et-admin-shortcuts .et-scroll-right').on('mouseup', function (e) {
            clearInterval(timer);
        })

        $('.et-admin-shortcuts .et-scroll-left,.et-admin-shortcuts .et-scroll-right').on('mouseout', function (e) {
            clearInterval(timer);
        })


    }

    //快捷键弹出事件
    if ($('body .et-admin-shortcuts [data-toggle="layer"]').length > 0) {
        $('[data-toggle="layer"]').on('click', function (e) {
            e.preventDefault();
            layer.closeAll();
            layer.open({
                type: 1,
                title: 0,
                shade: 0,
                time: 10000,
                shadeClose: 1,
                offset: 'auto',
                content: $(this).siblings('div').html()
            });
            $(document).on("click", ".layui-layer-content .et-btn-custom.et-close", function () {
                layer.closeAll();
            });
        })
    }

    //admin左侧伸缩
    if ($('body .et-admin-left-collapse').length > 0) {
        $('.et-admin-left-collapse').on('click', function (e) {
            e.preventDefault();
            if (!$('.et-admin-left').hasClass('et-hide-left')) {
                $('.et-admin-left').addClass('et-hide-left');
                $('.et-admin-shortcuts').addClass('et-expanded-full');
                $('.et-admin-content').addClass('et-expanded-full');
                $('.et-admin-content-fixed').addClass('et-expanded-full');
                $('.et-admin-left-collapse.et-arrows i').removeClass('fa-angle-left').addClass('fa-angle-right');
            } else {
                $('.et-admin-left').removeClass('et-hide-left');
                $('.et-admin-shortcuts').removeClass('et-expanded-full');
                $('.et-admin-content').removeClass('et-expanded-full');
                $('.et-admin-content-fixed').removeClass('et-expanded-full');
                $('.et-admin-left-collapse.et-arrows i').removeClass('fa-angle-right').addClass('fa-angle-left');
            }
        });
        if (!$('.et-admin-left').hasClass('et-hide-left')) {
            $('.et-admin-left-collapse.et-arrows i').removeClass('fa-angle-right').addClass('fa-angle-left');
        } else {
            $('.et-admin-left-collapse.et-arrows i').removeClass('fa-angle-left').addClass('fa-angle-right');
        }
    }

    //设置slimScroll滚动条
    if ($('body .et-admin-left .et-slimscroll').length > 0) {
        $('body .et-admin-left .et-slimscroll').slimScroll({
            height: '100%',
            wheelStep: 5,
            color: '#AAAAAA',
        });
    }


    //设置table中含有input的td、th内边距为0
    if ($('body .table .et-table-form-control').length > 0) {
        $fr = $('body .table .et-table-form-control');
        $fr.parent('td').css('padding', '0');
        $fr.parent('th').css('padding', '0');
    }


    //左侧主菜单
    if ($('.et-admin-menu .et-sub-menu').length > 0) {
        //添加折叠事件
        $('.et-admin-menu .et-sub-menu').siblings('a').on('click', function (e) {
            e.preventDefault();
            $li = $(this).parent('li');
            if (!$li.hasClass('active')) {
                $li.find(' > a .et-toggle-icon').removeClass('glyphicon-menu-left').addClass('glyphicon-menu-down');
                $li.addClass('active');
                $li.find(' > ul.et-sub-menu').slideDown(300);
                if ($li.parent('ul').hasClass('et-onlyone')) {
                    $li.siblings('li').find(' > a .et-toggle-icon').removeClass('glyphicon-menu-down').addClass('glyphicon-menu-left');
                    $li.siblings('li').removeClass('active');
                    $li.siblings('li').find('ul.et-sub-menu').slideUp(300);
                }
            } else {
                $li.find(' > a .et-toggle-icon').removeClass('glyphicon-menu-down').addClass('glyphicon-menu-left');
                $li.removeClass('active');
                $li.find(' > ul.et-sub-menu').slideUp(300);
            }
        });
        //如果没有子菜单则初始化左侧高度（因首页控制了左侧导航的高度）
        $('.et-admin-menu a').each(function(){
            if(!$(this).siblings('.et-sub-menu').length && $(this).attr('target')=='et-index-iframe'){
                $(this).on('click',function(){
                    $(".et-slimscroll").css('max-height','100%');
                    // $(".et-slimscroll").removeClass('et-limit');
                })
            }
        })
        checkMinified();
    }


    //检查左侧导航
    function checkMinified() {
        $('.et-admin-menu .active>.et-sub-menu')
            .css('display', 'block')
            .css('overflow', 'visible')
            .siblings('a').find('.et-toggle-icon').removeClass('glyphicon-menu-left').addClass('glyphicon-menu-down');
    }


    //初始化自定义下拉菜单
    $(window).bind("load resize", function () {
        initDropdownBlack();
    });


    //tabs切换后处理Dropdown
    $('.et-nav-tabs a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        initDropdownBlack();
    })

});


//首页搜索指引
function indexGuideSearch(){
    $("#et-search").addClass('guide');
    $("#et-search").on('click',function(){
        ajaxSetGuideSearch();
        $("#et-search").removeClass('guide');
        $('#et-search > input').focus();
    })
}

//首页我的收藏-申报功能指引
function gdGuideShow() {
    $(".et-gd-guide").show();
    $(".et-gd-guide >.item").on('click', function(e) {
        e.preventDefault();
        ajaxSetGuideSearch();
        $(".et-gd-guide").hide();
    })
}


//初始化首页自定义下拉菜单
function initDropdownBlack() {
    $('.dropdown-black').each(function () {
        $(this).width($(this).parent().parent().width() - parseFloat($(this).css('padding-left')) * 2 - parseFloat($(this).css('border-width')) * 2).css('left', 0 - ($(this).parent().position().left));
    })
}


//用户引导
function indexGuide(){
    //IE9以下浏览器不引导
    var DEFAULT_VERSION = 9.0;
    var ua = navigator.userAgent.toLowerCase();
    var isIE = ua.indexOf("msie")>-1;
    var safariVersion;
    if(isIE){
        safariVersion =  ua.match(/msie ([\d.]+)/)[1];
    }
    if(safariVersion <= DEFAULT_VERSION ){
        return;
    };

    //判断是否已引导
    if(localStorageUtil.getItem("etGuide") == "read"){return;}
    $(".et-index-guide").show();

    $(".et-index-guide-items .item").eq(0).show();
    $(".et-index-guide .clear").on('click',function(){
        $(".et-index-guide").hide();
        $(".et-index-guide-items").hide();
        localStorageUtil.setItem("etGuide", "read");
    })
    $(".et-index-guide-items .item a").on('click',function(){
        if($(this).parent().hide().next().length){
            $(this).parent().hide().next().show();
        }else{
            $(".et-index-guide").hide();
            localStorageUtil.setItem("etGuide", "read");
        }
    })
}

//若浏览器不支持localStorage则使用cookie储存
var localStorageUtil = {
    //获取localStorage，将数据从localStorage中取出
    getItem: function (item) {
        var value = null;
        if (this.hasLocalStorage()) {
            try {
                value = localStorage.getItem(item);
            } catch (erroe) {
                console.log("localStorage.getItem出错！" + error.message);
            } finally {
                return value;
            }
        } else {
            return this.getCookie(item);
        }

    },
    //设置localStotage， 将数据储存到localSttorage中
    setItem: function (key, value) {
        if (this.hasLocalStorage()) {
            try {
                //safari 无痕模式下，直接使用localStorage
                localStorage.setItem(key, value);
            } catch (error) {
                console.log("localStorage.setItem出错！" + error.message);
            }
        } else {
            this.setCookie(key, value);
        }
    },
    //判断浏览器是否支持 localStorage
    hasLocalStorage: function () {
        return window.Storage && window.localStorage && window.localStorage instanceof Storage;
    },
    //设置cookie
    setCookie: function (key, value, date) {
        var t = date || 30 * 3;
        var d = new Date();
        d.setTime(d.getTime() + (t * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = key + "=" + value + ";" + expires + ";path=/";

    },
    //获取cookie
    getCookie: function (name) {
        var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
        if (arr = document.cookie.match(reg)) {
            return arr[2];
        }
        return null;
    }

}


//datatable方法封装
function dataTablesInit(elo) {
    $('#' + elo.property.tableId).DataTable({
        ajax: {
            url: elo.requestUrl,   //请求后台路径
            dataSrc: '',
            error: function (jqXHR, textStatus, errorMsg) {
                layer.alert("请求失败");
            }
        },
        "searching": elo.gridInit.searching, //搜索框，默认是开启
        "lengthChange": elo.gridInit.lengthChange, //是否允许用户改变表格每页显示的记录数，默认是开启
        "paging": elo.gridInit.paging, //是否开启本地分页，默认是开启
        "processing": elo.gridInit.processing, //是否显示处理状态
        "scrollCollapse": elo.gridInit.scrollCollapse, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变
        "serverSide": elo.gridInit.serverSide, //开启服务器模式，默认是关闭
        "scrollY": elo.gridInit.scrollY, //设置高
        "scrollX": elo.gridInit.scrollX, //设置宽度
        "autoWidth": elo.gridInit.autoWidth, //是否自适应宽度
        "columns": elo.columns, //字段
        "columnDefs": elo.columnDefs, //列表状态
        "ordering": elo.gridInit.ordering, //是否允许Datatables开启排序
        "pageLength": elo.gridInit.pageLength, //默认每页显示条数
        "pagingType": elo.gridInit.pagingType, //分页按钮显示选项:首页，尾页，上一页和下一页四个按钮,加上数字按钮
        "lengthMenu": elo.gridInit.lengthMenu, //分页选项
        "order": elo.order,
        "info": elo.info,
        "language": {
            "sProcessing": "处理中...",
            "sLengthMenu": "显示 _MENU_ 项结果",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "显示 _START_ - _END_ 项，共 _TOTAL_ 项，  ",
            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        },
        "dom": "<'row'<'col-sm-12'<'#" + elo.property.buttonId + ".et-datatable-toolbar'>>>" +
        "<'row'<'col-xs-12'tr>>" +
        "<'row et-margin-top-xs'<'col-xs-12 col-sm-5 et-padding-horizontal-lg '<'pull-left'i><'pull-left'l>><'col-xs-12 col-sm-7 et-padding-right-lg'p>>",
        "initComplete": function () {
            $("#" + elo.property.buttonId).append(elo.buttons);
            if (elo.gridInit.search) {
                $search = $("input[type='search']");
                //隐藏默认的搜索框
                $search.parent().hide();
            }

            //单击行
            if (elo.gridInit.trClick) {
                $('#' + elo.property.tableId + ' tbody').off("click", "tr");
                $('#' + elo.property.tableId + ' tbody').on("click", "tr", function () {
                    if (elo.gridInit.singleSelection) {
                        $("#" + elo.property.tableId + " input[name='checkList']").prop("checked", false);
                        $("#" + elo.property.tableId + " tr").removeClass('selected');
                    }
                    $(this).toggleClass('selected');
                    if ($(this).hasClass("selected")) {
                        $(this).find("input").prop("checked", true);
                    } else {
                        $(this).find("input").prop("checked", false);
                    }

                });
            }
            //双击行
            if (elo.gridInit.trDblClick) {
                $('#' + elo.property.tableId + ' tbody').on("dblclick", "tr", elo.dblClick);
            }
            //checkbox全选
            $("#" + elo.property.checkAllId).click(function () {
                if ($(this).prop("checked")) {
                    $("#" + elo.property.tableId + " input[name='checkList']").prop("checked", true);
                    $("#" + elo.property.tableId + " tr").addClass('selected');
                } else {
                    $("#" + elo.property.tableId + " input[name='checkList']").prop("checked", false);
                    $("#" + elo.property.tableId + " tr").removeClass('selected');
                }
            });
        }
    })
    //添加序号
    if (elo.gridInit.serialNumber) {
        $("#" + elo.property.tableId).DataTable().on('order.dt search.dt',
            function () {
                $("#" + elo.property.tableId).DataTable().column(0, {
                    search: 'applied',
                    order: 'applied'
                }).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            }).draw();
    }

}

