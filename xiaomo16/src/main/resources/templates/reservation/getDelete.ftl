<html>
<#include "../common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>预约ID</th>
                            <th>用户ID</th>
                            <th>餐桌类型</th>
                            <th>预约时间</th>
                            <th>排队号码</th>
                            <th>预约状态</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list reservationList as reservation>
                            <tr>
                                <td>${reservation.reservaId}</td>
                                <td>${reservation.userId}</td>
                                <td>${reservation.tableType}</td>
                                <td>${reservation.reservaTime}</td>
                                <td>${reservation.queNum}</td>
                                <td>${reservation.status}</td>
                                <td><a href="/xiaomo/reservation/delete?userId=${reservation.userId}">删除</a></td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
    </div>

</div>
</body>
</html>