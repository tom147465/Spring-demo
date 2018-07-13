package com.zz.bill;

import com.zz.bill.entity.User;
import static org.junit.Assert.assertTrue;
import com.zz.bill.service.user.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRun {

    @Autowired
    private IUserService userService;

    @Test
    public void TestRegister(){
        String account = "libo";
        String pwd = "123123123";
        User user = new User();
        user.setAccount(account);
        user.setPwd(pwd);

        assertTrue(userService.checkExist(account));
        try {
            userService.insertNewUser(user);
        } catch (RuntimeException e) {

            System.out.println(e.getClass());
            //e.printStackTrace();
        }
    }



}
