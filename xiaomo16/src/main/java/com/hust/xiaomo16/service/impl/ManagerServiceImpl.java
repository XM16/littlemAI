package com.hust.xiaomo16.service.impl;

import com.hust.xiaomo16.entity.ManagerInfo;
import com.hust.xiaomo16.entity.User;
import com.hust.xiaomo16.repository.ManagerInfoRepository;
import com.hust.xiaomo16.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: sell
 * @description:
 * @author: Boon Guan
 * @create: 2019-01-24 12:17
 **/
@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerInfoRepository repository;

    @Override
    public ManagerInfo register(ManagerInfo managerInfo) {

        return repository.save(managerInfo);
    }

    @Override
    public void delete(String mUsername) {
        repository.delete(mUsername);
    }

    @Override
    public List<ManagerInfo> findManagerList() {
        return repository.findAll();
    }

    @Override
    public void update(ManagerInfo managerInfo) {
        repository.save(managerInfo);
    }

    @Override
    public ManagerInfo findManager(String mUsername) {
        return repository.findManagerByMuserame(mUsername);
    }
}
