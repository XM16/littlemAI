package com.hust.xiaomo16.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity

public class ReservationDetail {
    @Id
    private  String reservaId;

    private String userId;

    private Date reservaTime;

    private Integer tableType;

    private Integer queNum;

    private String status;

    public String getReservaId() {
        return reservaId;
    }

    public String getUserId() {
        return userId;
    }

    public Date getReservaTime() {
        return reservaTime;
    }

    public Integer getTableType() {
        return tableType;
    }

    public Integer getQueNum() {
        return queNum;
    }

    public String getStatus() {
        return status;
    }

    public void setReservaId(String reservaID) {
        reservaId = reservaID;
    }

    public void setUserId(String userID) {
        userId = userID;
    }

    public void setReservaTime(Date reserTime) {
        this.reservaTime = reserTime;
    }

    public void setTableType(Integer tableType) {
        this.tableType = tableType;
    }

    public void setQueNum(Integer queNum) {
        this.queNum = queNum;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
