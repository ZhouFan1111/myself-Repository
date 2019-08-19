/**
 * BootStrap分页导航
 * @param allPage 一共多少页
 * @param currentPage 当前页（从1开始）
 * @returns {String} 一个nav标签的bootstrap分页导航
 * @author KevinMa
 */
function pagination(allPage, currentPage) {
    var pageNumbers = '';
    var previous = '<li><a href="javascript:void(0)" onclick="searchPage(' + (currentPage - 1) + ')" aria-label="Previous"><span aria-hidden="true">«</span></a></li>';
    var next = '<li><a href="javascript:void(0)" onclick="searchPage(' + (currentPage + 1) + ')" aria-label="Next"><span aria-hidden="true">»</span></a></li>';

    var from = currentPage - 2;
    var to = currentPage + 2;

    if (allPage < 5) {
        from = 1;
        to = allPage;
    } else {
        from = currentPage - 2;
        to = from + 4;
        if (from < 1) {
            from = 1;
            to = 5;
        }
        if (to >= allPage) {
            to = allPage;
            from = to - 4;
        }
    }

    //上一页，下一页
    if (currentPage == 1) {
        previous = '<li class="disabled"><span aria-label="Previous"><span aria-hidden="true">«</span></span></li>';
    }
    if (currentPage == allPage) {
        next = '<li class="disabled"><span aria-label="Next"><span aria-hidden="true">»</span></span></li>';
    }

    //装入页码
    for (; from <= to; from++) {
        if (from == currentPage) {
            pageNumbers = pageNumbers + '<li class="active"><span>' + from + '<span class="sr-only">(current)</span></span></li>';
            continue;
        }
        pageNumbers = pageNumbers + '<li><a href="javascript:void(0)" onclick="searchPage(' + from + ')">' + from + '</a></li>';
    }


    pageNumbers = previous + pageNumbers;
    pageNumbers = pageNumbers + next;
    pageNumbers = '<ul class="pagination">' + pageNumbers;
    pageNumbers = pageNumbers + '</ul>';
    return pageNumbers;
}