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
                    <form role="form" method="post" action="/xiaomo/reservation/save">
                        <div class="form-group">
                            <label>预约Id</label>
                            <input name="reservaId" class="form-control" readonly="readonly" type="text" value="${(reservationDetail.reservaId)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>用户Id</label>
                            <input name="userId" type="number" readonly="readonly" class="form-control" value="${(reservationDetail.userId)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>预约时间</label>
                            <input name="reserTime" type="text" readonly="readonly" class="form-control" value="${(reservationDetail.reservaTime)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>排队号码</label>
                            <input name="queNum" type="number" readonly="readonly" class="form-control" value="${(reservationDetail.queNum)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>当前状态</label>
                            <input name="status" type="text" class="form-control" value="${(reservationDetail.status)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>餐桌类型</label>
                            <input name="tableType" readonly="readonly" type="number" class="form-control" value="${(reservationDetail.tableType)!''}"/>
                        </div>

                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>