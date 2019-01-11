# 订单模块API


###创建订单

```
POST /xmsell/buyer/order/create
```

参数

```json
name: "孙笑川"
phone: "18868822111"
userid: "sxcnmsl1234" //用户的userid
items: [{
    productId: "1423113435324",
    productQuantity: 2 //购买数量
}]

```

返回

```json
{
  "code": 0,
  "msg": "成功",
  "data": {
      "orderId": "147283992738221" 
  }
}
```

###订单列表

```
GET /xmsell/buyer/order/list
```

参数

```
userId: sxcnmsl1234
page: 0 //从第0页开始
size: 10
```

返回

```json
{
  "code": 0,
  "msg": "成功",
  "data": [
    {
      "orderId": "161873371171128075",
      "buyerName": "孙笑川",
      "buyerPhone": "18868822111",
      "buyerUserid": "sxcnmsl1234",
      "orderAmount": 0,
      "orderStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    },
    {
      "orderId": "161873371171128076",
      "buyerName": "张三",
      "buyerPhone": "18868877111",
      "buyerUserid": "sxcnmsl1235",
      "orderAmount": 0,
      "orderStatus": 0,
      "createTime": 1490171219,
      "updateTime": 1490171219,
      "orderDetailList": null
    }]
}
```

###查询订单详情

```
GET /xmsell/buyer/order/detail
```

参数

```
userId: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

返回

```json
{
    "code": 0,
    "msg": "成功",
    "data": {
          "orderId": "161899085773669363",
          "buyerName": "李四",
          "buyerPhone": "18868877111"
          "buyerUserid": "18eu2jwk2kse3r42e2e",
          "orderAmount": 18,
          "orderStatus": 0,
          "createTime": 1490177352,
          "updateTime": 1490177352,
          "orderDetailList": [
            {
                "detailId": "161899085974995851",
                "orderId": "161899085773669363",
                "productId": "157875196362360019",
                "productName": "招牌奶茶",
                "productPrice": 9,
                "productQuantity": 2,
                "productIcon": "http://xxx.com",
                "productImage": "http://xxx.com"
            }
        ]
    }
}
```

###取消订单

```
POST /xmsell/buyer/order/cancel
```

参数

```
userId: 18eu2jwk2kse3r42e2e
orderId: 161899085773669363
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": null
}
```


