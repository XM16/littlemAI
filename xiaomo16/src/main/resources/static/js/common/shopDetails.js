/*
* 自定义的js代码,用于获取店铺内所有的商品
*
* */

/* 定义一个全局变量用于记录末页的页码,服务于页面自动跳转到最后一页 */
var maxPage;
var currentPage;
var shopId = $("#shopId").val();
$(function(){
    build_shopDetails("/common/ajax/shop/get");
    build_shopCategory("/common/ajax/productCategory/all");
    to_page("/common/ajax/product/allOnSalesByShopId",1,1);
})

//点击链接"所有商品时,清除筛选的分类商品,获取所有上架中商品,相当于刷新了店铺最初的状态"
$("#allProducts").click(function () {
    to_page("/common/ajax/product/allOnSalesByShopId",1,1);
    return false;
});

//生成分类栏条目;
function build_shopCategory(url) {
    $.ajax({
        url:url,
        type:"get",
        success:function (result) {
            var productCategoryList = result.extend.productCategoryList;

            $("#shopCategoryList").empty();
            var categoryLevel =1;
            $.each(productCategoryList,function (index,productCategory) {

                if (categoryLevel != productCategory.priority){
                    $("#shopCategoryList").append(
                        "<hr/>" +
                        "<a class='nav-item shopCategory' style='color: #040505' href='#' categoryId='"+productCategory.productCategoryId+"'>"+productCategory.productCategoryName+"</a>"
                    )
                }else{
                    if (0 != index){
                        $("#shopCategoryList").append(
                            "<span >/</span>"
                        )
                    }
                    $("#shopCategoryList").append(
                        "<a class='nav-item shopCategory' style='color: #040505' href='#' categoryId='"+productCategory.productCategoryId+"'>"+productCategory.productCategoryName+"</a>"
                    )
                }
                
                categoryLevel = productCategory.priority;
            });

        }
    });
}

//生成店铺描述
function build_shopDetails(url) {
    $.ajax({
        url:url,
        type:"get",
        success:function (result) {
            var shop = result.extend.shop;
            var isFavorite = result.extend.isFavorite;
            if (true == isFavorite){
                $("#shopDetailsDiv").append("<button type='button' class='btn btn-sm btn-outline-secondary btn_cancelCollectShop' shopId='"+shop.shopId +"' ><span class='glyphicon glyphicon-heart'></span> 取消收藏</button>")

            }else {
                $("#shopDetailsDiv").append("<button type='button' class='btn btn-sm btn-outline-secondary btn_collectShop' shopId='"+shop.shopId +"' ><span class='glyphicon glyphicon-heart-empty'></span> 收藏</button>")

            }
        }
    });
}


//收藏店铺
$("#shopDetailsDiv").on('click','.btn_collectShop',function () {
    var userCenter =$("#userCenter").text();
    if("" == userCenter){
        window.location.href="/login";
    }
    var thisBtn = $(this);
    var shopId = thisBtn.attr("shopId");
    $.ajax({
        url:"/buyer/ajax/favoriteShop/add",
        type:'POST',
        data:{'shopId':shopId},
        success:function (result) {
            //判断当收藏成功时切换取消收藏按钮
            if (200 == result.code) {
                //这里必须在ajax的回掉函数外面就用变量thisBtn代替$(this),否则实现不了功能;
                thisBtn.removeClass("btn_collectShop").addClass("btn_cancelCollectShop");
                thisBtn.empty();
                thisBtn.append("<span class='glyphicon glyphicon-heart'></span>");
                thisBtn.append(" 取消收藏")
            }
            alert(result.msg);
        }
    });
})

//取消收藏店铺
$("#shopDetailsDiv").on('click','.btn_cancelCollectShop',function () {
    var userCenter =$("#userCenter").text();
    if("" == userCenter){
        window.location.href="/login";
    }
    var thisBtn = $(this);
    var shopId = thisBtn.attr("shopId");
    $.ajax({
        url:"/buyer/ajax/favoriteShop/delete",
        type:'POST',
        data:{'shopId':shopId},
        success:function (result) {
            //判断当收藏成功时切换取消收藏按钮
            if (200 == result.code) {
                //这里必须在ajax的回掉函数外面就用变量thisBtn代替$(this),否则实现不了功能;
                thisBtn.removeClass("btn_cancelCollectShop").addClass("btn_collectShop");
                thisBtn.empty();
                thisBtn.append("<span class='glyphicon glyphicon-heart-empty'></span>");
                thisBtn.append(" 收藏")
            }
            alert(result.msg);
        }
    });
})




/*页面上分类栏里的分类点击后的事件,由于链接是后生成的,所以要交给原本就存在的上级去绑定*/
$("#shopCategoryList").on('click','.shopCategory',function () {
    var categoryId = $(this).attr("categoryId");
    //这里直接使用修改后的to_page分页跳转方法;
    to_page("/common/ajax/product/allOnSalesByShopIdAndCategoryId",categoryId,1);
    return false;
})

//在分类状态下的分页跳转;
function to_page(url,categoryId,pn){
    $.ajax({
        url:url,
        data:{"categoryId":categoryId,"shopId":shopId,"pn":pn},
        type:"post",
        success:function(result){
            maxPage = result.extend.pageInfo.pages;
            currentPage = result.extend.pageInfo.pageNum;
            build_product_table(result);
            build_page_info(result);
            build_page_nav(url,categoryId,result);
        }
    });
}


/*获取商品信息并回显,需要传入一个url字符串以调用控制层不同的查询方法*/
function build_product_table(result){
    var flag=0;//用于生成收藏按钮时的判断,注意作用域,并且在每个商品遍历完后要重置成0;
    if(null != result.extend.favoriteProductList){
        var favoriteProductList = result.extend.favoriteProductList;
    }
    $("#productListRow").empty();
    var productList = result.extend.pageInfo.list;
    if (0 == productList.length){
        $("#productListRow").append(
            "<div class='container text-center'>" +
            "<h4>当前分类下没有商品</h4>"+
            "</div>"
        );
    } else {
        $.each(productList,function (index,product) {
            if(null != favoriteProductList){
                $.each(favoriteProductList,function (index,favoriteProduct) {
                    if (product.productId == favoriteProduct.productId){
                        flag = 1;
                    }
                })
            }
            var createTime = new Date(product.createTime);
            var editTime = new Date(product.editTime);
            if (0 == flag){
                $("#productListRow").append(
                    "<div class='col-lg-3 col-md-6 col-sm-6'>" +
                    "<div class='card mb-4 box-shadow'>" +
                    "<img class='card-img-top' src='../../../" +product.imgAddr +"' alt='商品图片'>" +
                    "<div class='card-body'>" +

                    "<h4>" +product.productName + "</h4>" +
                    "<div class='status'>" +
                    "<a class=\"text-muted\" href='/common/shop/shopDetailsPage/"+ product.shop.shopId+"'>店铺 : "+ product.shop.shopName +"</a>" +
                    "</div>" +
                    "<div>" +
                    "<span>$ " +product.normalPrice + "</span>"+
                    "</div>" +
                    "<div>" +
                    "<small class='text-muted'>商品创建时间 : " +createTime.toLocaleDateString() +"</small>" +
                    "</div>" +
                    "<div>" +
                    "<small class='text-muted'>最后编辑时间 : " +editTime.toLocaleDateString()+ "</small>" +
                    "</div>" +
                    "<div>" +
                    "<small class='text-muted'>商品分类 : "+product.productCategory.productCategoryName+ "</small>" +
                    "</div>" +
                    "<p class='card-text'>" +product.productDesc + "</p>" +
                    "<div class=''>" +
                    "<div class='btn-group'>" +
                    "<button type='button' class='btn btn-sm btn-outline-secondary btn_collectProduct'  productId='"+product.productId+"'><span class='glyphicon glyphicon-heart-empty'></span> 收藏</button>" +
                    "<button type='button' class='btn btn-sm btn-outline-secondary btn_addToCart'  productId='"+product.productId+"'>加入购物车</button>" +
                    "</div>" +
                    "</div>" +
                    "</div>"+
                    "</div>"
                );
            } else{
                $("#productListRow").append(
                    "<div class='col-lg-3 col-md-6 col-sm-6'>" +
                    "<div class='card mb-4 box-shadow'>" +
                    "<img class='card-img-top' src='../../../" +product.imgAddr +"' alt='商品图片'>" +
                    "<div class='card-body'>" +

                    "<h4>" +product.productName + "</h4>" +
                    "<div class='status'>" +
                    "<a class=\"text-muted\" href='/common/shop/shopDetailsPage/"+ product.shop.shopId+"'>店铺 : "+ product.shop.shopName +"</a>" +
                    "</div>" +
                    "<div>" +
                    "<span>$ " +product.normalPrice + "</span>"+
                    "</div>" +
                    "<div>" +
                    "<small class='text-muted'>商品创建时间 : " +createTime.toLocaleDateString() +"</small>" +
                    "</div>" +
                    "<div>" +
                    "<small class='text-muted'>最后编辑时间 : " +editTime.toLocaleDateString()+ "</small>" +
                    "</div>" +
                    "<div>" +
                    "<small class='text-muted'>商品分类 : "+product.productCategory.productCategoryName+ "</small>" +
                    "</div>" +
                    "<p class='card-text'>" +product.productDesc + "</p>" +
                    "<div class=''>" +
                    "<div class='btn-group'>" +
                    "<button type='button' class='btn btn-sm btn-outline-secondary btn_cancelCollectProduct'  productId='"+product.productId+"'><span class='glyphicon glyphicon-heart'></span> 取消收藏</button>" +
                    "<button type='button' class='btn btn-sm btn-outline-secondary btn_addToCart'  productId='"+product.productId+"'>加入购物车</button>" +
                    "</div>" +
                    "</div>" +
                    "</div>"+
                    "</div>"
                );
            }
            //注意要将flag的值重置为0;
            flag =0;
        })

    }
}


//将单个商品添加进购物车
$("#productListRow").on('click','.btn_addToCart',function () {
    var userCenter =$("#userCenter").text();
    if("" == userCenter){
        window.location.href="/login";
    }
    var productId = $(this).attr("productId");
    $.ajax({
        url:"/buyer/ajax/cart/addProduct",
        type:'POST',
        data:{'productId':productId},
        success:function (result) {
            alert(result.msg);
        }
    });
})

//收藏商品
$("#productListRow").on('click','.btn_collectProduct',function () {
    var userCenter =$("#userCenter").text();
    if("" == userCenter){
        window.location.href="/login";
    }
    var thisBtn = $(this);
    var productId = thisBtn.attr("productId");
    $.ajax({
        url:"/buyer/ajax/favoriteProduct/add",
        type:'POST',
        data:{'productId':productId},
        success:function (result) {
            //判断当收藏成功时切换取消收藏按钮
            if (200 == result.code) {
                //这里必须在ajax的回掉函数外面就用变量thisBtn代替$(this),否则实现不了功能;
                thisBtn.removeClass("btn_collectProduct").addClass("btn_cancelCollectProduct");
                thisBtn.empty();
                thisBtn.append("<span class='glyphicon glyphicon-heart'></span>");
                thisBtn.append(" 取消收藏")
            }
            alert(result.msg);
        }
    });
})

//取消收藏商品
$("#productListRow").on('click','.btn_cancelCollectProduct',function () {
    var userCenter =$("#userCenter").text();
    if("" == userCenter){
        window.location.href="/login";
    }
    var thisBtn = $(this);
    var productId = thisBtn.attr("productId");
    $.ajax({
        url:"/buyer/ajax/favoriteProduct/delete",
        type:'POST',
        data:{'productId':productId},
        success:function (result) {
            //判断当收藏成功时切换取消收藏按钮
            if (200 == result.code) {
                //这里必须在ajax的回掉函数外面就用变量thisBtn代替$(this),否则实现不了功能;
                thisBtn.removeClass("btn_cancelCollectProduct").addClass("btn_collectProduct");
                thisBtn.empty();
                thisBtn.append("<span class='glyphicon glyphicon-heart-empty'></span>");
                thisBtn.append(" 收藏")
            }
            alert(result.msg);
        }
    });
})


/*分页功能部分*/
/*  当前第${pageInfo.pageNum }/${pageInfo.pages }页,当前页记录数:${pageInfo.size }条;总记录数:${pageInfo.total }条; */
function build_page_info(result){
    /* 生成新的元素前一定要先清空掉以前的数据,否则会累加到页面上 */
    $("#page_info").empty();
    var productList = result.extend.pageInfo.list;
    if (0 == productList.length){
        return ;
    }
    $("#page_info").append("当前第"+result.extend.pageInfo.pageNum+"/"+result.extend.pageInfo.pages+"页,当前页记录数:"+result.extend.pageInfo.size+"条;总记录数:"+result.extend.pageInfo.total+"条");
}

/* 显示分页条 */
function build_page_nav(url,categoryId,result){
    /* 生成新的元素前一定要先清空掉以前的数据,否则会累加到页面上 */
    $("#page_nav").empty();
    var productList = result.extend.pageInfo.list;
    if (0 == productList.length){
        return ;
    }
    var ul = $("<ul></ul>").addClass("pagination");
    /* 生成首页按钮 */
    var li_frist = $("<li class='page-item'></li>").append($("<a class='page-link'></a>").attr("href","#").append("首页"));
    ul.append(li_frist);
    /* 生成上一页按钮 */
    var li_pre = $("<li class='page-item'></li>").append($("<a class='page-link'></a>").attr("href","#").append("&laquo;"));
    ul.append(li_pre);

    /* 当没有上一页时则按钮不可点击,否则可以点击 */
    if(result.extend.pageInfo.hasPreviousPage == false){
        li_frist.addClass("disabled");
        li_pre.addClass("disabled");
    }else{
        /* 为首页按钮添加一个点击跳转到首页的绑定事件 */
        li_frist.click(function(){
            to_page(url,categoryId,1);
            return false;
        });
        /* 为上一页按钮添加一个点击跳转到上一页的绑定事件 */
        li_pre.click(function(){
            to_page(url,categoryId,result.extend.pageInfo.prePage);
            return false;
        });
    }

    /* 遍历分页信息中是否有12345等页码数 */
    $.each(result.extend.pageInfo.navigatepageNums,function(index,nums){
        var a_nums = $("<a class='page-link'></a>").attr("href","#").append(nums)
        var li_nums = $("<li class='page-item'></li>").append(a_nums);

        if(result.extend.pageInfo.pageNum == nums){
            /* 当前页码高亮显示 ,如果不是当前页码,则会在else里添加点击功能*/
            li_nums.addClass("active");
        }else{
            /* 为每一个遍历后生成出来的li_nums添加一个点击跳转的绑定事件 */
            li_nums.click(function(){
                to_page(url,categoryId,nums);
                return false;
            });
        }
        ul.append(li_nums);
    });

    /* 生成下一页按钮 */
    var li_next = $("<li class='page-item'></li>").append($("<a class='page-link'></a>").attr("href","#").append("&raquo;"));
    ul.append(li_next);
    /* 生成末页按钮 */
    var li_last = $("<li class='page-item'></li>").append($("<a class='page-link'></a>").attr("href","#").append("末页"));
    ul.append(li_last);

    /* 当没有下一页时按钮不可点击,否则可以点击 */
    if(result.extend.pageInfo.hasNextPage == false){
        li_next.addClass("disabled");
        li_last.addClass("disabled");
    }else{
        /* 为下一页按钮添加一个点击跳转到下一页的绑定事件 */
        li_next.click(function(){
            to_page(url,categoryId,result.extend.pageInfo.nextPage);
            return false;
        });
        /* 为末页按钮添加一个点击跳转到末页的绑定事件 */
        li_last.click(function(){
            to_page(url,categoryId,result.extend.pageInfo.pages);
            return false;
        });
    }

    var nav = $("<nav></nav>").attr("aria-label","Page navigation").append(ul);
    $("#page_nav").append(nav);
}















