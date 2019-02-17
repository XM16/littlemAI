package com.hust.xiaomo16.repository;

import com.hust.xiaomo16.entity.ManagerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import javax.transaction.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest

public class ManagerInfoRepositoryTest {

    @Autowired ManagerInfoRepository repository;

    @Test
    public void save(){
        ManagerInfo managerInfo = new ManagerInfo();
        managerInfo.setManagerId("123");
        managerInfo.setMUsername("boonAnna");
        managerInfo.setMPassword("admin2333");
        repository.save(managerInfo);
    }

    @Test
    public void findByManagerId() {
        ManagerInfo managerInfo = repository.findByManagerId("123");
        Assert.assertNotNull(managerInfo);
    }
}