function update(){
    var d = {};
    d.userId = 30;
    d.username = "boon";
    $.ajax({
        url: "update1",
        data: JSON.stringify(d),
        //type、contentType必填,指明传参方式
        type: "POST",
        contentType: "application/json;charset=utf-8",
        success: function(response){
            //前端调用成功后，可以处理后端传回的json格式数据。
            if(response.success){
                alert(response.message);
            }
        },
        error:function(){
            alert("请求对象XMLHttpRequest: ");

        }
    });
}
