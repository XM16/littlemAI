<html>
<head>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.css" rel="stylesheet">
</head>

<body>
    <#--主要内容content-->
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <form role="form" method="post" action="/xiaomo/buy/order/add">
                    <div class="form-group">
                        <label for="buyerName">下单人名字</label><input type="text" class="form-control" name="buyerName" id="buyerName" />
                    </div>
                    <div class="form-group">
                        <label for="buyerPhone">下单人联系方式</label><input type="text" class="form-control" name="buyerPhone" id="buyerPhone" />
                    </div>

                    <button type="submit" class="btn btn-default">提交订单</button>
                </form>
            </div>
        </div>
    </div>


</body>
</html>