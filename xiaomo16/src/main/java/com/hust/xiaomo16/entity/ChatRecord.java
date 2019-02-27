package com.hust.xiaomo16.entity;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

//2019.02.25聊天记录实体
@Entity
@Data
@DynamicUpdate
public class ChatRecord {
    @Id
    private String sentenceId;
    //用户ID
    private String userId;
    //聊天时间
    private Date chatTime;
    //判断是否为机器人(0为用户，1为机器人)
    private int isRobot;
    private String sentenceInfo;

}
