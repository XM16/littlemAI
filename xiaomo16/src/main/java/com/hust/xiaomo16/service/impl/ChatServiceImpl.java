package com.hust.xiaomo16.service.impl;

import com.hust.xiaomo16.entity.ChatRecord;
import com.hust.xiaomo16.repository.ChatRecordRepository;
import com.hust.xiaomo16.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRecordRepository repository;

    @Override
    @Cacheable(key = "1234")
    public ChatRecord findOne(String userId) {
        return repository.findOne(userId);
    }

    @Override
    public Page<ChatRecord> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Cacheable(key = "1234")
    public ChatRecord save(ChatRecord chatRecord) {
        return repository.save(chatRecord);
    }


    @Override
    public void deleteChatRecordByUserId(String userId) {
        repository.deleteChatRecordByUserId(userId);
    }


    @Override
    public void deleteChatRecordByChatTime(Date chatTime) {
        repository.deleteChatRecordByChatTime(chatTime);

    }


}
