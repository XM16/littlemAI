<html>
    <head>
        <meta charset ="utf-8">
        <title>卖家订单列表</title>
        <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>

    <body>
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>订单id </th>
                        <th>客户姓名</th>
                        <th>客户电话</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>支付状态</th>
                        <th>创建时间</th>
                        <th colspan="2"> 操作</th>
                    </tr>
                    </thead>

                    <tbody>
                        <#list orderDTOPage.content as orderDTO>
                        <tr>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.buyerName}</td>
                            <td>${orderDTO.buyerPhone}</td>
                            <td>${orderDTO.orderId}</td>
                            <td>${orderDTO.orderStatus}</td>
                            <td>${orderDTO.payStatus}</td>
                            <td>${orderDTO.createTime}</td>
                            <td>详情</td>
                            <td>取消</td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
            <#--分页-->
                <ul class="pagination pull-right">
                         <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                         <#else>
                            <li><a href="list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                         </#if>

                            <#list 1..orderDTOPage.getTotalPages() as index>
                                <#if currentPage == index>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                    <li><a href="list?page=${index}&size=3">${index}</a></li>
                                </#if>
                            </#list>

                            <#if currentPage gte orderDTOPage.getTotalPages()>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else>
                                <li><a href="list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                            </#if>

                    <li>
                        <a href="#">下一页</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    </body>


</html>



