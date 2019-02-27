package com.hust.xiaomo16.repository;

import com.hust.xiaomo16.entity.ChatRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ChatRecordRepository extends JpaRepository<ChatRecord,String> {
    void deleteChatRecordByUserId(String userId);
    void deleteChatRecordByChatTime(Date chatTime);

}
