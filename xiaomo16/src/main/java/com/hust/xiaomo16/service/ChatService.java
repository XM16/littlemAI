package com.hust.xiaomo16.service;

import com.hust.xiaomo16.entity.ChatRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

//2019.02.25聊天记录
public interface ChatService {
    Page<ChatRecord> findAll(Pageable pageable);

    ChatRecord save(ChatRecord chatRecord);
    ChatRecord findOne(String userId);
    //通过用户ID删除聊天记录
    void deleteChatRecordByUserId(String userId);
    //通过聊天时间段删除聊天记录
    void deleteChatRecordByChatTime(Date chatTime);
}
