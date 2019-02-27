$('.conLeft li').on('click', function () {
	$(this).addClass('bg').siblings().removeClass('bg');
	var intername = $(this).children('.liRight').children('.intername').text();
	$('.headName').text(intername);
	$('.newsList').html('');
})
$('.sendBtn').on('click', function () {
	var news = $('#dope').val();
    news=news.replace("\n","");
	if (news == '') {
		alert('不能为空');
	} else {
		$('#dope').val('');
		var str = '';
		str += ' <li>' +
			'<div class="nesHead"><img src="/xiaomo/images/tou.jpg" "/></div>' +
			'<div class="news"><img class="jiao" src="/xiaomo/images/jiao.jpg" >' + news + '</div>' +
			'</li> ';
		$('.newsList').append(str);

		Send(news);
		$('.conLeft').find('li.bg').children('.liRight').children('.infor').text(news);
		$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight);

	}
	var d = {};
	d.userId = 30;
	d.userInput = news;
	$.ajax({
		url: "update",

		data: JSON.stringify(d),
		//type、contentType必填,指明传参方式

		type: "POST",
		contentType: "application/json;charset=utf-8",

		success: function(response){
			//前端调用成功后，可以处理后端传回的
			if(response.success){
				answers(response.message);
				//alert(response.message);
			}
		},
		error:function(){
			alert("请求对象XMLHttpRequest: ");

		}});


})

function send(event){
    //如果按了回车键也发送信息
    if(event.keyCode==13) {//键盘按下Enter键
        var news = $('#dope').val();
        news=news.replace("\n","");
        if (news == '') {
            alert('不能为空');
        } else {
            $('#dope').val('');
            var str = '';
            str += ' <li>' +
                '<div class="nesHead"><img src="/xiaomo/images/tou.jpg" "/></div>' +
                '<div class="news"><img class="jiao" src="/xiaomo/images/jiao.jpg" >' + news + '</div>' +
                '</li> ';
            $('.newsList').append(str);

            Send(news);
            $('.conLeft').find('li.bg').children('.liRight').children('.infor').text(news);
            $('.RightCont').scrollTop($('.RightCont')[0].scrollHeight);

        }
        var d = {};
        d.userId = 30;
        d.userInput = news;
        $.ajax({
            url: "update",

            data: JSON.stringify(d),
            //type、contentType必填,指明传参方式

            type: "POST",
            contentType: "application/json;charset=utf-8",

            success: function(response){
                //前端调用成功后，可以处理后端传回的
                if(response.success){
                    answers(response.message);
                    //alert(response.message);
                }
            },
            error:function(){
                alert("请求对象XMLHttpRequest: ");

            }});
    }
}
var xmlHttp

function Send(news) {
	xmlHttp = GetXmlHttpObject()

	if (xmlHttp == null) {

		alert("您的浏览器不支持AJAX！");

		return;

	}

	var url = "http://www.tuling123.com/openapi/api?key=19c44544782a4f8d9205ff0a49d232df";

	url = url + "&info=" + news;

	url = url + "&userid=1234";

	xmlHttp.onreadystatechange = stateChanged;

	xmlHttp.open("GET", url, true);

	xmlHttp.send(null);

	/**
	 * 这里的代码被我注释掉了
	 */
	 //document.getElementById("dope").value = "";

}

function stateChanged() {

	// xmlHttp.readyState
	// 0 （未初始化）
	// 对象已建立，但是尚未初始化（尚未调用open方法）

	// 1 （初始化）
	// 已调用send()方法，正在发送请求

	// 2 （发送数据）
	// send方法调用完成，但是当前的状态及http头未知

	// 3 （数据传送中）
	// 已接收部分数据，因为响应及http头不全，这时通过responseBody和responseText获取部分数据会出现错误，

	// 4 （完成）
	// 数据接收完毕，此时可以通过通过responseBody和responseText获取完整的回应数据
	if (xmlHttp.readyState == 4) {
		// var msg = eval('(' + xmlHttp.responseText + ')');
		//document.getElementById("robot").innerHTML = msg;
		var text = JSON.parse(xmlHttp.responseText).text;
	//	answers(text);
		//console.log(xmlHttp.responseText);
	}
}

function GetXmlHttpObject() {

	var xmlHttp = null;

	try {

		// Firefox, Opera 8.0+, Safari

		xmlHttp = new XMLHttpRequest();

	}

	catch (e) {

		// Internet Explorer

		try {

			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");

		}

		catch (e) {

			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");

		}

	}

	return xmlHttp;

}

/**
 * 参数被我修改了，调用方式也改为当onreadystatechange事件触发时调用并传参
 * @param {String} answer 
 */
function answers(answer) {
	/**
	 * 这里删了两句代码，把<li>标签里的变量改为了answer
	 */
	answer = '<li>' +
		'<div class="answerHead">小莫<img src="/xiaomo/images/6.jpg" /></div>' +
		'<div class="answers" id="robot"><img class="jiao" src="/xiaomo/images/20170926103645_03_02.jpg" >' +answer + '</div>' +
		'</li></html>';

	$('.newsList').append(answer);
	$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight);
}

$('.ExP').on('mouseenter', function () {
	$('.emjon').show();
})
$('.emjon').on('mouseleave', function () {
	$('.emjon').hide();
})
$('.emjon li').on('click', function () {
	var imgSrc = $(this).children('img').attr('src');
	var str = "";
	str += '<li>' +
		'<div class="nesHead"><th:text="${session.loginUser}" /> <img src="images/tou.jpg"/></div>' +
		'<div class="news"><img class="jiao" src="images/20170926103645_03_02.jpg" th:src="@{/images/20170926103645_03_02.jpg}"><img class="Expr" src="' + imgSrc + '"></div>' +
		'</li>';
	$('.newsList').append(str);
	$('.emjon').hide();
	$('.RightCont').scrollTop($('.RightCont')[0].scrollHeight);
})
