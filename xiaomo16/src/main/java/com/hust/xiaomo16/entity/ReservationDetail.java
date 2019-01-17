package com.hust.xiaomo16.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity

public class ReservationDetail {
    @Id
    private  String ReservaID;

    private String UserID;

    private Date reserTime;

    private Integer tableType;

    private Integer queNum;

    private String status;

    public String getReservaID() {
        return ReservaID;
    }

    public String getUserID() {
        return UserID;
    }

    public Date getReserTime() {
        return reserTime;
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

    public void setReservaID(String reservaID) {
        ReservaID = reservaID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public void setReserTime(Date reserTime) {
        this.reserTime = reserTime;
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
