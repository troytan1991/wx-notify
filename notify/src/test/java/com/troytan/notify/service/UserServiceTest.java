package com.troytan.notify.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.troytan.notify.manager.CmzyManager;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CmzyManager cmzyManager;

    @Test
    @Rollback(false)
    public void registerGroupTest() {
        cmzyManager.updateNews();
    }
}
