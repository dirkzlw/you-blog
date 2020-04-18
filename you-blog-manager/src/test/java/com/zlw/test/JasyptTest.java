package com.zlw.test;

import com.zlw.manager.ManagerApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Dirk
 * @date 2020-04-18 9:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ManagerApplication.class)
public class JasyptTest {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    public void testJasypt(){
        System.out.println(encryptor.encrypt("root"));
        System.out.println(encryptor.encrypt("aliroot"));
    }

}
